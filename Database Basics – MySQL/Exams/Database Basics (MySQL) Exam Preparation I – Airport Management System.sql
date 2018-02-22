-- ********* Database Basics (MySQL) Exam Preparation I – Airport  ************
-- ************************** Management System *******************************

CREATE DATABASE `airport_management_system`;
USE `airport_management_system`;

-- Section 1: Data Definition Language (DDL) – 40 pts -------------------------
-- 01.Table Design ------------------------------------------------------------

CREATE TABLE `towns` (
    `town_id` INT(11) NOT NULL PRIMARY KEY,
    `town_name` VARCHAR(30) NOT NULL
);

CREATE TABLE `airports` (
    `airport_id` INT(11) NOT NULL PRIMARY KEY,
    `airport_name` VARCHAR(50) NOT NULL,
    `town_id` INT(11) NOT NULL,
    CONSTRAINT fk_airports_towns FOREIGN KEY (`town_id`)
        REFERENCES `towns` (`town_id`)
);

CREATE TABLE `airlines` (
    `airline_id` INT(11) NOT NULL PRIMARY KEY,
    `airline_name` VARCHAR(30) NOT NULL,
    `nationality` VARCHAR(30) NOT NULL,
    `rating` INT(11) DEFAULT 0
);

CREATE TABLE `customers` (
    `customer_id` INT(11) NOT NULL PRIMARY KEY,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `gender` VARCHAR(1) CHECK ('M' OR 'F'),
    `home_town_id` INT(11) NOT NULL,
    CONSTRAINT fk_custumers_towns FOREIGN KEY (`home_town_id`)
        REFERENCES `towns` (`town_id`)
);

CREATE TABLE `flights` (
    `flight_id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `departure_time` DATETIME NOT NULL,
    `arrival_time` DATETIME NOT NULL,
    `status` VARCHAR(9) NOT NULL CHECK ('Departing' OR 'Delayed' OR 'Arrived'
        OR 'Cancelled'),
    `origin_airport_id` INT(11),
    `destination_airport_id` INT(11),
    `airline_id` INT(11) NOT NULL,
    CONSTRAINT fk_fairports_origin FOREIGN KEY (origin_airport_id)
        REFERENCES `airports` (airport_id),
    CONSTRAINT fk_fairports_destination FOREIGN KEY (destination_airport_id)
        REFERENCES `airports` (airport_id),
    CONSTRAINT fk_flights_airports_airline FOREIGN KEY (airline_id)
        REFERENCES `airlines` (airline_id)
);

CREATE TABLE `tickets` (
    `ticket_id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `price` DECIMAL(8 , 2 ) NOT NULL,
    `class` VARCHAR(6) NOT NULL CHECK ('First' OR 'Second' OR 'Third'),
    `seat` VARCHAR(5) NOT NULL,
    `customer_id` INT(11) NOT NULL,
    `flight_id` INT(11) NOT NULL,
    CONSTRAINT fk_tickets_customers FOREIGN KEY (`customer_id`)
        REFERENCES `customers` (customer_id),
    CONSTRAINT fk_tickets_flights FOREIGN KEY (`flight_id`)
        REFERENCES `flights` (flight_id)
);

-- Section 2: Data Manipulation Language (DML) – 30 pts -----------------------
-- 02.Data Insertion ----------------------------------------------------------

INSERT INTO `flights`(`departure_time`,
						`arrival_time`,
						`status`,
						`origin_airport_id`,
						`destination_airport_id`,
						`airline_id`
)
SELECT
	'2017-06-19 14:00:00' AS 'departure_time',
    '2017-06-21 11:00:00' AS 'arrival_time',
    (CASE a.airline_id % 4
		WHEN 0 THEN 'Departing'
        WHEN 1 THEN 'Delayed'
        WHEN 2 THEN 'Arrived'
        WHEN 3 THEN 'Canceled'
	END) AS 'status',
    CEIL(SQRT(CHAR_LENGTH(a.airline_name))) AS 'origin_airport_id',
    CEIL(SQRT(CHAR_LENGTH(a.nationality))) AS 'destination_airport_id',
    a.airline_id AS 'airline_id'
    FROM `airlines` AS a
    WHERE a.airline_id BETWEEN 1 AND 10;

-- 03.Update Arrived Flights --------------------------------------------------
-- ----------------------------------------------------------------------------

UPDATE `flights` 
SET 
    airline_id = 1
WHERE
    status LIKE 'Arrived';

-- 04.Update Tickets ----------------------------------------------------------
-- ----------------------------------------------------------------------------


UPDATE `airlines` AS a
        JOIN
    `flights` AS f ON f.airline_id = a.airline_id
        JOIN
    `tickets` AS t ON t.flight_id = f.flight_id 
SET 
    t.price = t.price * 1.5
WHERE
    rating = (SELECT 
            MAX(rating)
        FROM
            `airlines`);

-- Section 3: Querying – 100 pts ----------------------------------------------
-- 05.Tickets -----------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    t.ticket_id, t.price, t.class, t.seat
FROM
    `tickets` AS t
ORDER BY t.ticket_id ASC;

-- 06.Customers ---------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.customer_id,
    CONCAT_WS(' ', c.first_name, c.last_name) AS 'full_name',
    c.gender
FROM
    `customers` AS c
ORDER BY full_name ASC , c.customer_id ASC;

-- 07.Flights -----------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    f.flight_id, f.departure_time, f.arrival_time
FROM
    `flights` AS f
WHERE
    f.status LIKE 'Delayed'
