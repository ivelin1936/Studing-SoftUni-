-- *************** Database Basics MS SQL Exam – 22 Oct 2017 ******************
-- **************************** Report Service ********************************

-- Section 1. DDL (30 pts) ----------------------------------------------------
-- 1.Table design -------------------------------------------------------------

CREATE DATABASE `report_service`;
USE `report_service`;

CREATE TABLE `users` (
    `id` INT(11) UNSIGNED PRIMARY KEY UNIQUE,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(50) NOT NULL,
    `name` VARCHAR(50),
    `gender` VARCHAR(1),
    `birthdate` DATETIME,
    `age` INT(11) UNSIGNED,
    `email` VARCHAR(50) NOT NULL
);

CREATE TABLE `departments` (
    `id` INT(11) UNSIGNED PRIMARY KEY UNIQUE,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `employees` (
    `id` INT(11) UNSIGNED PRIMARY KEY UNIQUE,
    `first_name` VARCHAR(25),
    `last_name` VARCHAR(25),
    `gender` VARCHAR(1),
    `birthdate` DATETIME,
    `age` INT(11) UNSIGNED,
    `department_id` INT(11) UNSIGNED NOT NULL,
    CONSTRAINT fk_employees_departments FOREIGN KEY (`department_id`)
        REFERENCES `departments` (`id`)
);

CREATE TABLE `categories` (
    `id` INT(11) UNSIGNED PRIMARY KEY UNIQUE,
    `name` VARCHAR(50) NOT NULL,
    `department_id` INT(11) UNSIGNED,
    CONSTRAINT fk_categories_departments FOREIGN KEY (`department_id`)
        REFERENCES `departments` (`id`)
);

CREATE TABLE `status` (
    `id` INT(11) UNSIGNED PRIMARY KEY UNIQUE,
    `label` VARCHAR(30) NOT NULL
);

CREATE TABLE `reports` (
    `id` INT(11) UNSIGNED PRIMARY KEY UNIQUE,
    `category_id` INT(11) UNSIGNED NOT NULL,
    `status_id` INT(11) UNSIGNED NOT NULL,
    `open_date` DATETIME NOT NULL,
    `close_date` DATETIME,
    `description` VARCHAR(200),
    `user_id` INT(11) UNSIGNED NOT NULL,
    `employee_id` INT(11) UNSIGNED,
    CONSTRAINT fk_reports_categories FOREIGN KEY (`category_id`)
        REFERENCES `categories` (`id`),
    CONSTRAINT fk_reports_status FOREIGN KEY (`status_id`)
        REFERENCES `status` (`id`),
    CONSTRAINT fk_reports_users FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`),
    CONSTRAINT fk_reports_employees FOREIGN KEY (`employee_id`)
        REFERENCES `employees` (`id`)
);

-- Section 2. DML (10 pts) ----------------------------------------------------
-- ----------------------------------------------------------------------------

SET foreign_key_checks = 0;
INSERT INTO Departments(Id, Name)
VALUES(1, 'Infrastructure'), (2, 'Aged Care'), (3, 'Legal'), (4, 'Emergency'), (5, 'Roads Maintenance'), (6, 'Animals Care'), (7, 'Forestry Office'), (8, 'Property Management'), (9, 'Event Management'), (10, 'Environment');

INSERT INTO Categories(Id,
                       Name,
                       Department_id)
VALUES(1, 'Snow Removal', 5), (2, 'Recycling', 10), (3, 'Water/Air Pollution', 10), (4, 'Streetlight', 1), (5, 'Illegal Construction', 8), (6, 'Sports Events', 9), (7, 'Homeless Elders', 2), (8, 'Disabled People', 2), (9, 'Art Events', 9), (10, 'Animal in Danger', 6), (11, 'Destroyed Home', 4), (12, 'Street animal', 6), (13, 'Music Events', 9), (14, 'Dangerous Building', 8), (15, 'Traffic Lights', 5), (16, 'Potholes', 5), (17, 'Green Areas', 7), (18, 'Dangerous Trees', 7);

INSERT INTO Status(Id,
                   Label)
VALUES(1, 'waiting'), (2, 'in progress'), (3, 'completed'), (4, 'blocked');

INSERT INTO Employees(Id,
                      First_name,
                      Last_name,
                      Gender,
                      Birthdate,
                      Department_Id)
VALUES(1, 'Marlo', 'O''Malley', 'M', '1958-9-21', 1), (2, 'Nolan', 'Meneyer', 'M', '1961-4-29', 6), (3, 'Tarah', 'McWaters', 'F', '1954-5-22', 9), (4, 'Bernetta', 'Bigley', 'F', '1979-10-18', 2), (5, 'Gregory', 'Stithe', 'M', '1952-6-25', 5), (6, 'Bord', 'Hambleton', 'M', NULL, 8), (7, 'Humphrey', 'Tamblyn', 'M', '1941-3-26', 6), (8, 'Dinah', 'Zini', 'F', '1950-9-8', 10), (9, 'Eustace', 'Sharpling', 'M', '1956-10-29', 1), (10, 'Shannon', 'Partridge', 'M', '1952-2-14', 1), (11, 'Nancey', 'Heintsch', 'F', '1967-8-20', 3), (12, 'Niki', 'Stranaghan', 'M', '1969-11-26', 9), (13, 'Dick', 'Wentworth', 'M', '1983-4-29', 4), (14, 'Ives', 'McNeigh', 'M', '1952-11-15', 1), (15, 'Leonardo', 'Shopcott', 'M', '1939-1-15', 6), (16, 'Howard', 'Lovelady', 'M', '1969-6-6', 5), (17, 'Bron', 'Ledur', 'M', '1996-11-26', 10), (18, 'Adelind', 'Benns', 'F', '1935-11-23', 10), (19, 'Imogen', 'Burnup', 'F', '1952-5-8', 3), (20, 'Eldon', 'Gaze', 'M', '1947-8-24', 5), (21, 'Patsy', 'McLenahan', 'F', NULL, 10), (22, 'Jeane', 'Salisbury', 'F', '1967-9-13', 5), (23, 'Tiena', 'Ritchard', 'F', '1985-4-18', 3), (24, 'Hakim', 'Guilaem', 'M', '1963-4-9', 9), (25, 'Corny', 'Pickthall', 'M', '1979-12-18', 2), (26, 'Tam', 'Kornel', 'M', '1995-10-3', 9), (27, 'Abby', 'Brettoner', 'F', '1992-4-16', 9), (28, 'Galven', 'Moston', 'M', '1945-3-20', 5), (29, 'Stefa', 'Jakubovski', 'F', '1947-1-10', 2), (30, 'Hewet', 'Juschke', 'M', '1997-12-26', 7);

INSERT INTO Users(Id,
                  Username,
                  Name,
			   		Password,
                  Gender,
                  BirthDate,
		        		Age,
                  Email)
VALUES
(1, 'ealpine0', 'Erhart Alpine', 'b8eYD1a7R44', 'F', '1949-07-07', 68, 'ealpine0@squarespace.com'),
(2, 'awight1', 'Anitra Wight', 'hbHhuwBSxqeo', 'F', '1943-05-31', 74, 'awight1@artisteer.com'),
(3, 'fmacane2', 'Faustine MacAne', 'nhpbS3h2rfR', 'M', '1944-11-19', 73, 'fmacane2@is.gd'),
(4, 'fdenrico3', 'Florette D''Enrico', '0iMw1JpVk4', 'F', '1977-10-26', 40, 'fdenrico3@va.gov'),
(5, 'lrow4', 'Lindsey Row', 'neGBinke', 'F', '1934-01-16', 83, 'lrow4@netscape.com'),
(6, 'dfinicj5', 'NULL', '2GDReU', 'F', '1993-05-20', 24, 'shynson5@ihg.com'),
(7, 'llankham6', 'Lishe Lankham', 'ygNzd2f', 'F', '1951-11-05', 66, 'llankham6@histats.com'),
(8, 'tmartensen7', 'Tawnya Martensen', 'KyFw9oA', 'M', '1980-11-21', 37, 'tmartensen7@cornell.edu'),
(9, 'mgobbett8', 'Maud Gobbett', 'anv5ba2pvM', 'F', '1958-10-29', 59, 'mgobbett8@dmoz.org'),
(10, 'vinglese9', 'Veronique Inglese', 'ZCJp511W', 'M', '1962-02-16', 55, 'vinglese9@g.co'),
(11, 'mburdikina', 'Maggi Burdikin', 'MjXufd', 'F', '1986-04-23', 31, 'mburdikina@boston.com'),
(12, 'varkwrightb', 'Vanni Arkwright', 'sWKjjlWE', 'M', '1964-02-29', 53, '6varkwrightb@ucoz.com'),
(13, '5omarkwelleyc', 'Odette Markwelley', 'UfUE4pE', 'F', '1998-05-23', 19, 'omarkwelleyc@alibaba.com'),
(14, 'dpennid', 'Dorene Penni', 'rIBnJ77b', 'F', '1959-09-07', 58, 'dpennid@arizona.edu'),
(15, 'wkicke', 'Wileen Kick', '7bZQ3gntC', 'M', '1962-09-20', 55, 'wkicke@disqus.com'),
(16, '1qiskowf', 'Quent Iskow', 'DCDiR6YTf8', 'F', '1958-12-18', 59, 'qiskowf@opensource.org'),
(17, 'bkaasg', 'Brig Kaas', 'D1oonIJDX3G', 'M', '1996-07-13', 21, 'bkaasg@g.co'),
(18, 'gdiaperh', 'Germaine Diaper', 'YM05Ya6Xpo7', 'F', '1939-05-26', 78, 'gdiaperh@nsw.gov.au'),
(19, '1eallibertoni', 'Emlynn Alliberton', 's8XQ0d7iv', 'F', '1972-07-29', 45, 'eallibertoni@slashdot.org'),
(20, 'jgreggorj', 'Jocko Greggor', 'H1J1AbJW4BEB', 'M', '1981-04-22', 36, 'jgreggorj@whitehouse.gov');

INSERT INTO Reports(Id,
                    Category_Id,
                    Status_Id,
                    Open_Date,
                    Close_Date,
                    Description,
                    User_Id,
                    Employee_Id)
VALUES
(1, 1, 4, '2017-04-13', NULL, 'Stuck Road on Str.14', 14, 5),
(2, 2, 3, '2015-09-05', '2015-09-17', '366 kg plastic for recycling.', 10, NULL),
(3, 1, 2, '2015-01-03', NULL, 'Stuck Road on Str.29', 4, 22),
(4, 11, 4, '2015-12-06', NULL, 'Burned facade on Str.183', 7, NULL),
(5, 4, 4, '2015-11-17', NULL, 'Fallen streetlight columns on Str.40', 3, NULL),
(6, 18, 1, '2015-09-07', NULL, 'Fallen Tree on the road on Str.26', 14, 30),
(7, 2, 2, '2017-09-07', NULL, 'High grass in Park Riversqaure', 10, 8),
(8, 18, 3, '2016-04-23', '2016-05-01', 'Fallen Tree on the road on Str.26', 11, NULL),
(9, 10, 4, '2014-12-17', NULL, 'Stuck Road on Str.65', 1, 15),
(10, 2, 4, '2015-08-23', NULL, '399 kg plastic for recycling.', 12, 17),
(11, 4, 3, '2017-07-03', '2017-07-06', 'Fallen streetlight columns on Str.41', 19, 14),
(12, 10, 3, '2016-07-20', '2016-08-13', 'Burned facade on Str.793', 7, 7),
(13, 1, 2, '2016-01-26', NULL, '246 kg plastic for recycling.', 16, 20),
(14, 12, 1, '2016-06-09', NULL, 'Aggressive monkey corner of Str.36 and Str.92', 20, NULL),
(15, 1, 4, '2015-06-20', NULL, 'Stuck Road on Str.133', 17, NULL),
(16, 6, 1, '2015-10-09', NULL, 'Stuck Road on Str.66', 18, 24),
(17, 11, 4, '2015-08-26', NULL, 'Burned facade on Str.560', 14, NULL),
(18, 6, 4, '2014-10-24', NULL, '86 kg plastic for recycling.', 10, 24),
(19, 11, 4, '2016-01-14', NULL, '39 kg plastic for recycling.', 6, 13),
(20, 16, 1, '2016-07-02', NULL, 'Gigantic crater ?n Str.48', 3, NULL),
(21, 2, 4, '2015-03-31', NULL, 'High grass in Park Riversqaure', 14, 17),
(22, 14, 1, '2016-05-15', NULL, 'Falling bricks on Str.17', 14, NULL),
(23, 2, 3, '2017-07-24', '2017-07-27', 'Stuck Road on Str.8', 16, 18),
(24, 1, 3, '2015-05-23', '2016-05-19', 'Stuck Road on Str.123', 13, 16),
(25, 17, 1, '2016-01-11', NULL, '162 kg plastic for recycling.', 19, 30),
(26, 10, 2, '2016-11-10', '2016-11-20', 'Parked car on green area on the sidewalk of Str.74', 20, 15),
(27, 9, 2, '2014-12-17', NULL, 'Art exhibition on July 24', 8, NULL),
(28, 2, 4, '2017-07-12', NULL, 'Stuck Road on Str.13', 2, 18),
(29, 14, 3, '2015-09-25', '2016-10-12', 'Falling bricks on Str.38', 12, 13),
(30, 3, 4, '2016-08-02', NULL, 'Extreme increase in nitrogen dioxide at Litchfield', 4, NULL),
(31, 4, 4, '2017-09-12', NULL, 'Fallen streetlight columns on Str.14', 1, 1),
(32, 6, 3, '2015-06-08', '2015-06-13', 'Sky Run competition on September 8', 19, 12),
(33, 16, 2, '2015-11-17', NULL, 'Gigantic crater ?n Str.19', 1, NULL),
(34, 2, 4, '2017-07-10', NULL, 'Glass Bottles for recycling on Str.105', 5, NULL),
(35, 2, 1, '2017-02-06', NULL, 'Glass Bottles for recycling on Str.28', 5, NULL),
(36, 4, 2, '2016-01-01', NULL, 'Fallen streetlight columns on Str.15', 18, NULL),
(37, 5, 1, '2017-08-28', NULL, 'Glass Bottles for recycling on Str.150', 13, 17),
(38, 7, 2, '2016-02-27', NULL, 'Lonely child on corner of Str.3 and Str.30', 16, NULL),
(39, 9, 1, '2016-02-11', NULL, 'Glass Bottles for recycling on Str.116', 10, NULL),
(40, 7, 1, '2015-11-05', NULL, 'Lonely child on corner of Str.1 and Str.19', 7, 25);
SET foreign_key_checks = 1;

-- 2.Insert -------------------------------------------------------------------
-- ----------------------------------------------------------------------------

INSERT INTO `employees`(`first_name`, `last_name`, `gender`, `birthdate`, `department_id`)
VALUE 
('Marlo', 'O\'Malley', 'M', '1958-09-21', 1),
('Niki', 'Stanaghan', 'F', '1969-11-26', 4),
('Ayrton', 'Senna', 'M', '1960-03-21',  9),
('Ronnie',	'Peterson',	'M', '1944-02-14', 9),
('Giovanna', 'Amati', 'F', '1959-07-20', 5);

INSERT INTO `reports`(`category_id`, `status_id`, `open_date`, `close_date`, `description`, `user_id`, `employee_id`)
VALUE 
(1,	1, '2017-04-13', NULL, 'Stuck Road on Str.133', 6, 2),
(6,	3, '2015-09-05', '2015-12-06', 'Charity trail running', 3, 5),
(14, 2, '2015-09-07', NULL, 'Falling bricks on Str.58', 5, 2),
(4,	3, '2017-07-03', '2017-07-06', 'Cut off streetlight on Str.11', 1, 1);

-- 3.Update -------------------------------------------------------------------
-- ----------------------------------------------------------------------------

UPDATE `reports` AS r 
SET 
    r.status_id = 2
WHERE
    r.status_id = 1 AND r.category_id = 4;

-- 4.Delete -------------------------------------------------------------------
-- ----------------------------------------------------------------------------

DELETE FROM `reports` 
WHERE
    `reports`.`status_id` = 4;

-- Section 3. Querying (40 pts) -----------------------------------------------
-- ----------------------------------------------------------------------------

/* You need to start with a fresh dataset, 
   so recreate your DB and import the sample data again */

-- 5.Users by Age -------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    u.username, u.age
FROM
    `users` AS u
ORDER BY u.age ASC , u.username DESC;

-- 6.Unassigned Reports -------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    r.description, r.open_date
FROM
    `reports` AS r
WHERE
    r.employee_id IS NULL
ORDER BY r.open_date ASC , r.description;

-- 7.Employees & Reports ------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    e.first_name,
    e.last_name,
    r.description,
    DATE_FORMAT(r.open_date, '%Y-%m-%d') AS 'open_date'
FROM
    `employees` AS e
        INNER JOIN
    `reports` AS r ON r.employee_id = e.id
WHERE
    r.employee_id IS NOT NULL
ORDER BY e.id ASC , open_date ASC , r.id ASC;

-- 8.Most reported Category ---------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.name AS 'category_name',
    COUNT(r.category_id) AS 'reports_number'
FROM
    `categories` AS c
        INNER JOIN
    `reports` AS r ON r.category_id = c.id
GROUP BY c.id
ORDER BY reports_number ASC , category_name ASC;

-- 9.Employees in Category ----------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.name AS 'category_name', COUNT(e.id) AS 'employees_number'
FROM
    `categories` AS c
        INNER JOIN
    `departments` AS d ON d.id = c.department_id
        INNER JOIN
    `employees` AS e ON e.department_id = d.id
GROUP BY c.name
ORDER BY category_name ASC;

-- 10.Birthday Report ---------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT DISTINCT
    c.name AS 'category_name'
FROM
    `reports` AS r
        INNER JOIN
    `categories` AS c ON c.id = r.category_id
        INNER JOIN
    `users` AS u ON u.id = r.user_id
WHERE
    MONTH(r.open_date) = MONTH(u.birthdate)
        AND DAY(r.open_date) = DAY(u.birthdate)
ORDER BY category_name ASC;

-- 11.Users per Employee  -----------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    CONCAT_WS(' ', e.first_name, e.last_name) AS 'name',
    COUNT(r.user_id) AS 'users_count'
FROM
    `employees` AS e
        LEFT JOIN
    `reports` AS r ON r.employee_id = e.id
GROUP BY e.id
ORDER BY `users_count` DESC , `name` ASC;

-- 12.Emergency Patrol --------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    r.open_date, r.description, u.email AS 'reporter_email'
FROM
    `reports` AS r
        INNER JOIN
    `categories` AS c ON c.id = r.category_id
        INNER JOIN
    `departments` AS d ON d.id = c.department_id
        INNER JOIN
    `users` AS u ON u.id = r.user_id
WHERE
    r.close_date IS NULL
        AND CHAR_LENGTH(r.description) > 20
        AND d.name IN ('Infrastructure' , 'Emergency', 'Roads Maintenance')
ORDER BY r.open_date ASC , `reporter_email` ASC , r.id ASC;

-- 13.Numbers Coincidence -----------------------------------------------------
-- ----------------------------------------------------------------------------



























