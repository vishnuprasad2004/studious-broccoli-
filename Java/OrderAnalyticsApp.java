import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.*;

public class OrderAnalyticsApp {

    public static void main(String[] args) {

        List<Order> orders = getSampleOrders();

        // 👉 Call your methods here and print results to test

    }

    // =========================
    // TODO: IMPLEMENT BELOW USING STREAM API ONLY
    // =========================

    // 1️⃣ Total revenue from COMPLETED orders
    public static double getTotalRevenue(List<Order> orders) {
        double sum = orders.stream()
                .map(Order::getAmount)
                .reduce(0.0, Double::sum);
        return sum;

    }

    // 2️⃣ Top 3 highest value orders
    public static List<Order> getTop3Orders(List<Order> orders) {
        List<Order> top3Orders = orders.stream()
                .sorted(Comparator.comparingDouble(Order::getAmount).reversed())
                .limit(3)
                .toList();
        return top3Orders;
    }

    // 3️⃣ Unique cities
    public static Set<String> getUniqueCities(List<Order> orders) {
        return orders.stream()
                .map(Order::getCity)
                .distinct()
                .collect(Collectors.toSet());
    }

    // 4️⃣ Group by city
    public static Map<String, List<Order>> groupByCity(List<Order> orders) {
        // TODO
        return Collections.emptyMap();
    }

    // 5️⃣ Count orders by status
    public static Map<String, Long> countByStatus(List<Order> orders) {
        // TODO
        return Collections.emptyMap();
    }

    // 6️⃣ Customer who spent the most
    public static Optional<String> getTopSpendingCustomer(List<Order> orders) {
        // TODO
        return Optional.empty();
    }

    // 7️⃣ Any order above 100000?
    public static boolean hasHighValueOrder(List<Order> orders) {
        // TODO
        return false;
    }

    // 8️⃣ Partition orders into High Value (>50000)
    public static Map<Boolean, List<Order>> partitionHighValue(List<Order> orders) {
        // TODO
        return Collections.emptyMap();
    }

    // 9️⃣ Monthly revenue report
    public static Map<Month, Double> getMonthlyRevenue(List<Order> orders) {
        // TODO
        return Collections.emptyMap();
    }

    // 🔟 Average order value per city
    public static Map<String, Double> getAverageOrderValuePerCity(List<Order> orders) {
        // TODO
        return Collections.emptyMap();
    }

    // =========================
    // SAMPLE DATA
    // =========================

    private static List<Order> getSampleOrders() {

        return Arrays.asList(
                new Order(1L, "Alice", "Hyderabad", 45000, "COMPLETED", LocalDate.of(2025, 1, 10)),
                new Order(2L, "Bob", "Chennai", 75000, "COMPLETED", LocalDate.of(2025, 2, 15)),
                new Order(3L, "Alice", "Hyderabad", 150000, "COMPLETED", LocalDate.of(2025, 3, 5)),
                new Order(4L, "David", "Bangalore", 25000, "PENDING", LocalDate.of(2025, 1, 20)),
                new Order(5L, "Emma", "Chennai", 99000, "CANCELLED", LocalDate.of(2025, 2, 25)),
                new Order(6L, "Frank", "Mumbai", 120000, "COMPLETED", LocalDate.of(2025, 4, 18)),
                new Order(7L, "Alice", "Hyderabad", 30000, "COMPLETED", LocalDate.of(2025, 5, 10)),
                new Order(8L, "George", "Mumbai", 67000, "PENDING", LocalDate.of(2025, 3, 12))
        );
    }
}


// =========================
// ORDER CLASS
// =========================

class Order {

    private Long orderId;
    private String customerName;
    private String city;
    private double amount;
    private String status; // COMPLETED, CANCELLED, PENDING
    private LocalDate orderDate;

    public Order(Long orderId, String customerName, String city,
                 double amount, String status, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.city = city;
        this.amount = amount;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCity() {
        return city;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", city='" + city + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
