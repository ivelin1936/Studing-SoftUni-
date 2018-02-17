************************************************************************
*********** Exercises: Functions, Triggers and Transactions ************
************************************************************************

-- Part I – Queries for SoftUni Database -----------------------------

USE `soft_uni_database`;

-- 1.Employees with Salary Above 35000 -------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
SELECT 
    e.first_name, e.last_name
FROM
    `employees` AS e
WHERE
    e.salary > 35000
ORDER BY e.first_name ASC , e.last_name ASC , e.employee_id ASC;
END $$
DELIMITER ; 

-- 2.Employees with Salary Above Number ------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(accept_number  DOUBLE)
BEGIN
SELECT 
    e.first_name, e.last_name
FROM
    `employees` AS e
WHERE
    e.salary >= accept_number
ORDER BY e.first_name, e.last_name, e.employee_id ASC;
END $$
DELIMITER ; 

-- 3.Town Names Starting With ----------------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(parameter VARCHAR(20))
BEGIN
SELECT 
    t.name
FROM
    `towns` AS t
WHERE
    t.name = INSERT(t.name, 1, CHAR_LENGTH(parameter), parameter) 
ORDER BY t.name ASC;
END $$
DELIMITER ;

-- 4.Employees from Town ---------------------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(20))
BEGIN
SELECT 
    e.first_name, e.last_name
FROM
    `employees` AS e
        JOIN
    `addresses` AS a ON a.address_id = e.address_id
        JOIN
    `towns` AS t ON t.town_id = a.town_id
WHERE
    t.name = town_name
ORDER BY e.first_name ASC , e.last_name ASC , e.employee_id ASC;	
END $$
DELIMITER ; 

-- 5.Salary Level Function -------------------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary DOUBLE)
RETURNS VARCHAR(20)
BEGIN
DECLARE salary_level VARCHAR(20);
CASE 
	WHEN salary < 30000 THEN SET salary_level := 'Low';
	WHEN salary <= 50000 THEN SET salary_level := 'Average';
	WHEN salary > 50000 THEN SET salary_level := 'High';
END CASE;
RETURN salary_level;
END
DELIMITER ;

-- 6.Employees by Salary Level ---------------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(20))
BEGIN
SELECT 
    e.first_name, e.last_name
FROM
    `employees` AS e
WHERE
    UFN_GET_SALARY_LEVEL(e.salary) LIKE salary_level
ORDER BY e.first_name DESC , e.last_name DESC;
END $$
DELIMITER ; 

-- 6.Employees by Salary Level for JUDGE -----------------------------

DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(20))
BEGIN
SELECT e.first_name, e.last_name
FROM `employees` AS e
WHERE
    CASE
	WHEN salary_level LIKE 'Low' THEN e.salary < 30000
	WHEN salary_level LIKE 'Average' THEN e.salary >= 30000 AND e.salary <= 50000
    WHEN salary_level LIKE 'High' THEN e.salary > 50000
	END
ORDER BY e.first_name DESC , e.last_name DESC;
END $$
DELIMITER ; 

-- 7.Define Function -------------------------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS BIT
BEGIN
RETURN word REGEXP (CONCAT('^[',set_of_letters,']+$'));
END  
DELIMITER ;

-- PART II – Queries for Bank Database -------------------------------

USE `bank`;

-- 8.Find Full Name --------------------------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
SELECT 
    CONCAT_WS(' ', ac.first_name, ac.last_name) AS 'full_name'
FROM
    `account_holders` AS ac
ORDER BY full_name ASC , ac.id ASC; 
END $$
DELIMITER ; 

-- 9.People with Balance Higher Than ---------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE FUNCTION unf_get_total_money_of_all_accounts_by_id(input_id INT)
RETURNS DOUBLE
BEGIN
DECLARE total_money DOUBLE;
SET total_money := ( SELECT 
    SUM(a.balance)
FROM
    `account_holders` AS ac
        JOIN
    `accounts` AS a ON a.account_holder_id = ac.id
WHERE
    ac.id = input_id
GROUP BY ac.id );
RETURN total_money;
END
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(parameter DOUBLE)
BEGIN
SELECT 
    ac.first_name, ac.last_name
FROM
    `account_holders` AS ac
        JOIN
    `accounts` AS a ON a.account_holder_id = ac.id
WHERE
    UNF_GET_TOTAL_MONEY_OF_ALL_ACCOUNTS_BY_ID(ac.id) > parameter
ORDER BY ac.first_name ASC , ac.last_name , ac.id ASC;
END $$
DELIMITER ; 

-- 9.People with Balance Higher Than - FRO JUDGE ---------------------

DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(parameter DOUBLE)
BEGIN
SELECT 
    ac.first_name, ac.last_name
FROM
    `account_holders` AS ac
        JOIN
    `accounts` AS a ON ac.id = a.account_holder_id
GROUP BY ac.id
HAVING SUM(a.balance) > parameter
ORDER BY a.id ASC, ac.first_name;
END $$
DELIMITER ; 

-- 10.Future Value Function ------------------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(initial_sums DECIMAL(19,4), interest_rate DOUBLE, years INT)
 RETURNS DOUBLE(19,4)
