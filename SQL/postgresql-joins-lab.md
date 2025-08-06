
# PostgreSQL Joins Lab: Practice & Interview Prep

## 📦 Prerequisites

Before diving into joins, make sure you're comfortable with:
- SQL basics: `SELECT`, `FROM`, `WHERE`, `GROUP BY`, `ORDER BY`
- Table creation and data types (`INT`, `VARCHAR`, `DATE`, etc.)
- Primary and foreign keys
- Aggregate functions: `COUNT()`, `AVG()`, `SUM()`, etc.
- PostgreSQL interface (pgAdmin, psql, SQLFiddle, etc.)

---

## 🧱 Base Schema: Company HR System

### Tables:

```sql
CREATE TABLE departments (
    dept_id SERIAL PRIMARY KEY,
    dept_name VARCHAR(50)
);

CREATE TABLE employees (
    emp_id SERIAL PRIMARY KEY,
    emp_name VARCHAR(100),
    dept_id INT REFERENCES departments(dept_id),
    hire_date DATE
);

CREATE TABLE projects (
    proj_id SERIAL PRIMARY KEY,
    proj_name VARCHAR(100),
    emp_id INT REFERENCES employees(emp_id)
);

CREATE TABLE salaries (
    emp_id INT REFERENCES employees(emp_id),
    salary NUMERIC(10, 2),
    valid_from DATE,
    valid_to DATE
);
```

### Sample Data:

```sql
-- Departments
INSERT INTO departments (dept_name) VALUES 
('Engineering'), ('Marketing'), ('HR'), ('Finance');

-- Employees
INSERT INTO employees (emp_name, dept_id, hire_date) VALUES
('Alice', 1, '2020-01-10'),
('Bob', 1, '2021-03-15'),
('Charlie', 2, '2019-07-01'),
('Diana', 3, '2022-09-23'),
('Eve', NULL, '2023-01-01');

-- Projects
INSERT INTO projects (proj_name, emp_id) VALUES
('AI Engine', 1),
('Web Revamp', 2),
('Ad Campaign', 3),
('HR Portal', 4),
('Data Migration', NULL);

-- Salaries
INSERT INTO salaries (emp_id, salary, valid_from, valid_to) VALUES
(1, 80000, '2020-01-01', '2021-01-01'),
(1, 90000, '2021-01-02', '2022-01-01'),
(1, 100000, '2022-01-02', NULL),
(2, 70000, '2021-03-15', NULL),
(3, 60000, '2019-07-01', NULL),
(4, 65000, '2022-09-23', NULL);
```

---

## 🔗 SQL Join Types Explained

### 1. INNER JOIN

Returns only matched rows from both tables.

```sql
SELECT emp_name, dept_name
FROM employees
INNER JOIN departments ON employees.dept_id = departments.dept_id;
```

| emp_name | dept_name   |
|----------|-------------|
| Alice    | Engineering |
| Bob      | Engineering |
| Charlie  | Marketing   |
| Diana    | HR          |

---

### 2. LEFT JOIN

Returns all rows from the left table, and matched rows from right.

```sql
SELECT emp_name, dept_name
FROM employees
LEFT JOIN departments ON employees.dept_id = departments.dept_id;
```

| emp_name | dept_name   |
|----------|-------------|
| Alice    | Engineering |
| Bob      | Engineering |
| Charlie  | Marketing   |
| Diana    | HR          |
| Eve      | NULL        |

---

### 3. RIGHT JOIN

Same as LEFT JOIN, but keeps all rows from the **right** table.

```sql
SELECT dept_name, emp_name
FROM departments
RIGHT JOIN employees ON departments.dept_id = employees.dept_id;
```

---

### 4. FULL OUTER JOIN

Returns all rows from both tables. NULL where no match.

```sql
SELECT emp_name, dept_name
FROM employees
FULL OUTER JOIN departments ON employees.dept_id = departments.dept_id;
```

---

### 5. CROSS JOIN

Returns the cartesian product of both tables.

```sql
SELECT emp_name, dept_name
FROM employees
CROSS JOIN departments;
```

---

## 💡 Real-World Tips

| Join Type      | Real-World Example                                |
|----------------|---------------------------------------------------|
| INNER JOIN     | Find employees with valid departments             |
| LEFT JOIN      | List all employees (even if not assigned)         |
| RIGHT JOIN     | List all departments and whoever is assigned      |
| FULL OUTER JOIN| Merging datasets from different sources           |
| CROSS JOIN     | Generating combinations (e.g., test cases)        |

---

## 🧪 Practice Problems & Answers

### ✅ Problem 1  
**List all employees and the names of their departments (even if no department).**

```sql
SELECT emp_name, dept_name
FROM employees
LEFT JOIN departments ON employees.dept_id = departments.dept_id;
```

---

### ✅ Problem 2  
**Show all departments and the names of employees in them (even if no employees).**

```sql
SELECT dept_name, emp_name
FROM departments
LEFT JOIN employees ON departments.dept_id = employees.dept_id;
```

---

### ✅ Problem 3  
**List all employees not assigned to any project.**

```sql
SELECT emp_name
FROM employees
LEFT JOIN projects ON employees.emp_id = projects.emp_id
WHERE projects.proj_id IS NULL;
```

---

## 🪟 Bonus: Window Function Sneak Peek

### Rank Salary History

```sql
SELECT emp_id, salary, valid_from,
       RANK() OVER (PARTITION BY emp_id ORDER BY valid_from DESC) AS salary_rank
FROM salaries;
```

| emp_id | salary  | valid_from | salary_rank |
|--------|---------|------------|-------------|
| 1      | 100000  | 2022-01-02 | 1           |
| 1      | 90000   | 2021-01-02 | 2           |
| 1      | 80000   | 2020-01-01 | 3           |
| 2      | 70000   | 2021-03-15 | 1           |

---

## 💼 Interview Questions

| Question                                                | Topic          |
|---------------------------------------------------------|----------------|
| Difference between INNER and LEFT JOIN?                 | Join logic     |
| How to find unmatched records in a join?                | LEFT JOIN + IS NULL |
| What is a cartesian product?                            | CROSS JOIN     |
| How to get the latest record per group?                 | Window functions |
| When to use FULL OUTER JOIN in real life?               | Data merging   |

---

## ✅ Next Topics

- More window functions (`ROW_NUMBER`, `DENSE_RANK`)
- Subqueries and CTEs
- Aggregation with GROUP BY
- Joins with conditions in WHERE vs ON

---

Happy Querying! 🧠
