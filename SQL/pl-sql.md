# Welcome to PL/SQL (Procedure Language/ Structured Query Language) 101

[Harvard CS50’s Intro to Databases with SQL – Full University Course](https://youtu.be/WXk7yDqsKxs?si=jNQ9EL4GOCcMlqRu)

## Introduction
PL/SQL (Procedural Language/SQL) is Oracle’s extension of SQL that adds procedural features like loops, conditions, and error handling. It allows developers to write powerful programs that combine SQL queries with logic to control how data is processed. With PL/SQL, complex operations, calculations, and error handling can be performed directly within the Oracle database, making data manipulation more efficient and flexible.

PL/SQL allows developers to:

- Execute SQL queries and DML commands inside procedural blocks.
- Define variables and perform complex calculations.
- Create reusable program units, such as procedures, functions, and triggers.
- Handle exceptions, ensuring the program runs smoothly even when errors occur.

## Key Features of PL/SQL
PL/SQL brings the benefits of procedural programming to the relational database world. Some of the most important features of PL/SQL include:

- Block Structure: PL/SQL can execute a number of queries in one block using single command.
- Procedural Constructs: One can create a PL/SQL unit such as procedures, functions, packages, triggers, and types, which are stored in the database for reuse by applications.
- Error Handling: PL/SQL provides a feature to handle the exception which occurs in PL/SQL block known as exception handling block.
- Reusable Code: Create stored procedures, functions, triggers, and packages, which can be executed repeatedly.
Performance: Reduces network traffic by executing multiple SQL statements within a single block



## Triggers
PL/SQL triggers are block structures and predefined programs invoked automatically when some event occurs. They are stored in the database and invoked repeatedly in a particular scenario. It can either be enabled or disabled.

Syntax:
```pgsql
CREATE TRIGGER name
AFTER/BEFORE INSERT/DELETE/UPDATE ON table_name
FOR EACH ROW
BEGIN
    ...
END;
```

Example:
Here, we have taken an example of automatically adding the transaction status of a entity sold/bought from a `collection` table. Hence whenever an item is inserted or deleted, it's staus will be added to the `transactions` table. 

We can access the inserted or deleted rows' values through `NEW` and `OLD` keywords. 

```pgsql
CREATE TRIGGER sell
BEFORE DELETE ON collections
FOR EACH ROW
BEGIN
    INSERT INTO transactions (title, action)
    VALUES (OLD."title", 'sold')
END;


CREATE TRIGGER buy
AFTER INSERT ON collections
FOR EACH ROW
BEGIN
    INSERT INTO transactions (title, action)
    VALUES (NEW."title", 'bought')
END;
```

## Soft Deletion

