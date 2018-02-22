-- **************************** DATABASE EXAM  ********************************
-- ************************ Konare Trade Bank(KTB) ****************************

CREATE DATABASE `konare_trade_bank`;
USE `konare_trade_bank`;

-- bank database

-- Section 1. DDL -------------------------------------------------------------
-- ----------------------------------------------------------------------------

CREATE TABLE deposit_types (
    deposit_type_id INT PRIMARY KEY,
    name VARCHAR(20)
);
 
CREATE TABLE deposits (
    deposit_id INT PRIMARY KEY AUTO_INCREMENT,
    amount DECIMAL(10 , 2 ),
    start_date DATE,
    end_date DATE,
    deposit_type_id INT,
    customer_id INT,
    FOREIGN KEY (deposit_type_id)
        REFERENCES deposit_types (deposit_type_id),
    FOREIGN KEY (customer_id)
        REFERENCES customers (customer_id)
);
 
CREATE TABLE employees_deposits (
    employee_id INT,
    deposit_id INT,
    PRIMARY KEY (employee_id , deposit_id),
    FOREIGN KEY (employee_id)
        REFERENCES employees (employee_id),
    FOREIGN KEY (deposit_id)
        REFERENCES deposits (deposit_id)
);
 
CREATE TABLE credit_history (
    credit_history_id INT PRIMARY KEY,
    mark CHAR(1),
    start_date DATE,
    end_date DATE,
    customer_id INT,
    FOREIGN KEY (customer_id)
        REFERENCES customers (customer_id)
);
 
CREATE TABLE payments (
    payment_id INT PRIMARY KEY,
    `date` DATE,
    amount DECIMAL(10 , 2 ),
    loan_id INT,
    FOREIGN KEY (loan_id)
        REFERENCES loans (loan_id)
);
 
CREATE TABLE users (
    user_id INT PRIMARY KEY,
    user_name VARCHAR(20),
    `password` VARCHAR(20),
    customer_id INT UNIQUE,
    FOREIGN KEY (customer_id)
        REFERENCES customers (customer_id)
);
 
ALTER TABLE employees
ADD manager_id INT,
ADD FOREIGN KEY (manager_id) REFERENCES employees(employee_id); 

-- Section 2. DML -------------------------------------------------------------
-- 1.	Insert data into the following tables  --------------------------------

INSERT INTO `employees_deposits`
VALUES 
(15, 4),
(20, 15),
(8,	7),
(4,	8),
(3,	13),
(3,	8),
(4,	10),
(10, 1),
(13, 4),
(14, 9);

INSERT INTO `deposit_types`
VALUES
(1,	'Time Deposit'),
(2,	'Call Deposit'),
(3,	'Free Deposit');

INSERT INTO `deposits`(`amount`,
						`start_date`,
						`end_date`,
						`deposit_type_id`,
                        `customer_id`)
SELECT 
	(CASE 
		WHEN c.date_of_birth > '1980-01-01' THEN 1000
	ELSE 1500
    END
    +
    CASE
		WHEN c.gender = 'M' THEN 100
        ELSE 200
        END) AS 'amount',
	DATE(NOW()) AS 'start_date',
    NULL AS 'end_date',
    (CASE
		WHEN c.customer_id > 15 THEN 3
		WHEN c.customer_id % 2 = 0 THEN 2
        ELSE 1
    END) AS 'deposit_type_id',
    c.customer_id AS 'customer_id'
FROM `customers` AS c 
WHERE c.customer_id < 20;

-- 2.	Update Employees  -----------------------------------------------------
-- ----------------------------------------------------------------------------

UPDATE `employees` 
SET 
    manager_id = (CASE
        WHEN employee_id BETWEEN 2 AND 10 THEN 1
        WHEN employee_id BETWEEN 12 AND 20 THEN 11
        WHEN employee_id BETWEEN 22 AND 30 THEN 21
        WHEN employee_id IN (11 , 21) THEN 1
    END);

-- 3.	Delete Records  -------------------------------------------------------
-- ----------------------------------------------------------------------------

DELETE FROM `employees_deposits` 
WHERE
    deposit_id = 9 OR employee_id = 3;

-- Section 3. Querying  -------------------------------------------------------

-- For this section put your queries in judge 
-- and use SQL Server prepare DB and run queries.

-- 1.	Employees’ Salary -----------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    e.employee_id, e.hire_date, e.salary, e.branch_id
FROM
    `employees` AS e
WHERE
    e.salary > 2000
        AND e.hire_date > '2009-06-15';

-- 2.Customer Age -------------------------------------------------------------
-- ----------------------------------------------------------------------------
-- TODO...
SELECT 
    c.first_name,
    c.date_of_birth,
    FLOOR(TIMESTAMPDIFF(DAY,c.date_of_birth,'2016-10-01') / 360) AS 'age'
FROM
    `customers` AS c
WHERE
    FLOOR(TIMESTAMPDIFF(DAY,c.date_of_birth,'2016-10-01') / 360)
		BETWEEN 40 AND 50;

-- 3.Customer City ------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    c.gender,
    ci.city_name
FROM
    `customers` AS c
        JOIN
    `cities` AS ci ON ci.city_id = c.city_id
WHERE
    (c.last_name LIKE 'Bu%' OR c.first_name LIKE '%a')
        AND CHAR_LENGTH(ci.city_name) >= 8
ORDER BY c.customer_id ASC;

-- 4.Employee Accounts --------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    e.employee_id, e.first_name, a.account_number
FROM
    `employees` AS e
        JOIN
    `employees_accounts` AS ac ON ac.employee_id = e.employee_id
        JOIN
    `accounts` AS a ON a.account_id = ac.account_id