ORDER BY f.flight_id ASC;

-- 08.Top 5 Airlines ----------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT DISTINCT
    a.airline_id, a.airline_name, a.nationality, a.rating
FROM
    `airlines` AS a
        JOIN
    `flights` AS f ON f.airline_id = a.airline_id
ORDER BY a.rating DESC , a.airline_id ASC
LIMIT 5;

-- 09.‘First Class’ Tickets ---------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    t.ticket_id,
    ap.airport_name AS 'destination',
    CONCAT_WS(' ', c.first_name, c.last_name) AS 'customer_name'
FROM
    `tickets` AS t
        JOIN
    `flights` AS f ON f.flight_id = t.flight_id
        JOIN
    `airports` AS ap ON ap.airport_id = f.destination_airport_id
        JOIN
    `customers` AS c ON c.customer_id = t.customer_id
WHERE
    t.price < 5000 AND t.class LIKE 'First'
ORDER BY t.ticket_id ASC;

-- 10.Home Town Customers -----------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT DISTINCT
    c.customer_id,
    CONCAT_WS(' ', c.first_name, c.last_name) AS 'full_name',
    t.town_name AS 'home_town'
FROM
    `customers` AS c
        JOIN
    `tickets` AS ti ON ti.customer_id = c.customer_id
        JOIN
    `flights` AS f ON f.flight_id = ti.flight_id
        JOIN
    `airports` AS a ON a.airport_id = f.origin_airport_id
        JOIN
    `towns` AS t ON t.town_id = a.town_id
WHERE
    c.home_town_id = a.town_id
        AND f.status LIKE 'Departing'
ORDER BY c.customer_id ASC;

-- 11.Flying Customers --------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT DISTINCT
    c.customer_id,
    CONCAT_WS(' ', c.first_name, c.last_name) AS 'full_name',
    (2016 - YEAR(c.date_of_birth)) AS 'age'
FROM
    `customers` AS c
        JOIN
    `tickets` AS t ON t.customer_id = c.customer_id
        JOIN
    `flights` AS f ON f.flight_id = t.flight_id
WHERE
    f.status LIKE 'Departing'
ORDER BY age ASC , c.customer_id ASC;

-- 12.Delayed Customers -------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.customer_id,
    CONCAT_WS(' ', c.first_name, c.last_name) AS 'full_name',
    t.price,
    a.airport_name AS 'destination'
FROM
    `customers` AS c
        JOIN
    `tickets` AS t ON t.customer_id = c.customer_id
        JOIN
    `flights` AS f ON f.flight_id = t.flight_id
        JOIN
    `airports` AS a ON a.airport_id = f.destination_airport_id
WHERE
    f.status LIKE 'Delayed'
ORDER BY t.price DESC
LIMIT 3;

-- 13.Last Departing Flights --------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    *
FROM
    (SELECT 
        f.flight_id,
            f.departure_time,
            f.arrival_time,
            a.airport_name AS 'origin',
            air.airport_name AS 'destination'
    FROM
        `flights` AS f
    JOIN `airports` AS a ON a.airport_id = f.origin_airport_id
    JOIN `airports` AS air ON air.airport_id = f.destination_airport_id
    WHERE
        f.status LIKE 'Departing'
    ORDER BY f.departure_time DESC
    LIMIT 5) AS `last_df_table`
ORDER BY last_df_table.departure_time ASC , last_df_table.flight_id ASC;

-- 14.Flying Children ---------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT DISTINCT
    c.customer_id,
    CONCAT_WS(' ', c.first_name, c.last_name) AS 'full_name',
    (2016 - YEAR(c.date_of_birth)) AS 'age'
FROM
    `customers` AS c
        JOIN
    `tickets` AS t ON t.customer_id = c.customer_id
        JOIN
    `flights` AS f ON f.flight_id = t.flight_id
WHERE
    (2016 - YEAR(c.date_of_birth)) < 21 AND f.status LIKE 'Arrived'
ORDER BY age DESC , c.customer_id ASC;

-- 15.Airports and Passengers -------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    a.airport_id,
    a.airport_name,
    COUNT(t.customer_id) AS 'passengers'
FROM
    `airports` AS a
        JOIN
    `flights` AS f ON f.origin_airport_id = a.airport_id
        JOIN
    `tickets` AS t ON t.flight_id = f.flight_id
WHERE
    f.status LIKE 'Departing'
GROUP BY a.airport_id , a.airport_name
ORDER BY a.airport_id ASC;

-- Section 4: Programmability -------------------------------------------------

CREATE TABLE customer_reviews(
	review_id INT AUTO_INCREMENT,
	review_content VARCHAR(255) NOT NULL,
	review_grade INT NOT NULL,
	airline_id INT,
	customer_id INT,
	CONSTRAINT pk_customer_reviews PRIMARY KEY(review_id),
	CONSTRAINT fk_customer_reviews_airlines FOREIGN KEY(airline_id) REFERENCES airlines(airline_id),
	CONSTRAINT fk_customer_reviews_customers FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE customer_bank_accounts(
	account_id INT AUTO_INCREMENT,
	account_number VARCHAR(10) UNIQUE NOT NULL,
	balance DECIMAL(10, 2) NOT NULL,
	customer_id INT,
	CONSTRAINT pk_customer_bank_accounts PRIMARY KEY(account_id),
	CONSTRAINT fk_customer_bank_accounts_customers FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);


-- 16.Submit Review -----------------------------------------------------------
-- ----------------------------------------------------------------------------





























