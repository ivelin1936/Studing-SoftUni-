************************************************************************
******************* Exercises: Subqueries and JOINs ********************
************************************************************************

USE `soft_uni_database`;

-- 1.Employee Address --------------------------------------------------
-- ---------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.job_title,
    e.address_id,
    a.address_text AS 'address_text'
FROM
    `employees` AS e
        JOIN
    `addresses` AS a ON a.address_id = e.address_id
ORDER BY a.address_id
LIMIT 5;

-- 2.Addresses with Towns ----------------------------------------------
-- ---------------------------------------------------------------------

SELECT 
    e.first_name, e.last_name, t.name AS 'town', a.address_text
FROM
    `employees` AS e
        JOIN
    `addresses` AS a ON a.address_id = e.address_id
        JOIN
    `towns` AS t ON t.town_id = a.town_id
ORDER BY e.first_name ASC , e.last_name ASC
LIMIT 5;

-- 3.Sales Employee ----------------------------------------------------
-- ---------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name,
    e.last_name,
    d.name AS 'department_name'
FROM
    `employees` AS e
        JOIN
    `departments` AS d ON d.department_id = e.department_id
WHERE
    d.name LIKE 'Sales'
ORDER BY e.employee_id DESC;

-- 4.Employee Departments --------------------------------------------
-- -------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name,
    e.salary,
    d.name AS 'department_name'
FROM
    `employees` AS e
        JOIN
    `departments` AS d ON d.department_id = e.department_id
WHERE
    e.salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;

-- 5.Employees Without Project ---------------------------------------
-- -------------------------------------------------------------------

SELECT 
    e.employee_id, e.first_name
FROM
    `employees` AS e
        LEFT JOIN
    `employees_projects` AS ep ON ep.employee_id = e.employee_id
        LEFT JOIN
    `projects` AS p ON p.project_id = ep.project_id
WHERE
    ep.project_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

-- 6.Employees Hired After -------------------------------------------
-- -------------------------------------------------------------------

SELECT 
    e.first_name,
    e.last_name,
    e.hire_date,
    d.name AS 'dept_name'
FROM
    `employees` AS e
        JOIN
    `departments` AS d ON d.department_id = e.department_id
WHERE
    DATE_FORMAT(e.hire_date, '%Y-%m-%d') > '1999-01-01'
        AND d.name IN ('Sales' , 'Finance')
ORDER BY e.hire_date ASC;

-- 7.Employees with Project ------------------------------------------
-- -------------------------------------------------------------------

SELECT 
    e.employee_id, e.first_name, p.name AS 'project_name'
FROM
    `employees` AS e
        JOIN
    `employees_projects` AS ep ON ep.employee_id = e.employee_id
        JOIN
    `projects` AS p ON p.project_id = ep.project_id
WHERE
    DATE_FORMAT(p.start_date, '%Y-%m-%d') > '2002-08-13'
        AND p.end_date IS NULL
ORDER BY e.first_name ASC , p.name ASC
LIMIT 5;

-- 8.Employee 24 -----------------------------------------------------
-- -------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name,
    (CASE
        WHEN YEAR(p.start_date) >= 2005 THEN NULL
        WHEN YEAR(p.start_date) < 2005 THEN p.name
    END) AS 'project_name'
FROM
    `employees` AS e
        LEFT JOIN
    `employees_projects` AS emp ON emp.employee_id = e.employee_id
        LEFT JOIN
    `projects` AS p ON p.project_id = emp.project_id
WHERE
    e.employee_id = 24
ORDER BY p.name ASC;

-- 9.Employee Manager ------------------------------------------------
-- -------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name,
    e.manager_id,
    empl.first_name AS 'manager_name'
FROM
    `employees` AS e
        JOIN
    `employees` AS empl ON e.manager_id = empl.employee_id
WHERE
    e.manager_id = 3 OR e.manager_id = 7
ORDER BY e.first_name ASC;

-- 10.Employee Summary -----------------------------------------------
-- -------------------------------------------------------------------

