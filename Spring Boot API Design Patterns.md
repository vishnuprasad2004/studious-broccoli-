# Spring Boot API Design Patterns & Best Practices

> A complete reference covering Security, Request/Response Design, Error Handling, Versioning, Performance, and everything in between — with real code examples and edge cases.

---

## Table of Contents

1. [Standardized API Response Structure](#1-standardized-api-response-structure)
2. [Global Exception Handling](#2-global-exception-handling)
3. [API Versioning](#3-api-versioning)
4. [Request Validation](#4-request-validation)
5. [Security — Authentication & Authorization](#5-security--authentication--authorization)
6. [Rate Limiting & Throttling](#6-rate-limiting--throttling)
7. [Idempotency](#7-idempotency)
8. [Pagination, Filtering & Sorting](#8-pagination-filtering--sorting)
9. [HATEOAS](#9-hateoas)
10. [HTTP Status Codes — Used Correctly](#10-http-status-codes--used-correctly)
11. [Logging & Correlation IDs (Traceability)](#11-logging--correlation-ids-traceability)
12. [Audit Logging](#12-audit-logging)
13. [CORS Configuration](#13-cors-configuration)
14. [Content Negotiation](#14-content-negotiation)
15. [DTO Pattern & MapStruct](#15-dto-pattern--mapstruct)
16. [Soft Delete Pattern](#16-soft-delete-pattern)
17. [Optimistic Locking (Concurrency Control)](#17-optimistic-locking-concurrency-control)
18. [Caching](#18-caching)
19. [Circuit Breaker Pattern](#19-circuit-breaker-pattern)
20. [API Documentation (OpenAPI / Swagger)](#20-api-documentation-openapi--swagger)
21. [Input Sanitization & SQL Injection Prevention](#21-input-sanitization--sql-injection-prevention)
22. [Sensitive Data Masking](#22-sensitive-data-masking)
23. [HTTP Security Headers](#23-http-security-headers)
24. [Async APIs & Webhooks](#24-async-apis--webhooks)
25. [Health Checks & Readiness Probes](#25-health-checks--readiness-probes)

---

## 1. Standardized API Response Structure

### The Problem
Without a standard response envelope, every endpoint returns a different shape. Frontend devs have no contract to code against.

### The Pattern — Consistent Envelope

```java
// Generic wrapper for all API responses
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private ApiError error;
    private Map<String, Object> meta;      // pagination, timestamps, etc.
    private String traceId;                // for debugging
    private Instant timestamp;

    // Success factory
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.timestamp = Instant.now();
        return response;
    }

    // Success with message
    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> response = success(data);
        response.message = message;
        return response;
    }

    // Error factory
    public static <T> ApiResponse<T> error(String message, String errorCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.error = new ApiError(errorCode, message);
        response.timestamp = Instant.now();
        return response;
    }
}
```

```java
@Data
@AllArgsConstructor
public class ApiError {
    private String code;        // Machine-readable: "USER_NOT_FOUND"
    private String message;     // Human-readable: "The user with ID 42 was not found"
    private List<FieldError> fieldErrors;  // For validation failures
}
```

### Usage in Controller

```java
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable Long id) {
    UserDTO user = userService.findById(id);
    return ResponseEntity.ok(ApiResponse.success(user));
}
```

### What the Frontend Receives

```json
// ✅ Success
{
  "success": true,
  "data": { "id": 1, "name": "John Doe", "email": "john@example.com" },
  "timestamp": "2025-03-09T14:00:00Z",
  "traceId": "abc-123-xyz"
}

// ✅ Error
{
  "success": false,
  "error": {
    "code": "USER_NOT_FOUND",
    "message": "User with ID 999 does not exist"
  },
  "timestamp": "2025-03-09T14:00:00Z",
  "traceId": "abc-123-xyz"
}
```

### Edge Cases
- Always include `traceId` — even on errors — so support teams can search logs
- `@JsonInclude(NON_NULL)` prevents `null` fields from cluttering responses
- Never return raw entity objects — always go through a DTO

---

## 2. Global Exception Handling

### The Problem
Without centralized handling, every controller has try/catch blocks, stack traces leak to users, and error formats are inconsistent.

### The Pattern — `@RestControllerAdvice`

```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 404 — Resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex,
                                                             HttpServletRequest request) {
        log.warn("Resource not found: {} | URI: {}", ex.getMessage(), request.getRequestURI());
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error(ex.getMessage(), "RESOURCE_NOT_FOUND"));
    }

    // 400 — Validation failure
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(fe -> new FieldError(fe.getField(), fe.getDefaultMessage()))
            .collect(Collectors.toList());

        ApiResponse<Void> response = ApiResponse.error("Validation failed", "VALIDATION_ERROR");
        response.getError().setFieldErrors(errors);
        return ResponseEntity.badRequest().body(response);
    }

    // 403 — Access denied
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.error("You do not have permission to perform this action", "ACCESS_DENIED"));
    }

    // 409 — Conflict (e.g., duplicate email)
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicate(DuplicateResourceException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(ex.getMessage(), "DUPLICATE_RESOURCE"));
    }

    // 500 — Catch-all: NEVER expose stack traces
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error at {}: {}", request.getRequestURI(), ex.getMessage(), ex);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.error("An unexpected error occurred. Please try again later.", "INTERNAL_ERROR"));
    }
}
```

```java
// Custom domain exceptions
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s with ID %d not found", resourceName, id));
    }
}

// Usage in service
public User findById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", id));
}
```

### Edge Cases
- Never log at ERROR level for 4xx — use WARN. ERROR should alert your on-call team
- The generic handler catches `Exception`, which includes `NullPointerException` — always log the full stack trace internally but return a clean message
- `HttpMessageNotReadableException` fires when JSON is malformed — handle it separately with a 400

---

## 3. API Versioning

### Strategies Compared

| Strategy | Example | Pros | Cons |
|---|---|---|---|
| URI Path | `/api/v1/users` | Simple, visible, cacheable | URL pollution |
| Header | `X-API-Version: 1` | Clean URLs | Hard to test in browser |
| Query Param | `/users?version=1` | Easy to test | Can conflict with filters |
| Media Type | `Accept: application/vnd.app.v1+json` | RESTful standard | Complex |

### Recommended — URI Path Versioning

```java
// Version 1
@RestController
@RequestMapping("/api/v1/users")
public class UserV1Controller {

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserV1DTO>> getUser(@PathVariable Long id) {
        // Returns: id, name, email
    }
}

// Version 2 — added phone, split name into firstName/lastName
@RestController
@RequestMapping("/api/v2/users")
public class UserV2Controller {

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserV2DTO>> getUser(@PathVariable Long id) {
        // Returns: id, firstName, lastName, email, phone
    }
}
```

### Edge Cases
- Always keep old versions running until clients migrate — announce deprecation with `Deprecation` and `Sunset` headers
- Never break a version — add new fields, never remove or rename them in the same version
- Document the migration guide when releasing v2

```java
// Deprecation headers
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<UserV1DTO>> getUser(@PathVariable Long id,
                                                       HttpServletResponse response) {
    response.addHeader("Deprecation", "true");
    response.addHeader("Sunset", "Sat, 01 Jan 2026 00:00:00 GMT");
    response.addHeader("Link", "</api/v2/users/" + id + ">; rel=\"successor-version\"");
    // ...
}
```

---

## 4. Request Validation

### The Pattern — Bean Validation + Custom Validators

```java
@Data
public class CreateUserRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phone;

    @Valid  // Triggers nested validation
    @NotNull
    private AddressRequest address;
}
```

```java
// Custom validator — e.g., password strength
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface StrongPassword {
    String message() default "Password must be at least 8 characters with uppercase, lowercase, digit, and special char";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class PasswordValidator implements ConstraintValidator<StrongPassword, String> {
    private static final Pattern PATTERN = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && PATTERN.matcher(value).matches();
    }
}
```

```java
// Controller — activate with @Valid
@PostMapping
public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody CreateUserRequest request) {
    UserDTO created = userService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(created));
}
```

### Validation Error Response

```json
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Validation failed",
    "fieldErrors": [
      { "field": "email", "message": "Must be a valid email address" },
      { "field": "firstName", "message": "First name is required" }
    ]
  }
}
```

### Edge Cases
- Validate at the controller layer — never trust anything from the client
- `@Validated` (Spring) vs `@Valid` (Jakarta) — use `@Validated` on the class for group-based validation
- Cross-field validation (e.g., `startDate` before `endDate`) requires a class-level constraint

```java
// Cross-field validation
@DateRangeValid  // class-level annotation
public class BookingRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
```

---

## 5. Security — Authentication & Authorization

### 5.1 JWT Security Configuration

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity  // Enables @PreAuthorize on methods
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disabled for stateless APIs
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/actuator/health").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(customAuthEntryPoint)   // 401
                .accessDeniedHandler(customAccessDeniedHandler)   // 403
            );

        return http.build();
    }
}
```

### 5.2 Method-Level Authorization

```java
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // Only the user themselves OR an admin can view a profile
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable Long id) { ... }

    // Only admins can delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) { ... }

    // Only the resource owner
    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id,
                                                            @Valid @RequestBody UpdateUserRequest request) { ... }
}
```

### 5.3 Principle of Least Privilege

```java
// Bad: one role does everything
@PreAuthorize("hasRole('USER')")

// Good: granular permissions
@PreAuthorize("hasAuthority('user:read')")
@PreAuthorize("hasAuthority('user:write')")
@PreAuthorize("hasAuthority('order:delete')")
```

### Edge Cases
- Always return `401 Unauthorized` for missing/invalid token — not `403`
- Return `403 Forbidden` when authenticated but lacking permission
- Never reveal whether a resource exists to unauthorized users — return `404` instead of `403` to avoid enumeration attacks
- Validate JWT signature, expiration, issuer, AND audience on every request

---

## 6. Rate Limiting & Throttling

### The Problem
Without limits, a single user or bot can flood your API and cause a denial of service.

### Pattern — Using Bucket4j

```xml
<dependency>
    <groupId>com.github.vladimir-bukhtoyarov</groupId>
    <artifactId>bucket4j-core</artifactId>
    <version>8.x.x</version>
</dependency>
```

```java
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket getBucket(String key) {
        return buckets.computeIfAbsent(key, k ->
            Bucket.builder()
                .addLimit(Bandwidth.classic(100, Refill.greedy(100, Duration.ofMinutes(1)))) // 100 req/min
                .build()
        );
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String key = getClientKey(request); // IP or user ID
        Bucket bucket = getBucket(key);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            chain.doFilter(request, response);
        } else {
            long waitSeconds = TimeUnit.NANOSECONDS.toSeconds(probe.getNanosToWaitForRefill());
            response.addHeader("Retry-After", String.valueOf(waitSeconds));
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());  // 429
            response.getWriter().write("{\"error\": \"Too many requests. Try again in " + waitSeconds + "s\"}");
        }
    }

    private String getClientKey(HttpServletRequest request) {
        // Prefer authenticated user ID over IP
        String userId = (String) request.getAttribute("userId");
        return userId != null ? "user:" + userId : "ip:" + request.getRemoteAddr();
    }
}
```

### Rate Limit Response Headers

```
X-Rate-Limit-Limit: 100
X-Rate-Limit-Remaining: 45
X-Rate-Limit-Reset: 1709999999
Retry-After: 30
```

### Edge Cases
- Rate limit by user ID when authenticated (not IP) — shared office IPs would otherwise block everyone
- Use Redis for rate limit state in multi-instance deployments
- Different limits for different tiers: free=100/min, pro=1000/min, enterprise=unlimited

---

## 7. Idempotency

### What Is It?
An idempotent operation produces the same result whether called once or many times. Critical for payment APIs, order creation, and any mutation where network retries could cause duplicates.

### The Problem Without It
```
Client sends POST /orders → network timeout
Client retries  POST /orders → two orders created!
```

### Pattern — Idempotency Keys

```java
@Service
public class IdempotencyService {

    private final RedisTemplate<String, String> redis;
    private static final Duration TTL = Duration.ofHours(24);

    public Optional<String> getCachedResponse(String key) {
        String value = redis.opsForValue().get("idempotency:" + key);
        return Optional.ofNullable(value);
    }

    public void saveResponse(String key, String responseBody) {
        redis.opsForValue().set("idempotency:" + key, responseBody, TTL);
    }
}
```

```java
@PostMapping("/orders")
public ResponseEntity<ApiResponse<OrderDTO>> createOrder(
        @RequestHeader("Idempotency-Key") String idempotencyKey,
        @Valid @RequestBody CreateOrderRequest request) {

    // Check cache first
    Optional<String> cached = idempotencyService.getCachedResponse(idempotencyKey);
    if (cached.isPresent()) {
        // Return exact same response as before
        OrderDTO order = objectMapper.readValue(cached.get(), OrderDTO.class);
        return ResponseEntity.ok(ApiResponse.success(order, "Order already processed"));
    }

    // Process new order
    OrderDTO order = orderService.create(request);

    // Cache response for future retries
    idempotencyService.saveResponse(idempotencyKey, objectMapper.writeValueAsString(order));

    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(order));
}
```

### Client Usage

```
POST /api/v1/orders
Idempotency-Key: 550e8400-e29b-41d4-a716-446655440000
```

### Edge Cases
- Validate the idempotency key format (UUID recommended)
- If two identical requests arrive simultaneously (race condition), use a database lock or Redis `SETNX`
- Return `422` if the same idempotency key is used with a **different** request body — client logic error

---

## 8. Pagination, Filtering & Sorting

### The Problem
`GET /users` that returns 1 million records will crash both server and client.

### Pattern — Pageable

```java
@GetMapping
public ResponseEntity<ApiResponse<Page<UserDTO>>> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") @Max(100) int size,
        @RequestParam(defaultValue = "createdAt") String sortBy,
        @RequestParam(defaultValue = "DESC") Sort.Direction direction,
        @RequestParam(required = false) String search,
        @RequestParam(required = false) String status) {

    // Validate sort field to prevent injection
    Set<String> allowedSortFields = Set.of("createdAt", "name", "email", "status");
    if (!allowedSortFields.contains(sortBy)) {
        throw new BadRequestException("Invalid sort field: " + sortBy);
    }

    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
    UserFilter filter = new UserFilter(search, status);

    Page<UserDTO> users = userService.findAll(filter, pageable);
    return ResponseEntity.ok(ApiResponse.successWithMeta(users));
}
```

### Paginated Response

```json
{
  "success": true,
  "data": [ ... ],
  "meta": {
    "page": 0,
    "size": 20,
    "totalElements": 1542,
    "totalPages": 78,
    "first": true,
    "last": false
  }
}
```

### Cursor-Based Pagination (for infinite scroll / large datasets)

```java
// Better than offset for real-time data — no "page drift"
@GetMapping
public ResponseEntity<ApiResponse<List<PostDTO>>> getPosts(
        @RequestParam(required = false) String cursor,  // last seen ID
        @RequestParam(defaultValue = "20") int limit) {

    List<Post> posts = postRepository.findByCursorAfter(cursor, limit + 1);
    boolean hasMore = posts.size() > limit;
    String nextCursor = hasMore ? posts.get(limit - 1).getId().toString() : null;

    return ResponseEntity.ok(ApiResponse.successWithCursor(
        posts.stream().limit(limit).map(mapper::toDTO).toList(),
        nextCursor,
        hasMore
    ));
}
```

### Edge Cases
- Cap `size` at 100 to prevent `GET /users?size=99999999`
- Whitelist allowed sort fields to prevent SQL injection via sort parameter
- Offset pagination has drift: if a row is deleted between page 1 and page 2 requests, you skip a record — use cursor pagination for feeds

---

## 9. HATEOAS

### What Is It?
**Hypermedia As The Engine Of Application State** — responses include links to related actions, so clients discover what they can do next without hardcoding URLs.

### Pattern

```java
@GetMapping("/{id}")
public ResponseEntity<EntityModel<OrderDTO>> getOrder(@PathVariable Long id) {
    OrderDTO order = orderService.findById(id);

    EntityModel<OrderDTO> model = EntityModel.of(order,
        linkTo(methodOn(OrderController.class).getOrder(id)).withSelfRel(),
        linkTo(methodOn(OrderController.class).cancelOrder(id)).withRel("cancel"),
        linkTo(methodOn(UserController.class).getUser(order.getUserId())).withRel("customer")
    );

    return ResponseEntity.ok(model);
}
```

### Response

```json
{
  "id": 42,
  "status": "PENDING",
  "total": 99.99,
  "_links": {
    "self":     { "href": "/api/v1/orders/42" },
    "cancel":   { "href": "/api/v1/orders/42/cancel" },
    "customer": { "href": "/api/v1/users/7" }
  }
}
```

---

## 10. HTTP Status Codes — Used Correctly

### The Rules

```
2xx — Success
├── 200 OK          — GET, PUT, PATCH success
├── 201 Created     — POST that creates a resource (+ Location header)
├── 202 Accepted    — Async operation started (not yet complete)
├── 204 No Content  — DELETE success, or PUT with no body

4xx — Client's fault
├── 400 Bad Request      — Malformed JSON, missing fields
├── 401 Unauthorized     — Not authenticated (no/invalid token)
├── 403 Forbidden        — Authenticated but not permitted
├── 404 Not Found        — Resource doesn't exist
├── 405 Method Not Allowed — GET on a POST-only endpoint
├── 409 Conflict         — Duplicate resource, version conflict
├── 410 Gone             — Resource existed but was permanently deleted
├── 422 Unprocessable    — Syntactically valid but semantically wrong
├── 429 Too Many Requests — Rate limit exceeded

5xx — Server's fault
├── 500 Internal Server Error — Unexpected crash
├── 502 Bad Gateway           — Upstream service failed
├── 503 Service Unavailable   — Maintenance / overloaded
├── 504 Gateway Timeout       — Upstream timed out
```

### The `Location` Header on 201

```java
@PostMapping
public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody CreateUserRequest request) {
    UserDTO created = userService.create(request);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(created.getId())
        .toUri();

    return ResponseEntity.created(location).body(ApiResponse.success(created));
    // Response header: Location: /api/v1/users/42
}
```

### Edge Cases
- `401` vs `403`: "You're not logged in" vs "You're logged in but can't do this"
- `404` vs `410`: Use `410 Gone` when you want to signal a permanently deleted resource (SEO matters too)
- Avoid `200` with an error body like `{ "success": false }` — this confuses clients and breaks HTTP semantics

---

## 11. Logging & Correlation IDs (Traceability)

### What Is a Correlation ID?
A unique ID generated per request that flows through all logs, all services, and is returned in the response. Allows you to trace a single request across microservices.

### Pattern

```java
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationIdFilter extends OncePerRequestFilter {

    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    public static final String MDC_KEY = "correlationId";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String correlationId = Optional
            .ofNullable(request.getHeader(CORRELATION_ID_HEADER))
            .orElse(UUID.randomUUID().toString()); // Generate if client didn't send one

        // Put in MDC so it appears in every log line automatically
        MDC.put(MDC_KEY, correlationId);
        response.addHeader(CORRELATION_ID_HEADER, correlationId); // Echo back to client

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_KEY); // CRITICAL: clean up after request
        }
    }
}
```

### Logback Configuration (`logback-spring.xml`)

```xml
<pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level [correlationId=%X{correlationId}] %logger{36} - %msg%n</pattern>
```

### Result in Logs

```
2025-03-09 14:22:01 [http-nio-8080] INFO [correlationId=abc-123] UserService - Fetching user 42
2025-03-09 14:22:01 [http-nio-8080] DEBUG [correlationId=abc-123] UserRepository - SQL executed in 12ms
2025-03-09 14:22:01 [http-nio-8080] INFO [correlationId=abc-123] UserController - Returning user 42
```

### Structured Logging (JSON Logs for Production)

```xml
<!-- logstash-logback-encoder for ELK Stack / Datadog -->
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
</dependency>
```

```java
// Structured log instead of string interpolation
log.info("User created", kv("userId", user.getId()), kv("email", user.getEmail()));
// Output: {"message":"User created","userId":42,"email":"john@example.com","correlationId":"abc-123"}
```

### Edge Cases
- `MDC.remove()` MUST be in a `finally` block — thread pools reuse threads, so stale correlation IDs bleed into the next request
- Propagate correlation ID to downstream HTTP calls via `RestTemplate`/`WebClient` interceptors
- Never log passwords, tokens, credit card numbers, or PII

---

## 12. Audit Logging

### What Is It?
A permanent record of **who did what, when, and to what data**. Required for compliance (GDPR, HIPAA, SOC2).

### Pattern — Spring Data JPA Auditing

```java
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
            .map(Authentication::getName);
    }
}
```

```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private Instant updatedAt;
}
```

### Custom Audit Log for Security Events

```java
@Entity
@Table(name = "audit_logs")
public class AuditLog {
    private String action;          // "USER_DELETED", "PASSWORD_CHANGED"
    private String performedBy;     // who did it
    private String targetResource;  // "User#42"
    private String ipAddress;
    private Instant timestamp;
    private String oldValue;        // JSON snapshot before
    private String newValue;        // JSON snapshot after
}

// In service
auditLogService.log(AuditAction.USER_DELETED, "User#" + id, currentUser, request.getRemoteAddr());
```

---

## 13. CORS Configuration

### The Problem
Browsers block cross-origin requests by default. You need to explicitly allow your frontend domain.

### Pattern

```java
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ❌ Never use "*" in production with credentials
        config.setAllowedOrigins(List.of(
            "https://yourapp.com",
            "https://www.yourapp.com"
        ));

        // For development
        if (activeProfile.equals("dev")) {
            config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173"));
        }

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Correlation-ID", "Idempotency-Key"));
        config.setExposedHeaders(List.of("X-Correlation-ID", "X-Rate-Limit-Remaining", "Location"));
        config.setAllowCredentials(true);  // Required for cookies (refresh token)
        config.setMaxAge(3600L);           // Preflight cache: 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }
}
```

### Edge Cases
- `allowCredentials(true)` + `allowedOrigins("*")` is **illegal** — browser will refuse it
- Always explicitly list `exposedHeaders` for headers you want the frontend to read
- OPTIONS preflight requests must return `200` immediately — Spring handles this automatically with the above config

---

## 14. Content Negotiation

### What Is It?
The client tells the server what format it wants via the `Accept` header; the server responds accordingly.

```java
@GetMapping(value = "/{id}",
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
}
```

```
GET /api/v1/users/1
Accept: application/json  → JSON response

GET /api/v1/users/1
Accept: application/xml   → XML response
```

### Custom Media Type for Versioning

```java
@GetMapping(value = "/{id}", produces = "application/vnd.myapp.v2+json")
public ResponseEntity<UserV2DTO> getUserV2(@PathVariable Long id) { ... }
```

---

## 15. DTO Pattern & MapStruct

### Why DTOs?
- Decouple your API contract from your database schema
- Prevent accidental exposure of sensitive fields (passwords, internal IDs)
- Allow independent evolution of API and database

### Pattern — Request DTO / Response DTO / Entity

```java
// What you receive from client
public record CreateUserRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email String email,
    @StrongPassword String password
) {}

// What you return to client (no password!)
public record UserDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    String role,
    Instant createdAt
) {}

// Database entity
@Entity
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;  // Never in DTO
    private String role;
    private Instant createdAt;
}
```

### MapStruct for Mapping

```java
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(CreateUserRequest request);

    List<UserDTO> toDTOList(List<User> users);
}
```

### Edge Cases
- Use `@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)` as a fallback to hide fields in JSON output
- Never return `Entity` objects directly from controllers — you risk lazy-loading exceptions and data leaks
- Use different DTOs for create vs update — update might have different required fields

---

## 16. Soft Delete Pattern

### What Is It?
Instead of permanently deleting rows, mark them as deleted. Useful for audit trails, undo functionality, and referential integrity.

```java
@Entity
public class Product {
    private Long id;
    private String name;
    private boolean deleted = false;
    private Instant deletedAt;
    private String deletedBy;
}
```

```java
// Repository — automatically filters deleted records
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // This finds only non-deleted records
    @Query("SELECT p FROM Product p WHERE p.deleted = false")
    List<Product> findAllActive();

    // Better — use @Where annotation on Entity
}
```

```java
// Even better — filter at entity level
@Entity
@Where(clause = "deleted = false")  // Applied to ALL queries automatically
@SQLDelete(sql = "UPDATE products SET deleted = true, deleted_at = NOW() WHERE id = ?")
public class Product { ... }
```

```java
// Service
public void deleteProduct(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product", id));

    product.setDeleted(true);
    product.setDeletedAt(Instant.now());
    product.setDeletedBy(getCurrentUser());
    productRepository.save(product);  // @SQLDelete fires instead of DELETE
}
```

### Edge Cases
- Unique constraints must include the `deleted` flag: `UNIQUE(email, deleted)` — otherwise you can't re-register a deleted email
- Your `findById` should still return 404 for deleted records (the `@Where` handles this)
- Implement a hard-delete job that purges records older than N days for GDPR compliance

---

## 17. Optimistic Locking (Concurrency Control)

### The Problem
Two users load the same record, both edit it, and the last save wins — silently overwriting the other's changes.

```
User A reads product (stock=10) at 14:00:00
User B reads product (stock=10) at 14:00:01
User A sets stock=8, saves at 14:00:05
User B sets stock=7, saves at 14:00:06  ← overwrites User A's change!
```

### Pattern

```java
@Entity
public class Product {
    private Long id;
    private String name;
    private int stock;

