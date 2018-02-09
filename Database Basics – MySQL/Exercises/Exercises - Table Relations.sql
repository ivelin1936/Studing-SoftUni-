************************************************************************
********************* Exercises: Table Relations ***********************
************************************************************************

CREATE DATABASE `exercises_tr`;
USE `exercises_tr`;

-- 1.One-To-One Relationship -------------------------------------------
-- ---------------------------------------------------------------------

CREATE TABLE `passports` (
    passport_id INT ,
    passport_number VARCHAR(10)
);

CREATE TABLE `persons` (
    person_id INT,
    first_name VARCHAR(20),
    salary DECIMAL(10, 2),
    passport_id INT,
    CONSTRAINT pk_person PRIMARY KEY(person_id)
);

ALTER TABLE `passports`
ADD CONSTRAINT pk_passports PRIMARY KEY(passport_id);

ALTER TABLE `persons` 
MODIFY passport_id INT UNIQUE,
ADD CONSTRAINT fk_persons_passports FOREIGN KEY (passport_id)
REFERENCES `passports` (passport_id);

INSERT INTO `passports` (`passport_id`, `passport_number`)
VALUE 
	(101, 'N34FG21B'),
	(102, 'K65LO4R7'),
	(103, 'ZE657QP2');

INSERT INTO `persons` (`person_id`, `first_name`, `salary`,`passport_id`)
VALUE 
	(1, 'Roberto', 43300.00, 102),
	(2, 'Tom', 56100.00, 103),
	(3, 'Yana', 60200.00, 101);

-- 2.One-To-Many Relationship ------------------------------------------
-- ---------------------------------------------------------------------

CREATE TABLE `manufacturers` (
    manufacturer_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    established_on DATE
);

CREATE TABLE `models` (
    model_id INT PRIMARY KEY NOT NULL UNIQUE,
    name VARCHAR(20),
    manufacturer_id INT NOT NULL,
    CONSTRAINT fk_models_manufacturers FOREIGN KEY (manufacturer_id)
        REFERENCES `manufacturers` (manufacturer_id)
);

INSERT INTO `manufacturers` (`name`, `established_on`)
VALUES 
	('BMW', '1916-03-01'),
	('Tesla', '2003-01-01'),
	('Lada', '1966-05-01');

INSERT INTO `models` (`model_id`, `name`, `manufacturer_id`)
VALUES 
	(101, 'X1', 1),
	(102, 'i6', 1),
	(103, 'Model S', 2),
	(104, 'Model X', 2),
	(105, 'Model 3', 2),
	(106, 'Nova', 3);

-- 3.Many-To-Many Relationship -----------------------------------------
-- ---------------------------------------------------------------------

CREATE TABLE `students` (
    student_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(20)
);

CREATE TABLE `exams` (
    exam_id INT NOT NULL PRIMARY KEY UNIQUE,
    name VARCHAR(20)
);

CREATE TABLE `students_exams` (
    student_id INT NOT NULL,
    exam_id INT NOT NULL,
    PRIMARY KEY (student_id, exam_id),
    CONSTRAINT fk_se_students FOREIGN KEY (student_id)
        REFERENCES `students` (student_id),
    CONSTRAINT fk_se_exams FOREIGN KEY (exam_id)
        REFERENCES `exams` (exam_id)
);

INSERT INTO `students` (`name`)
VALUE 
	('Mila'), 
    ('Toni'), 
    ('Ron');

INSERT INTO `exams` (`exam_id`, `name`)
VALUE 
	(101, 'Spring MVC'), 
    (102, 'Neo4j'), 
    (103, 'Oracle 11g');

INSERT INTO `students_exams` (`student_id`, `exam_id`)
VALUE 
	(1, 101),
	(1, 102),
	(2, 101),
	(3, 103),
	(2, 102),
	(2, 103);

-- 4.Self-Referencing --------------------------------------------------
-- ---------------------------------------------------------------------

CREATE TABLE teachers (
    teacher_id INT PRIMARY KEY,
    name VARCHAR(20),
    manager_id INT
);
 
 
INSERT INTO teachers(`teacher_id`,`name`, `manager_id`)
VALUES 
	(101,'John', NULL),
	(102,'Maya', 106),
	(103,'Silvia', 106),
	(104,'Ted', 105),
	(105,'Mark', 101),
	(106,'Greta', 101);
 
 
ALTER TABLE teachers
ADD CONSTRAINT asdfgh FOREIGN KEY (manager_id)
REFERENCES teachers(teacher_id);

-- 5.Online Store Database ---------------------------------------------
-- ---------------------------------------------------------------------

CREATE TABLE `cities` (
    city_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE `customers` (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    birthday DATE,
    city_id INT,
    CONSTRAINT fk_customers_cities FOREIGN KEY (city_id)
        REFERENCES `cities` (city_id)
);

CREATE TABLE `orders` (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id)
        REFERENCES `customers` (customer_id)
);

CREATE TABLE `item_types` (
    item_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE `items` (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    item_type_id INT,
    CONSTRAINT fk_items_itypes FOREIGN KEY (item_type_id)
        REFERENCES `item_types` (item_type_id)
);

CREATE TABLE `order_items` (
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    CONSTRAINT pk_order_items PRIMARY KEY (order_id, item_id),
    CONSTRAINT fk_oi_order FOREIGN KEY (order_id)
        REFERENCES `orders` (order_id),
    CONSTRAINT fk_oi_item FOREIGN KEY (item_id)
        REFERENCES `items` (item_id)
);

-- 6.University Database -----------------------------------------------
-- ---------------------------------------------------------------------

CREATE TABLE `subjects` (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_name VARCHAR(50)
);

CREATE TABLE `majors` (
    major_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE `students` (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_number VARCHAR(12),
    student_name VARCHAR(50),
    major_id INT,
    CONSTRAINT fk_students_majors FOREIGN KEY (major_id)
        REFERENCES `majors` (major_id)
);

CREATE TABLE `agenda` (
    student_id INT,
    subject_id INT,
    CONSTRAINT pk_agenda PRIMARY KEY (student_id, subject_id),
    CONSTRAINT fk_agenda_students FOREIGN KEY (student_id)
        REFERENCES `students` (student_id),
    CONSTRAINT fk_agenda_subjects FOREIGN KEY (subject_id)
        REFERENCES `subjects` (subject_id)
);

CREATE TABLE `payments` (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    payment_date DATE,
    payment_amount DECIMAL(8 , 2 ),
    student_id INT,
    CONSTRAINT fk_payments_students FOREIGN KEY (student_id)
        REFERENCES `students` (student_id)
);

-- 9.Peaks in Rila -----------------------------------------------------
-- ---------------------------------------------------------------------

USE `geography`;

SELECT 
    m.mountain_range,
    p.peak_name,
    p.elevation AS 'peak_elevation'
FROM
    peaks AS p
        JOIN
    `mountains` AS m ON p.mountain_id = m.id
WHERE
    m.mountain_range = 'Rila'
ORDER BY p.elevation DESC;




