BEGIN
 DECLARE output DOUBLE(19,4);
 SET output := initial_sums * POW((1 + interest_rate), years);
 RETURN output;
END $$
DELIMITER ;

-- 10.Future Value Function - With WHILE cicle -----------------------

DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(initial_sums DECIMAL(19,4), interest_rate DOUBLE, years INT)
 RETURNS DOUBLE
BEGIN
 DECLARE output DOUBLE;
 SET output := initial_sums;
 
	while_name: WHILE years <> 0 DO
	   SET output := output + (output * interest_rate);
	   SET years := years - 1;
	END WHILE while_name;
    
 RETURN ROUND(output, 2);
END $$
DELIMITER ;

-- 11.Calculating Interest -------------------------------------------
-- -------------------------------------------------------------------
-- NEED TEST IN JUDGE
DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(account_id INT, interest_rate DECIMAL(19,4))
BEGIN
SELECT 
    ah.id AS 'account_id',
    ah.first_name,
    ah.last_name,
    a.balance AS 'current_balance',
    UFN_CALCULATE_FUTURE_VALUE(a.balance, interest_rate, 5) AS 'balance_in_5_years'
FROM
    `account_holders` AS ah
        INNER JOIN
    `accounts` AS a ON ah.id = a.account_holder_id
WHERE
    ah.id = account_id
GROUP BY ah.id;
END
DELIMITER ; 

-- 12.Deposit Money --------------------------------------------------
-- -------------------------------------------------------------------
-- NEED TEST IN JUDGE
DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DOUBLE)
BEGIN
	START TRANSACTION;
	IF money_amount < 0 
		THEN ROLLBACK;
	ELSE 
		UPDATE `accounts` AS a
		SET a.balance = a.balance + money_amount
		WHERE a.id = account_id;
        COMMIT;
    END IF;
END $$
DELIMITER ; 

-- 13.Withdraw Money -------------------------------------------------
-- -------------------------------------------------------------------
-- NEED TEST IN JUDGE

DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DOUBLE)
BEGIN
START TRANSACTION;
	IF money_amount < 0 
		THEN ROLLBACK;
	ELSEIF (SELECT 
				a.balance
			FROM
				`accounts` AS a
			WHERE
				a.id = account_id) < money_amount
		THEN ROLLBACK;
	ELSE 
		UPDATE `accounts` AS a
		SET a.balance = a.balance - money_amount
		WHERE a.id = account_id;
        COMMIT;
	END IF;
END $$
DELIMITER ;

-- 14.Money Transfer -------------------------------------------------
-- -------------------------------------------------------------------
-- NEED TEST IN JUDGE
DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DOUBLE)
BEGIN
START TRANSACTION;
	CASE
		WHEN (SELECT 
				a.id
			FROM
				`accounts` AS a
			WHERE
				a.id = from_account_id) IS NULL THEN ROLLBACK;
		WHEN (SELECT 
				a.id
			FROM
				`accounts` AS a
			WHERE
				a.id = to_account_id) IS NULL THEN ROLLBACK;
		WHEN amount < 0 THEN ROLLBACK;
		WHEN (SELECT 
				a.balance
			FROM
				`accounts` AS a
			WHERE
				a.id = from_account_id) < amount THEN ROLLBACK;
        ELSE 
			CALL usp_withdraw_money(from_account_id, amount);
			CALL usp_deposit_money(to_account_id, amount);
            COMMIT;
    END CASE;
END $$
DELIMITER ; 

-- 15.Log Accounts Trigger -------------------------------------------
-- -------------------------------------------------------------------
-- NEED TEST IN JUDGE
CREATE TABLE logs(
log_id INT PRIMARY KEY AUTO_INCREMENT, 
account_id INT, 
old_sum DOUBLE, 
new_sum DOUBLE
); 

DELIMITER $$
CREATE TRIGGER tr_update_accounts_amount
AFTER UPDATE 
ON accounts
FOR EACH ROW
BEGIN
INSERT INTO logs(`account_id`,
				 `old_sum`,
				 `new_sum`)
VALUES (OLD.`id`,
		OLD.`balance`,
        NEW.`balance`);
END $$
DELIMITER ;

-- 16.Emails Trigger -------------------------------------------------
-- -------------------------------------------------------------------
-- NEED TEST IN JUDGE
CREATE TABLE notification_emails(
id INT PRIMARY KEY AUTO_INCREMENT, 
recipient INT, 
subject VARCHAR(100), 
body TEXT
); 

DELIMITER $$
CREATE TRIGGER tr_create_new_email_on_new_record_in_logs
AFTER INSERT 
ON `logs`
FOR EACH ROW
BEGIN
INSERT INTO notification_emails(`recipient`, `subject`, `body`)
VALUES (
	NEW.`account_id`,
    CONCAT('Balance change for account: ', NEW.`account_id`),
    CONCAT('On ', 
		DATE_FORMAT(NOW(), '%b %e %Y'),
        ' at ', 
        TIME_FORMAT(NOW(), '%r'),
        ' your balance was changed from ', 
        NEW.`old_sum`, 
        ' to ', 
        NEW.`new_sum`, 
        '.')
        );
END $$
DELIMITER ;

-- -------------------------------------------------------------------






























