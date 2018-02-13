************************************************************************
********************** Lab: Subqueries and JOINs ***********************
************************************************************************

USE `soft_uni_database`;

-- 1.Managers ----------------------------------------------------------
-- ---------------------------------------------------------------------

SELECT 
    e.employee_id,
    CONCAT_WS(' ', e.first_name, e.last_name) AS 'full_name',
    d.department_id,
    d.name AS 'department_name'
FROM
    `employees` AS e
        RIGHT JOIN
    `departments` AS d ON d.manager_id = e.employee_id
ORDER BY e.employee_id
LIMIT 5;

-- 2.Towns Adresses ----------------------------------------------------
-- ---------------------------------------------------------------------

SELECT 
    t.town_id, t.name AS 'town_name', a.address_text
FROM
    `towns` AS t
        JOIN
    `addresses` AS a ON t.town_id = a.town_id
WHERE
    t.name IN ('San Francisco' , 'Sofia', 'Carnation')
ORDER BY t.town_id , a.address_id;

-- 3.Employees Without Managers ----------------------------------------
-- ---------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name,
    e.last_name,
    e.department_id,
    e.salary
FROM
    `employees` AS e
WHERE
    manager_id IS NULL;

-- 4.Higher Salary ----------------------------------------------------
-- --------------------------------------------------------------------

SELECT 
    COUNT(e.employee_id)
FROM
    `employees` AS e
WHERE
    e.salary > (SELECT 
            AVG(em.salary)
        FROM
            `employees` AS em);

-- --------------------------------------------------------------------


































