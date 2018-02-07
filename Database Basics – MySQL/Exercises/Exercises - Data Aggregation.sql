******************************************************************
**************** Exercises: Data Aggregation *********************
******************************************************************

-- Get familiar with the gringotts database. ---------------------

USE `gringotts`;

-- 1. Records’ Count ---------------------------------------------
-- ---------------------------------------------------------------

SELECT 
    COUNT(wd.id) AS 'count'
FROM
    `wizzard_deposits` AS wd;

-- 2.Longest Magic Wand ------------------------------------------
-- ---------------------------------------------------------------

SELECT 
    MAX(wd.magic_wand_size) AS 'longest_magic_wand'
FROM
    `wizzard_deposits` AS wd;

-- 3.Longest Magic Wand per Deposit Groups -----------------------
-- ---------------------------------------------------------------

SELECT 
    wd.deposit_group,
    MAX(wd.magic_wand_size) AS 'longest_magic_wand'
FROM
    `wizzard_deposits` AS wd
GROUP BY wd.deposit_group
ORDER BY `longest_magic_wand` ASC , wd.deposit_group ASC;

-- 4.Smallest Deposit Group per Magic Wand Size* -----------------
-- ---------------------------------------------------------------

SELECT 
    wd.deposit_group
FROM
    `wizzard_deposits` AS wd
HAVING MIN(wd.magic_wand_size);

-- 5.Deposits Sum ------------------------------------------------
-- ---------------------------------------------------------------

SELECT 
    wd.deposit_group, SUM(wd.deposit_amount) AS 'total_sum'
FROM
    `wizzard_deposits` AS wd
GROUP BY wd.deposit_group
ORDER BY `total_sum` ASC;

-- 6.Deposits Sum for Ollivander family --------------------------
-- ---------------------------------------------------------------

SELECT 
    wd.deposit_group, SUM(wd.deposit_amount) AS 'total_sum'
FROM
    `wizzard_deposits` AS wd
WHERE
    wd.magic_wand_creator LIKE ('Ollivander family')
GROUP BY wd.deposit_group
ORDER BY wd.deposit_group ASC;

-- 7.Deposits Filter ---------------------------------------------
-- ---------------------------------------------------------------

SELECT 
    wd.deposit_group, SUM(wd.deposit_amount) AS 'total_sum'
FROM
    `wizzard_deposits` AS wd
WHERE
    wd.magic_wand_creator LIKE ('Ollivander family')
GROUP BY wd.deposit_group
HAVING total_sum < 150000
ORDER BY total_sum DESC;

-- 8.Deposit charge ----------------------------------------------
-- ---------------------------------------------------------------

SELECT 
    wd.deposit_group,
    wd.magic_wand_creator,
    MIN(wd.deposit_charge) AS 'min_deposit_charge'
FROM
    `wizzard_deposits` AS wd
GROUP BY wd.deposit_group , wd.magic_wand_creator
ORDER BY wd.magic_wand_creator ASC , wd.deposit_group ASC;

-- 9. Age Groups -------------------------------------------------
-- ---------------------------------------------------------------

SELECT 
    CASE
        WHEN wd.age <= 10 THEN '[0-10]'
        WHEN wd.age <= 20 THEN '[11-20]'
        WHEN wd.age <= 30 THEN '[21-30]'
        WHEN wd.age <= 40 THEN '[31-40]'
        WHEN wd.age <= 50 THEN '[41-50]'
        WHEN wd.age <= 60 THEN '[51-60]'
        WHEN wd.age > 60 THEN '[61+]'
    END AS `age_group`,
    COUNT(wd.id) AS 'wizard_count'
FROM
    `wizzard_deposits` AS wd
GROUP BY age_group;

-- 10. First Letter ----------------------------------------------
-- ---------------------------------------------------------------

SELECT DISTINCT
    (LEFT(wd.first_name, 1)) AS 'first_letter'
FROM
    `wizzard_deposits` AS wd
WHERE
    wd.deposit_group LIKE ('Troll Chest')
GROUP BY first_name
ORDER BY first_letter ASC;

-- 11.Average Interest  ------------------------------------------
-- ---------------------------------------------------------------

SELECT 
    wd.deposit_group,
    wd.is_deposit_expired,
    AVG(wd.deposit_interest) AS 'average_interest'
