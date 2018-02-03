***************************************************************
************** Exercises: Built-in Functions ******************
***************************************************************

-- Part I – Queries for SoftUni Database ----------------------

USE `soft_uni`;

-- 1.	Find Names of All Employees by First Name -------------
-- ------------------------------------------------------------

SELECT 
    e.first_name, e.last_name
FROM
    `employees` AS e
WHERE
    e.first_name LIKE ('Sa%')
ORDER BY e.employee_id ASC;

-- 2.	Find Names of All employees by Last Name  -------------
-- ------------------------------------------------------------

SELECT 
    e.first_name, e.last_name
FROM
    `employees` AS e
WHERE
    e.last_name LIKE ('%ei%')
ORDER BY e.employee_id ASC;

-- 3.	Find First Names of All Employees ---------------------
-- ------------------------------------------------------------
-- 50/100 TODO.....
SELECT 
    e.first_name
FROM
    `employees` AS e
WHERE
    (e.department_id = 3
        OR e.department_id = 10)
        AND 
        YEAR(e.hire_date) BETWEEN 1995 AND 2005
ORDER BY e.employee_id ASC;

-- 4.	Find All Employees Except Engineers -------------------
-- ------------------------------------------------------------

SELECT 
    first_name, last_name
FROM
    `employees`
WHERE
    job_title NOT LIKE ('%engineer%')
ORDER BY employee_id ASC;

-- 5.	Find Towns with Name Length ---------------------------
-- ------------------------------------------------------------

SELECT 
    t.name
FROM
    `towns` AS t
WHERE
    CHAR_LENGTH(t.name) = 5
        OR CHAR_LENGTH(t.name) = 6
ORDER BY t.name ASC;

-- 6. Find Towns Starting With --------------------------------
-- ------------------------------------------------------------

SELECT 
    t.town_id, t.name
FROM
    `towns` AS t
WHERE
    t.name LIKE ('M%') 
		OR t.name LIKE ('K%')
        OR t.name LIKE ('B%')
        OR t.name LIKE ('E%')
ORDER BY t.name ASC;

-- OR -->>>

SELECT 
    t.town_id, t.name
FROM
    `towns` AS t
WHERE
    t.name REGEXP "^[M|K|B|E]{1}"
ORDER BY t.name ASC; 

-- OR -->>>

SELECT 
    t.town_id, t.name
FROM
    `towns` AS t
WHERE
	SUBSTRING(t.name, 1, 1) IN ('M', 'K', 'B', 'E')
ORDER BY t.name ASC; 

-- OR -->>>

SELECT 
    t.town_id, t.name
FROM
    `towns` AS t
WHERE
	LEFT(t.name, 1) IN ('M', 'K', 'B', 'E')
ORDER BY t.name ASC; 

-- 7.Find Towns Not Starting With -----------------------------
-- ------------------------------------------------------------

SELECT 
    t.town_id, t.name
FROM
    `towns` AS t
WHERE
	SUBSTRING(t.name, 1, 1) NOT IN ('R', 'B', 'D')
ORDER BY t.name ASC; 

-- 8.Create View Employees Hired After 2000 Year --------------
-- ------------------------------------------------------------

CREATE VIEW v_employees_hired_after_2000 AS
    SELECT 
        e.first_name, e.last_name
    FROM
        `employees` AS e
    WHERE
        YEAR(e.hire_date) > 2000;

-- 9.Length of Last Name --------------------------------------
-- ------------------------------------------------------------

SELECT 
    first_name, last_name
FROM
    `employees`
WHERE
    CHAR_LENGTH(last_name) = 5;

-- Part II – Queries for Geography Database  ------------------

USE `geography`;

-- 10.Countries Holding ‘A’ 3 or More Times -------------------
-- ------------------------------------------------------------

SELECT 
    c.country_name, c.iso_code
FROM
    `countries` AS c
WHERE
    c.country_name LIKE ('%a%a%a%')
ORDER BY c.iso_code ASC;

-- 11.	 Mix of Peak and River Names --------------------------
-- ------------------------------------------------------------

SELECT 
    p.peak_name, 
    r.river_name,
    LOWER(CONCAT(p.peak_name, SUBSTRING(r.river_name, 2))) AS `mix`
FROM
    `peaks` AS p,
    `rivers` AS r
WHERE
    RIGHT(p.peak_name, 1) = LEFT(r.river_name, 1)
    ORDER BY mix ASC;

-- 11.	 Mix of Peak and River Names --------------------------

USE `diablo`;

-- 12.	Games from 2011 and 2012 year -------------------------
-- ------------------------------------------------------------

SELECT 
    g.name, DATE_FORMAT(g.start, '%Y-%m-%d') AS `start`
FROM
    `games` AS g
WHERE
    YEAR(g.start) = 2011
        OR YEAR(g.start) = 2012
ORDER BY g.start ASC , g.name ASC
LIMIT 50;

-- 13.User Email Providers ------------------------------------
-- ------------------------------------------------------------

SELECT 
    u.user_name,
    SUBSTRING_INDEX(u.email, '@', - 1) AS `Email Provider`
FROM
    `users` AS u
ORDER BY `Email Provider` ASC , u.user_name ASC;

-- 14.Get Users with IP Address Like Pattern ------------------
-- ------------------------------------------------------------

SELECT 
    u.user_name, u.ip_address
FROM
    `users` AS u
WHERE
    u.ip_address LIKE ('___.1%.%.___')
ORDER BY u.user_name ASC;

-- 15.Show All Games with Duration and Part of the Day --------
-- ------------------------------------------------------------

SELECT 
    g.name AS game,
    CASE
        WHEN EXTRACT(HOUR FROM g.start) < 12 THEN 'Morning'
        WHEN EXTRACT(HOUR FROM g.start) < 18 THEN 'Afternoon'
        WHEN EXTRACT(HOUR FROM g.start) < 24 THEN 'Evening'
    END AS `Part of the Day`,
    CASE
        WHEN g.duration <= 3 THEN 'Extra Short'
        WHEN g.duration <= 6 THEN 'Short'
        WHEN g.duration <= 10 THEN 'Long'
        ELSE 'Extra Long'
    END AS `Duration`
FROM
    `games` AS g;

-- Part IV – Date Functions Queries ---------------------------

USE `orders`;

-- 16.Orders Table --------------------------------------------
-- ------------------------------------------------------------

SELECT 
    o.product_name,
    o.order_date,
    DATE_ADD(o.order_date, INTERVAL 3 DAY) AS 'pay_due',
    DATE_ADD(o.order_date, INTERVAL 1 MONTH) AS 'deliver_due'
FROM
    `orders` AS o

-- ------------------------------------------------------------