    @Version  // JPA manages this automatically
    private Long version;
}
```

```java
// What the client sends
public record UpdateProductRequest(
    String name,
    int stock,
    Long version  // Client must echo back the version they read
) {}
```

```java
// When saving, JPA checks: UPDATE products SET ... WHERE id=? AND version=?
// If version doesn't match, throws OptimisticLockException
@ExceptionHandler(OptimisticLockException.class)
public ResponseEntity<ApiResponse<Void>> handleConflict(OptimisticLockException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(ApiResponse.error("The resource was modified by someone else. Please reload and try again.", "VERSION_CONFLICT"));
}
```

### Response includes version

```json
{
  "id": 1,
  "name": "Blue Kart",
  "stock": 10,
  "version": 3
}
```

---

## 18. Caching

### Patterns

```java
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10))
            .serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(factory)
            .cacheDefaults(config)
            .build();
    }
}
```

```java
@Service
public class ProductService {

    @Cacheable(value = "products", key = "#id")  // Cache on read
    public ProductDTO findById(Long id) {
        return productRepository.findById(id).map(mapper::toDTO)
            .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @CachePut(value = "products", key = "#result.id")  // Update cache on write
    public ProductDTO update(Long id, UpdateProductRequest request) { ... }

    @CacheEvict(value = "products", key = "#id")  // Remove from cache on delete
    public void delete(Long id) { ... }
}
```

### HTTP Cache Headers

```java
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<ProductDTO>> getProduct(@PathVariable Long id) {
    ProductDTO product = productService.findById(id);
    return ResponseEntity.ok()
        .cacheControl(CacheControl.maxAge(5, TimeUnit.MINUTES).cachePublic())
        .eTag(String.valueOf(product.getVersion()))  // ETag for conditional requests
        .body(ApiResponse.success(product));
}
```

### Edge Cases
- Cache invalidation is hard — always set a reasonable TTL as a safety net
- Don't cache user-specific data in shared caches — include user ID in the cache key
- `@Cacheable` doesn't cache exceptions — if the DB is down, every request still hits the DB

---

## 19. Circuit Breaker Pattern

### What Is It?
When a downstream service (payment gateway, external API) starts failing, the circuit breaker "opens" and stops sending requests to it — returning a fallback immediately instead of waiting for timeouts.

```
CLOSED → all requests pass through
OPEN   → all requests fail immediately (fallback returned)
HALF-OPEN → test requests pass through; if they succeed, close again
```

### Pattern — Resilience4j

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
```

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        slidingWindowSize: 10
        failureRateThreshold: 50         # Open if 50% of last 10 calls fail
        waitDurationInOpenState: 30s
        permittedNumberOfCallsInHalfOpenState: 3
  retry:
    instances:
      paymentService:
        maxAttempts: 3
        waitDuration: 500ms
```

```java
@Service
public class PaymentService {

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    @Retry(name = "paymentService")
    public PaymentResult processPayment(PaymentRequest request) {
        return externalPaymentGateway.charge(request);
    }

