************************************************************************
************** Lab: Functions, Triggers and Transactions ***************
************************************************************************

-- 1.Count Employees by Town -----------------------------------------
-- -------------------------------------------------------------------

USE soft_uni_database;

DELIMITER $$
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(20))
RETURNS DOUBLE 
BEGIN
DECLARE e_count DOUBLE;
SET e_count := (SELECT 
    COUNT(e.employee_id)
FROM
    `employees` AS e
        INNER JOIN
    `addresses` AS a ON a.address_id = e.address_id
        INNER JOIN
    `towns` AS t ON t.town_id = a.town_id
WHERE
    t.name = town_name);
RETURN e_count;
END
DECLARE e_count DOUBLE;
SET e_count := (SELECT 
    COUNT(e.employee_id)
FROM
    `employees` AS e
        INNER JOIN
    `addresses` AS a ON a.address_id = e.address_id
        INNER JOIN
    `towns` AS t ON t.town_id = a.town_id
WHERE
    t.name = town_name);
RETURN e_count;
END

-- 2.Employees Promotion -----------------------------------------
-- -------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_raise_salaries(department_name VAR) 


-- 2.Employees Promotion --------------------------------------------
-- ------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(50))
BEGIN
UPDATE `employees` AS e
        JOIN
		`departments` AS d 
		ON d.department_id = e.department_id 
SET 
    e.salary = e.salary  * 1.05
WHERE
    d.name = department_name;
END $$
DELIMITER ;

-- 3.Employees Promotion By ID --------------------------------------
-- ------------------------------------------------------------------

DELIMITER %%
CREATE PROCEDURE usp_raise_salary_by_id(id INT) 
BEGIN
START TRANSACTION;
IF ((SELECT COUNT(e.employee_id = id)
		FROM `employees` AS e
		WHERE e.employee_id = id) != 1)
    THEN 
		ROLLBACK;
    ELSE
		UPDATE `employees` AS e
		SET 
			e.salary = e.salary * 1.05
		WHERE
			e.employee_id = id;
	END IF;
END %%
DELIMITER ;

-- 4.Triggered ------------------------------------------------------
-- ------------------------------------------------------------------

CREATE TABLE deleted_employees (
employee_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50),
last_name VARCHAR(50),
middle_name VARCHAR(50),
job_title VARCHAR(50),
department_id INT,
salary DOUBLE(10,4),
deleted_date DATE
); 

DELIMITER $$
CREATE TRIGGER tr_deleted_employees
AFTER DELETE 
ON employees
FOR EACH ROW

BEGIN
INSERT INTO deleted_employees(`first_name`,
							`last_name`,
                            `middle_name`,
                            `job_title`, 
                            `department_id`, 
                            `salary`,
                            `deleted_date`)
VALUES (OLD.`first_name`,
		OLD.`last_name`,
		OLD.`middle_name`,
		OLD.`job_title`, 
		OLD.`department_id`, 
		OLD.`salary`,
        DATE(NOW())
);
END $$
DELIMITER ;

-- ------------------------------------------------------------------
































