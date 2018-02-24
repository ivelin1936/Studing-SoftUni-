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

SELECT 
    u.nickname, u.gender, u.age
FROM
    `users` AS u
WHERE
    u.age BETWEEN 22 AND 37
ORDER BY u.id ASC;

-- 06. Messages ---------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    m.content, m.sent_on
FROM
    `messages` AS m
WHERE
    m.sent_on > '2014-05-12'
        AND m.content LIKE '%just%'
ORDER BY m.id DESC;

-- 07. Chats ------------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.title, c.is_active
FROM
    `chats` AS c
WHERE
    (c.is_active = FALSE AND CHAR_LENGTH(c.title) < 5)
        OR SUBSTRING(c.title, 3, 2) = 'tl'
ORDER BY c.title DESC;

-- 08. Chat Messages ----------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.id, c.title, m.id
FROM
    `chats` AS c
        JOIN
    `messages` AS m ON m.chat_id = c.id
WHERE
    m.sent_on < '2012-03-26'
        AND RIGHT(c.title, 1) = 'x'
ORDER BY c.id ASC , m.id ASC;

-- 09. Message Count ----------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.id, COUNT(m.id) AS 'total_messages'
FROM
    `chats` AS c
        JOIN
    `messages` AS m ON m.chat_id = c.id
WHERE
    m.id < 90
GROUP BY c.id
ORDER BY total_messages DESC , c.id ASC
LIMIT 5;

-- 10. Credentials ------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    u.nickname, c.email, c.password
FROM
    `users` AS u
        JOIN
    `credentials` AS c ON c.id = u.credential_id
WHERE
    c.email LIKE '%co.uk'
ORDER BY c.email ASC;

-- 11. Locationss -------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    u.id, u.nickname, u.age
FROM
    `users` AS u
WHERE
    u.location_id IS NULL;

-- 12. Left Users -------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    m.id, m.chat_id, m.user_id
FROM
    `messages` AS m
        LEFT JOIN
    `users_chats` AS uc ON uc.chat_id = m.chat_id
        AND uc.user_id = m.user_id
WHERE
    m.chat_id = 17 AND uc.user_id IS NULL
ORDER BY m.id DESC;

-- 13. Users in Bulgaria ------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    u.nickname, c.title, l.latitude, l.longitude
FROM
    `users` AS u
        JOIN
    `locations` AS l ON l.id = u.location_id
        JOIN
    `users_chats` AS uc ON uc.user_id = u.id
        JOIN
    `chats` AS c ON c.id = uc.chat_id
WHERE
    (l.latitude BETWEEN 41.139999 AND 44.129999)
        AND (l.longitude BETWEEN 22.209999 AND 28.359999)
ORDER BY c.title ASC;

-- 14. Last Chat --------------------------------------------------------------
-- ----------------------------------------------------------------------------

SELECT 
    c.title, m.content
FROM
    `chats` AS c
        LEFT JOIN
    `messages` AS m ON m.chat_id = c.id
WHERE
    c.start_date = (SELECT 
						start_date
					FROM
						`chats`
					ORDER BY start_date DESC
					LIMIT 1)
ORDER BY m.sent_on ASC , m.id ASC;

-- Section 4: Programmability – 40 pts ----------------------------------------
-- 15. Radians ----------------------------------------------------------------
-- ----------------------------------------------------------------------------

 DELIMITER $$
CREATE FUNCTION udf_get_radians(degrees DOUBLE)
 RETURNS FLOAT
BEGIN
 DECLARE radians FLOAT;
 SET radians := (degrees * PI()) / 180;
 RETURN radians;
END $$
DELIMITER ;

-- 16. Change Password --------------------------------------------------------
-- ----------------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE udp_change_password(email VARCHAR(30), new_password VARCHAR(20))
BEGIN
START TRANSACTION;
    UPDATE `credentials` AS c
    SET c.password = new_password
    WHERE c.email = email;
    
    IF email NOT IN (SELECT `email` FROM `credentials`)
		THEN
			ROLLBACK;
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'The email does\'t exist!';
	ELSE COMMIT;
    END IF;
END $$
DELIMITER ;

-- 17. Send Message -----------------------------------------------------------
-- ----------------------------------------------------------------------------

DELIMITER $$
CREATE PROCEDURE udp_send_message(user_id INT, chat_id INT, content VARCHAR(200))
BEGIN
START TRANSACTION;
    INSERT INTO `messages`(`content`, `sent_on`, `chat_id`, `user_id`)
    VALUES (content, '2016-12-15', chat_id, user_id);
    
    IF user_id NOT IN (SELECT `user_id` FROM `users_chats`)
		THEN
			ROLLBACK;
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'There is no chat with that user!';
	ELSE COMMIT;
    END IF;
END $$
DELIMITER ;

-- 18. Log Messages -----------------------------------------------------------
-- ----------------------------------------------------------------------------

CREATE TABLE `messages_log`(
	`id` INT(11) PRIMARY KEY,
    `content` VARCHAR(200),
    `sent_on` DATE,
    `chat_id` INT(11),
    `user_id` INT(11)
);

DELIMITER $$
CREATE TRIGGER tr_deleted_messages
BEFORE DELETE ON `messages`
FOR EACH ROW
BEGIN
	INSERT INTO `messages_log`(`id`, `content`, `sent_on`, `chat_id`, `user_id`)
    VALUES (OLD.id, OLD.content, OLD.sent_on, OLD.chat_id, OLD.user_id);
END $$
DELIMITER ;

-- Section 5: Bonus – 10 pts --------------------------------------------------
-- 19. Delete Users -----------------------------------------------------------
-- ----------------------------------------------------------------------------

DELIMITER $$
CREATE TRIGGER tr_deleted_users
BEFORE DELETE ON `users`
FOR EACH ROW
BEGIN
    DELETE FROM `messages` WHERE messages.user_id = OLD.id;
	DELETE FROM `users_chats` WHERE users_chats.user_id = OLD.id;
	DELETE FROM `messages_log` WHERE messages_log.user_id = OLD.id;
END $$
DELIMITER ;

-- ----------------------------------------------------------------------------