SELECT 
    e.employee_id,
    CONCAT_WS(' ', e.first_name, e.last_name) AS 'employee_name',
    CONCAT_WS(' ', emp.first_name, emp.last_name) AS 'manager_name',
    d.name AS 'department_name'
FROM
    `employees` AS e
        JOIN
    `employees` AS emp ON e.manager_id = emp.employee_id
        JOIN
    `departments` AS d ON d.department_id = e.department_id
WHERE
    e.manager_id IS NOT NULL
ORDER BY e.employee_id ASC
LIMIT 5;

-- 11.Min Average Salary ---------------------------------------------
-- -------------------------------------------------------------------

CREATE VIEW `departments_avg_salary` AS
    SELECT 
        e.department_id, AVG(e.salary) AS 'avg_salary'
    FROM
        `employees` AS e
    WHERE
        e.department_id IS NOT NULL
    GROUP BY e.department_id
    ORDER BY e.employee_id ASC;

SELECT MIN(das.avg_salary)
FROM `departments_avg_salary` AS das;

-- 12.Highest Peaks in Bulgaria --------------------------------------
-- -------------------------------------------------------------------

USE `geography`;

SELECT 
    c.country_code, m.mountain_range, p.peak_name, p.elevation
FROM
    `countries` AS c
        JOIN
    `mountains_countries` AS mc ON mc.country_code = c.country_code
        JOIN
    `mountains` AS m ON m.id = mc.mountain_id
        JOIN
    `peaks` AS p ON p.mountain_id = m.id
WHERE
    c.country_name = 'Bulgaria'
        AND p.elevation > 2835
ORDER BY p.elevation DESC;

-- 13.Count Mountain Ranges ------------------------------------------
-- -------------------------------------------------------------------

SELECT 
    c.country_code, COUNT(m.mountain_range) AS 'mountain_range'
FROM
    `countries` AS c
        JOIN
    `mountains_countries` AS mc ON mc.country_code = c.country_code
        JOIN
    `mountains` AS m ON m.id = mc.mountain_id
WHERE
    c.country_name IN ('United States' , 'Russia', 'Bulgaria')
GROUP BY c.country_code
ORDER BY `mountain_range` DESC;

-- 14.Countries with Rivers ------------------------------------------
-- -------------------------------------------------------------------

SELECT 
    c.country_name, r.river_name
FROM
    `countries` AS c
        JOIN
    `continents` AS cont ON cont.continent_code = c.continent_code
        LEFT JOIN
    `countries_rivers` AS cr ON cr.country_code = c.country_code
        LEFT JOIN
    `rivers` AS r ON r.id = cr.river_id
WHERE
    cont.continent_name = 'Africa'
ORDER BY c.country_name ASC
LIMIT 5;

-- 15.*Continents and Currencies -------------------------------------
-- -------------------------------------------------------------------



-- 16.Countries without any Mountains --------------------------------
-- -------------------------------------------------------------------

SELECT 
    COUNT(c.country_code) AS 'country_count'
FROM
    `countries` AS c
	LEFT JOIN
    `mountains_countries` AS mc ON mc.country_code = c.country_code
WHERE
    mc.mountain_id IS NULL;

-- 17.Highest Peak and Longest River by Country ----------------------
-- -------------------------------------------------------------------

SELECT 
    c.country_name,
    MAX(p.elevation) AS 'highest_peak_elevation',
    MAX(r.length) AS 'longest_river_length'
FROM
    `countries` AS c
        LEFT JOIN
    `mountains_countries` AS mc ON mc.country_code = c.country_code
        LEFT JOIN
    `mountains` AS m ON m.id = mc.mountain_id
        LEFT JOIN
    `peaks` AS p ON p.mountain_id = m.id
        LEFT JOIN
    `countries_rivers` AS cr ON cr.country_code = c.country_code
        LEFT JOIN
    `rivers` AS r ON r.id = cr.river_id
GROUP BY c.country_code
ORDER BY `highest_peak_elevation` DESC , `longest_river_length` DESC , c.country_name ASC
LIMIT 5;

-- -------------------------------------------------------------------























