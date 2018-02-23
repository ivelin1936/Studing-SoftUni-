-- ************** Database Basics (MySQL) Exam Preparation II  ****************
-- **************************** The Nerd Herd *********************************

CREATE DATABASE `the_nerd_herd`;
USE `the_nerd_herd`;

-- Section I: Data Definition Language (DDL) – 30 pts -------------------------
-- 01.Table Design ------------------------------------------------------------

CREATE TABLE `locations` (
    `id` INT(11) PRIMARY KEY UNIQUE,
    `latitude` FLOAT,
    `longitude` FLOAT
);

CREATE TABLE `credentials` (
    `id` INT(11) PRIMARY KEY UNIQUE,
    `email` VARCHAR(30),
    `password` VARCHAR(20)
);

CREATE TABLE `users` (
    `id` INT(11) PRIMARY KEY UNIQUE,
    `nickname` VARCHAR(25),
    `gender` CHAR(1),
    `age` INT(11),
    `location_id` INT(11),
    `credential_id` INT(11) UNIQUE,
    CONSTRAINT fk_users_locations FOREIGN KEY (`location_id`)
        REFERENCES `locations` (`id`),
    CONSTRAINT fk_users_credentials FOREIGN KEY (`credential_id`)
        REFERENCES `credentials` (`id`)
);

CREATE TABLE `chats` (
    `id` INT(11) PRIMARY KEY UNIQUE,
    `title` VARCHAR(32),
    `start_date` DATE,
    `is_active` BIT(1)
);

CREATE TABLE `messages` (
    `id` INT(11) PRIMARY KEY UNIQUE,
    `content` VARCHAR(200),
    `sent_on` DATE,
    `chat_id` INT(11),
    `user_id` INT(11),
    CONSTRAINT fk_messages_chats FOREIGN KEY (`chat_id`)
        REFERENCES `chats` (`id`),
    CONSTRAINT fk_messages_users FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
);

CREATE TABLE `users_chats` (
    `user_id` INT(11),
    `chat_id` INT(11),
    CONSTRAINT pk_user_chat PRIMARY KEY (`user_id` , `chat_id`),
    CONSTRAINT fk_uch_users FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`),
    CONSTRAINT fk_uch_chats FOREIGN KEY (`chat_id`)
        REFERENCES `chats` (`id`)
);

-- Section 2: Data Manipulation Language (DML) – 30 pts -----------------------

/* You will have to fill the data before you can manipulate it. 
   The employers have provided a data.sql file which contains the 
   data you must import in your database */

-- 02. Insert -----------------------------------------------------------------
-- ----------------------------------------------------------------------------

INSERT INTO `messages`(`content`, `sent_on`, `chat_id`, `user_id`)
SELECT 
	CONCAT_WS('-', u.age, u.gender, l.latitude, l.longitude) AS 'content',
    '2016-12-15' AS 'sent_on',
    (CASE u.gender
		WHEN 'F'
			THEN CEIL(SQRT(u.age * 2))
		ELSE 
			CEIL(POW(u.age / 18, 3))
	END) AS 'chat_id'
    ,u.id AS 'user_id'
FROM `users` AS u
JOIN `locations` AS l ON l.id = u.location_id
WHERE u.id BETWEEN 10 AND 20;

-- 03. Update -----------------------------------------------------------------
-- ----------------------------------------------------------------------------

UPDATE `chats` AS c
        JOIN
    `messages` AS m ON m.chat_id = c.id 
SET 
    c.start_date = m.sent_on
WHERE
    m.sent_on < c.start_date;

-- 04. Delete -----------------------------------------------------------------
-- ----------------------------------------------------------------------------

DELETE l FROM `locations` AS l
        LEFT JOIN
    `users` AS u ON u.location_id = l.id 
WHERE
    u.id IS NULL;

-- Section 3: Querying – 100 pts ----------------------------------------------
-- 05. Age Range --------------------------------------------------------------
-- ----------------------------------------------------------------------------












































































