****************************************************************
******************* Lab: Data Aggregation **********************
****************************************************************

USE `restaurant`;

-- 1.Departments Info ------------------------------------------
-- ------------------------------------------------------------- 

SELECT 
    e.department_id, COUNT(e.salary) AS `Number of employees`
FROM
    `employees` AS e
GROUP BY e.department_id
ORDER BY e.department_id , `Number of employees`;

-- 2.Average Salary --------------------------------------------
-- -------------------------------------------------------------

SELECT 
    e.department_id, ROUND(AVG(e.salary), 2) AS `Average Salary`
FROM
    `employees` AS e
GROUP BY e.department_id
ORDER BY e.department_id;

-- 3.Min Salary ------------------------------------------------
-- -------------------------------------------------------------

SELECT 
    e.department_id, ROUND(MIN(e.salary), 2) AS `Min Salary`
FROM
    `employees` AS e
GROUP BY e.department_id
HAVING MIN(e.salary) > 800;

-- 4.Appetizers Count ------------------------------------------
-- -------------------------------------------------------------

SELECT 
    COUNT(p.id)
FROM
    products AS p
WHERE
    p.category_id = 2 AND p.price > 8
GROUP BY p.category_id;

-- 5.Menu Prices -----------------------------------------------
-- -------------------------------------------------------------

SELECT 
    p.category_id,
    ROUND(AVG(p.price), 2) AS 'Average Price',
    ROUND(MIN(p.price), 2) AS 'Cheapest Product',
    ROUND(MAX(p.price), 2) AS 'Most Expensive Product'
FROM
    products AS p
GROUP BY p.category_id;