    // Fallback — what to do when circuit is open
    public PaymentResult paymentFallback(PaymentRequest request, Exception ex) {
        log.error("Payment service unavailable, using fallback", ex);
        // Queue for retry, return pending status
        return PaymentResult.pending("Payment queued for processing");
    }
}
```

---

## 20. API Documentation (OpenAPI / Swagger)

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.x.x</version>
</dependency>
```

```java
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User management endpoints")
public class UserController {

    @Operation(
        summary = "Get user by ID",
        description = "Returns a single user. Requires authentication.",
        responses = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Not authorized"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(
            @Parameter(description = "User ID", example = "42") @PathVariable Long id) { ... }
}
```

```java
@Bean
public OpenAPI apiInfo() {
    return new OpenAPI()
        .info(new Info()
            .title("My API")
            .version("1.0")
            .description("Complete API documentation"))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(new Components()
            .addSecuritySchemes("bearerAuth",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
}
```

Swagger UI at: `http://localhost:8080/swagger-ui.html`

---

## 21. Input Sanitization & SQL Injection Prevention

### Pattern — Always Use JPA / Named Parameters

```java
// ✅ Safe — parameterized query
@Query("SELECT u FROM User u WHERE u.email = :email")
Optional<User> findByEmail(@Param("email") String email);

// ✅ Safe — Spring Data method name
Optional<User> findByEmailAndStatus(String email, UserStatus status);

// ❌ NEVER do this — SQL injection vulnerability
@Query("SELECT u FROM User u WHERE u.email = '" + email + "'")
```

### HTML Sanitization (for user-generated content)

```java
// Using OWASP Java HTML Sanitizer
PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
String safeHtml = policy.sanitize(userInput);

// Or strip all HTML
String clean = Jsoup.clean(userInput, Whitelist.none());
```

### Edge Cases
- Never trust `Content-Type` — always validate the actual body
- Validate file uploads: check MIME type by reading file bytes (not extension), limit size, scan for malware in critical systems
- Path traversal: never use user input directly in file paths — `../../../etc/passwd`

---

## 22. Sensitive Data Masking

### Pattern — Custom Serializer

```java
// Mask in logs
@JsonSerialize(using = MaskedSerializer.class)
private String creditCardNumber;  // Stored as full, logged as ****-****-****-1234

// Mask in API response entirely
@JsonIgnore
private String passwordHash;

// Partial masking in response
public String getMaskedEmail() {
    // john@example.com → j***@example.com
    return email.replaceAll("(?<=.{1}).(?=.*@)", "*");
}
```

```java
// Custom Jackson serializer for masking
public class MaskedSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null && value.length() > 4) {
            gen.writeString("****" + value.substring(value.length() - 4));
        } else {
            gen.writeString("****");
        }
    }
}
```

### Logback Masking Pattern

```xml
<!-- In logback config — mask tokens in log output -->
<pattern>%replace(%msg){'(Bearer\s+)\S+', '$1[REDACTED]'}%n</pattern>
```

---

## 23. HTTP Security Headers

```java
@Configuration
public class SecurityHeadersConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers
            .contentSecurityPolicy(csp ->
                csp.policyDirectives("default-src 'self'; frame-ancestors 'none'"))
            .frameOptions(frame -> frame.deny())                    // Prevent clickjacking
            .xssProtection(xss -> xss.enable())                    // XSS filter
            .contentTypeOptions(Customizer.withDefaults())         // No MIME sniffing
            .httpStrictTransportSecurity(hsts -> hsts
                .includeSubDomains(true)
                .maxAgeInSeconds(31536000))                        // Force HTTPS for 1 year
        );
        return http.build();
    }
}
```

### Headers You Should See on Every Response

```
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
Strict-Transport-Security: max-age=31536000; includeSubDomains
Content-Security-Policy: default-src 'self'
X-XSS-Protection: 1; mode=block
```

---

## 24. Async APIs & Webhooks

### Async Response — 202 Accepted

```java
@PostMapping("/reports")
public ResponseEntity<ApiResponse<AsyncJobDTO>> generateReport(@RequestBody ReportRequest request) {

    String jobId = UUID.randomUUID().toString();

    // Start async processing
    CompletableFuture.runAsync(() -> reportService.generate(jobId, request));

    AsyncJobDTO job = new AsyncJobDTO(jobId, "PROCESSING",
        "/api/v1/jobs/" + jobId);  // Polling URL

    return ResponseEntity
        .accepted()  // 202
        .body(ApiResponse.success(job, "Report generation started"));
}

// Client polls this endpoint
@GetMapping("/jobs/{jobId}")
public ResponseEntity<ApiResponse<JobStatusDTO>> getJobStatus(@PathVariable String jobId) {
    JobStatusDTO status = jobService.getStatus(jobId);
    return ResponseEntity.ok(ApiResponse.success(status));
}
```

### Webhook Pattern

```java
// When job completes, POST to client's callback URL
@Service
public class WebhookService {

    public void notify(String callbackUrl, Object payload) {
        String body = objectMapper.writeValueAsString(payload);
        String signature = hmacSign(body, webhookSecret);  // HMAC for verification

        restTemplate.execute(callbackUrl, HttpMethod.POST, request -> {
            request.getHeaders().add("X-Webhook-Signature", signature);
            request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            request.getBody().write(body.getBytes());
        }, null);
    }
}
```

---

## 25. Health Checks & Readiness Probes

### Custom Health Indicators

```java
@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try {
            long start = System.currentTimeMillis();
            dataSource.getConnection().isValid(1);
            long latency = System.currentTimeMillis() - start;

            return Health.up()
                .withDetail("latency_ms", latency)
                .withDetail("pool_size", hikariPool.getTotalConnections())
                .build();
        } catch (Exception ex) {
            return Health.down()
                .withDetail("error", ex.getMessage())
                .build();
        }
    }
}
```

```yaml
management:
  endpoint:
    health:
      show-details: when-authorized    # Don't expose details publicly
      probes:
        enabled: true                  # Enables /actuator/health/liveness + /readiness
  health:
    livenessstate:
      enabled: true
    readinessstate:
      enabled: true
```

### Kubernetes Probes

```yaml
livenessProbe:
  httpGet:
    path: /actuator/health/liveness
    port: 8080
  initialDelaySeconds: 30
  periodSeconds: 10

readinessProbe:
  httpGet:
    path: /actuator/health/readiness
    port: 8080
  initialDelaySeconds: 15
  periodSeconds: 5
```

---

## Summary Checklist

| Category | Pattern | Priority |
|---|---|---|
| Response | Standardized API envelope | 🔴 Critical |
| Errors | Global exception handler | 🔴 Critical |
| Security | JWT + method-level auth | 🔴 Critical |
| Security | HTTP security headers | 🔴 Critical |
| Security | Input validation + sanitization | 🔴 Critical |
| Versioning | URI path versioning | 🟠 High |
| Reliability | Idempotency keys | 🟠 High |
| Reliability | Circuit breaker | 🟠 High |
| Observability | Correlation IDs + structured logging | 🟠 High |
| Compliance | Audit logging | 🟠 High |
| Performance | Pagination (all list endpoints) | 🟠 High |
| Performance | Caching (Redis) | 🟡 Medium |
| Data | DTO pattern + MapStruct | 🟡 Medium |
| Data | Soft delete | 🟡 Medium |
| Data | Optimistic locking | 🟡 Medium |
| Docs | OpenAPI / Swagger | 🟡 Medium |
| Rate | Rate limiting | 🟡 Medium |
| Discovery | HATEOAS | 🟢 Nice to have |
| Async | 202 + polling / webhooks | 🟢 Nice to have |