WHERE
    YEAR(a.start_date) > 2012
ORDER BY e.first_name DESC
LIMIT 5;

-- 5.Employee Cities ----------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.city_name,
    b.name,
    COUNT(e.employee_id) AS 'employees_count'
FROM
    `cities` AS c
        JOIN
    `branches` AS b ON b.city_id = c.city_id
        JOIN
    `employees` AS e ON e.branch_id = b.branch_id
WHERE
    c.city_id NOT IN (4 , 5)
GROUP BY c.city_name , b.name
HAVING employees_count >= 3;

-- 6.Loan Statistics ----------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    SUM(lo.amount) AS 'total_loan_amount',
    MAX(lo.interest) AS 'max_interest',
    MIN(e.salary) AS 'min_employee_salary'
FROM
    `employees` AS e
        JOIN
    `employees_loans` AS el ON el.employee_id = e.employee_id
        JOIN
    `loans` AS lo ON lo.loan_id = el.loan_id;
    
-- 7.Unite People -------------------------------------------------------------
-- ----------------------------------------------------------------------------
/* 
SELECT *
FROM 
(SELECT e.first_name, c.city_name
FROM `employees` AS e
JOIN `branches` AS b ON b.branch_id = e.branch_id
JOIN `cities` AS c ON c.city_id = b.city_id
LIMIT 3) AS `table1`,
(SELECT cu.first_name, ci.city_name
FROM `customers` AS cu
JOIN `cities` AS ci ON ci.city_id = cu.city_id
LIMIT 3) AS `table2`; 
*/

-- 8.Customers without Accounts -----------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.customer_id, c.height
FROM
    `customers` AS c
WHERE
    c.customer_id NOT IN (SELECT 
            customer_id
        FROM
            accounts)
        AND (c.height BETWEEN 1.74 AND 2.04);

-- 9.Customers without Accounts -----------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.customer_id, l.amount
FROM
    customers AS c
        JOIN
    loans AS l ON l.customer_id = c.customer_id
WHERE
    l.amount > (SELECT 
            AVG(lo.amount)
        FROM
            `loans` AS lo
                JOIN
            `customers` AS cu ON cu.customer_id = lo.customer_id
        WHERE
            cu.gender LIKE 'M')
ORDER BY c.last_name ASC
LIMIT 5;

-- 10.Oldest Account ----------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.customer_id, c.first_name, a.start_date
FROM
    `customers` AS c
        JOIN
    `accounts` AS a ON a.customer_id = c.customer_id
ORDER BY a.start_date ASC
LIMIT 1;

-- Section 4. Programmability -------------------------------------------------

-- For this section put your queries in judge 
-- and use SQL Server run skeleton, run queries and check DB.

-- 1.String Joiner Function ---------------------------------------------------
-- ----------------------------------------------------------------------------

DELIMITER $$
CREATE FUNCTION udf_concat_string(str1 VARCHAR(255), str2 VARCHAR(255))
RETURNS VARCHAR(255)
BEGIN
	DECLARE reverse_str1 VARCHAR(255);
	DECLARE reverse_str2 VARCHAR(255);
    
    SET reverse_str1 := REVERSE(str1);
    SET reverse_str2 := REVERSE(str2);
RETURN CONCAT(reverse_str1, reverse_str2);
END $$
DELIMITER ;

-- 2.Unexpired Loans Procedure ------------------------------------------------
-- ----------------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_customers_with_unexpired_loans(customer_id INT)
BEGIN
SELECT 
    c.customer_id AS 'CustimerID',
    c.first_name AS 'FirstName',
    l.loan_id AS 'LoanID'
FROM
    `customers` AS c
        JOIN
    `loans` AS l ON l.customer_id = c.customer_id
WHERE
    c.customer_id = customer_id
        AND l.expiration_date IS NULL;
END
DELIMITER ; 

-- 3.Take Loan Procedure ------------------------------------------------------
-- ----------------------------------------------------------------------------
	
DELIMITER $$
CREATE PROCEDURE usp_take_loan(customer_id INT,loan_amount DECIMAL(10,2),interest DECIMAL(4,2),start_date DATE)
BEGIN
	START TRANSACTION;
		INSERT INTO `loans`(`customer_id`, `amount`, `interest`, `start_date`)
        VALUES (customer_id, loan_amount, interest, start_date);
        
	IF (loan_amount NOT BETWEEN 0.01 AND 100000) 
		THEN 
			ROLLBACK;
			SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Invalid Loan Amount.';
	ELSE
        COMMIT;
    END IF;
END $$
DELIMITER ; 

-- 4.Trigger Hire Employee ----------------------------------------------------
-- ----------------------------------------------------------------------------

DELIMITER $$
CREATE TRIGGER tr_hire_employee
AFTER INSERT
ON `employees` 
FOR EACH ROW
BEGIN
	UPDATE `employees_loans` AS el
    SET el.employee_id = NEW.employee_id
    WHERE el.employee_id + 1 = NEW.employee_id;
END $$
DELIMITER ;

-- 1.Delete Trigger -----------------------------------------------------------
-- ----------------------------------------------------------------------------

CREATE TABLE account_logs( 
account_id INT,
account_number CHAR(12) NOT NULL,
start_date DATE NOT NULL,
customer_id INT NOT NULL
);

DELIMITER $$
CREATE TRIGGER tr_account_logs
AFTER DELETE
ON `accounts` 
FOR EACH ROW
BEGIN
	INSERT INTO `account_logs`(account_id ,account_number,start_date,customer_id)
    VALUES (OLD.account_id, OLD.account_number, OLD.start_date, OLD.customer_id);
END $$
DELIMITER ;