FROM
    `wizzard_deposits` AS wd
WHERE
    wd.deposit_start_date > '1985-01-01'
GROUP BY wd.deposit_group , wd.is_deposit_expired
ORDER BY wd.deposit_group DESC , wd.is_deposit_expired ASC;

-- 12.Rich Wizard, Poor Wizard* ----------------------------------
-- ---------------------------------------------------------------

SELECT 
    SUM(asd.difference) AS sum_difference
FROM
    (SELECT 
        (wd1.deposit_amount - wd2.deposit_amount) AS difference
    FROM
        wizzard_deposits AS wd1, wizzard_deposits AS wd2
    WHERE
        wd2.id - wd1.id = 1) AS asd;

-- 13.Employees Minimum Salaries ---------------------------------
-- ---------------------------------------------------------------

USE `soft_uni_database`;
-- ---------------------------------------------------------------

SELECT 
    e.department_id, MIN(e.salary) AS 'minimum_salary'
FROM
    `employees` AS e
WHERE
     e.department_id IN (2, 5, 7)
        AND DATE(e.hire_date) > '2000-01-01'
GROUP BY e.department_id;

-- 14.Employees Average Salaries ---------------------------------
-- ---------------------------------------------------------------

CREATE TABLE `high_paid_employees` AS SELECT * FROM
    `employees` AS e
WHERE
    e.salary > 30000;

DELETE FROM `high_paid_employees` 
WHERE
    manager_id = 42; 

UPDATE `high_paid_employees` AS hpe 
SET 
    hpe.salary = hpe.salary + 5000
WHERE
    hpe.department_id = 1;

SELECT 
    hpe.department_id, AVG(hpe.salary) AS 'avg_salary'
FROM
    `high_paid_employees` AS hpe
GROUP BY hpe.department_id
ORDER BY hpe.department_id ASC;

-- 15.Employees Maximum Salaries ---------------------------------
-- ---------------------------------------------------------------

SELECT 
    e.department_id, MAX(e.salary) AS 'max_salary'
FROM
    `employees` AS e
GROUP BY e.department_id
HAVING MAX(e.salary) < 30000
    OR MAX(e.salary) > 70000
ORDER BY e.department_id ASC;

-- 16.Employees Count Salaries -----------------------------------
-- ---------------------------------------------------------------

SELECT 
    COUNT(e.salary)
FROM
    `employees` AS e
WHERE
    e.manager_id IS NULL;

-- 17.3rd Highest Salary* ----------------------------------------
-- ---------------------------------------------------------------

SELECT 
    emp.department_id, MAX(emp.salary) AS third_highest_salary
FROM
    employees AS emp
        JOIN
    (SELECT 
        e.department_id AS department_id,
            MAX(e.salary) AS max_salary
    FROM
        employees AS e
    JOIN (SELECT 
        e.department_id AS department_id,
            MAX(e.salary) AS max_salary
    FROM
        employees AS e
    GROUP BY e.department_id) AS first_max_salary ON e.department_id = first_max_salary.department_id
    WHERE
        e.salary < first_max_salary.max_salary
    GROUP BY e.department_id) AS second_max_salary ON emp.department_id = second_max_salary.department_id
WHERE
    emp.salary < second_max_salary.max_salary
GROUP BY emp.department_id
ORDER BY emp.department_id;

-- 18.Salary Challenge** -----------------------------------------
-- ---------------------------------------------------------------

CREATE VIEW `respect_departments` AS
    SELECT 
        emp.department_id AS department_id,
        AVG(emp.salary) AS avg_salary
    FROM
        `employees` AS emp
    GROUP BY emp.department_id;

SELECT 
    e.first_name,
    e.last_name,
    e.department_id
FROM
    `employees` AS e,
    `respect_departments` AS rd
WHERE
    e.department_id = rd.department_id
        AND e.salary >= rd.avg_salary
ORDER BY e.department_id ASC
LIMIT 10;

-- 19.Departments Total Salaries ---------------------------------
-- ---------------------------------------------------------------

SELECT 
    e.department_id, SUM(e.salary) AS 'total_salary'
FROM
    `employees` AS e
GROUP BY e.department_id
ORDER BY e.department_id ASC;




























