# Database Normalization

*(1NF, 2NF, 3NF, BCNF) with definitions, analogies, and examples*

---

## What is Normalization?

**Normalization** is the process of organizing data in a database to:

* reduce redundancy
* avoid anomalies (insert, update, delete issues)
* improve data integrity

Think of it as **cleaning and organizing your cupboard** so every item has a proper place.

---

## Why Normalization is needed?

Without normalization, you face:

* **Update anomaly** – same data updated in multiple places
* **Insert anomaly** – cannot insert data without other data
* **Delete anomaly** – deleting one thing removes important info

---

# 1️⃣ First Normal Form (1NF)

## Definition

A table is in **1NF** if:

* Each column contains **atomic (indivisible) values**
* No repeating groups or multi-valued attributes
* Each record can be uniquely identified

---

## Analogy

Imagine a **phone contact**.
One contact should not store multiple phone numbers in a single field.

❌ `9876, 9123`
✅ One phone number per row

---

## Example (Not in 1NF)

| StudentID | Name | Subjects      |
| --------- | ---- | ------------- |
| 1         | Alex | Math, Science |

❌ `Subjects` has multiple values

---

## Convert to 1NF

| StudentID | Name | Subject |
| --------- | ---- | ------- |
| 1         | Alex | Math    |
| 1         | Alex | Science |

✅ Atomic values
✅ No repeating groups

---

# 2️⃣ Second Normal Form (2NF)

## Definition

A table is in **2NF** if:

* It is already in **1NF**
* **No partial dependency**
* Every non-key attribute depends on the **entire primary key**

> Partial dependency happens only when the primary key is **composite**

---

## Analogy

Think of a **college ID card**:

* Roll Number identifies the student
* Subject Code identifies the subject
* Student name should not depend only on subject

---

## Example (Not in 2NF)

**Primary Key: (StudentID, Subject)**

| StudentID | Subject | StudentName | Marks |
| --------- | ------- | ----------- | ----- |
| 1         | Math    | Alex        | 90    |
| 1         | Science | Alex        | 85    |

❌ `StudentName` depends only on `StudentID`, not full key

---

## Convert to 2NF

### Student Table

| StudentID | StudentName |
| --------- | ----------- |
| 1         | Alex        |

### Marks Table

| StudentID | Subject | Marks |
| --------- | ------- | ----- |
| 1         | Math    | 90    |
| 1         | Science | 85    |

✅ No partial dependency
✅ All attributes depend on full key

---

# 3️⃣ Third Normal Form (3NF)

## Definition

A table is in **3NF** if:

* It is already in **2NF**
* No **transitive dependency**
* Non-key attributes depend **only on primary key**

---

## Analogy

Your **Aadhaar number** determines you.
Your **pincode** determines city.
City should not be stored repeatedly for each Aadhaar.

---

## Example (Not in 3NF)

| EmpID | EmpName | DeptID | DeptName |
| ----- | ------- | ------ | -------- |
| 101   | John    | D1     | HR       |

❌ `DeptName` depends on `DeptID`, not directly on `EmpID`

---

## Convert to 3NF

### Employee Table

| EmpID | EmpName | DeptID |
| ----- | ------- | ------ |
| 101   | John    | D1     |

### Department Table

| DeptID | DeptName |
| ------ | -------- |
| D1     | HR       |

✅ No transitive dependency
✅ Clean separation

---

# 4️⃣ Boyce-Codd Normal Form (BCNF)

## Definition

A table is in **BCNF** if:

* It is in **3NF**
* **Every determinant is a candidate key**

---

## Analogy

Only **roll numbers** should decide student details.
No other attribute should behave like a key.

---

## Example (Not in BCNF)

| Student | Subject | Teacher |
| ------- | ------- | ------- |
| Alex    | Math    | Mr.A    |
| Sam     | Math    | Mr.A    |

* Subject → Teacher
* Subject is **not a candidate key**

❌ Violates BCNF

---

## Convert to BCNF

### SubjectTeacher Table

| Subject | Teacher |
| ------- | ------- |
| Math    | Mr.A    |

### StudentSubject Table

| Student | Subject |
| ------- | ------- |
| Alex    | Math    |
| Sam     | Math    |

✅ Every determinant is a key

---

# Quick Comparison Table

| Normal Form | Main Rule                           |
| ----------- | ----------------------------------- |
| 1NF         | Atomic values                       |
| 2NF         | No partial dependency               |
| 3NF         | No transitive dependency            |
| BCNF        | Determinant must be a candidate key |

---

