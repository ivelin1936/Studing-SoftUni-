**************************************************************************
*************** Exercises: Data Definition and Data Types ****************
**************************************************************************

ALTER TABLE `people`
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

-- ----------------------------------------------------------------------
-- 1.Create Database Minions --->

CREATE DATABASE minions;

-- ----------------------------------------------------------------------
-- 2.Create Tables in Minions --->

USE minions;

CREATE TABLE minions
(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
age INT(11) NOT NULL
);

CREATE TABLE towns
(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL
);

-- ----------------------------------------------------------------------
-- 3.Alter Minions Table --->

ALTER TABLE minions ADD column town_id int;

ALTER TABLE minions
ADD CONSTRAINT fk_minions_towns FOREIGN KEY(town_id)
REFERENCES towns(id);

-- ----------------------------------------------------------------------
-- 4.Insert Records in Both Tables --->

INSERT INTO `minions`.`towns` (`name`) 
VALUES 
('Sofia'), 
('Plovdiv'), 
('Varna');

INSERT INTO `minions`.`minions` (`name`, `age`, `town_id`) 
VALUES
('Kevin',22, 1),
('Bob', 15, 3),
('Steward', 13, 2);

-- ----------------------------------------------------------------------
-- 5.Truncate Table Minions --->

TRUNCATE TABLE Minions;

-- ----------------------------------------------------------------------
-- 6.Drop All Tables --->

DROP TABLE Minions;

DROP TABLE Towns;

-- ----------------------------------------------------------------------
-- 7.Create Table People --->

CREATE TABLE people (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(200) NOT NULL,
picture BLOB,
height DOUBLE(3,2),
weight DOUBLE(5,2),
gender ENUM('m', 'f') NOT NULL,
birthdate DATE NOT NULL,
biography TEXT );

INSERT INTO people (`name`, `picture`, `height`, `weight`, `gender`, `birthdate`, `biography`)
VALUES
('Gosho', 'asd', 1.23, 2.34,  'm', '2017-12-17', 'someone very lazy man'),
('Ivan', 'asd', 1.23, 2.34, 'm', '1963-01-17', 'I donot know my biography'),
('Ana', 'asd', 1.23, 2.34, 'f', '1987-04-19', 'very lazy'),
('Ivo', 'asd', 1.23, 2.34, 'm', '1999-07-01', 'bla bla bla'),
('Gergana', 'asd', 1.23, 2.34, 'f', '2016-12-17', 'something for biography');

-- ----------------------------------------------------------------------
-- 8.Create Table Users --->

CREATE TABLE users (
`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) UNIQUE NOT NULL,
`password` VARCHAR(26) NOT NULL,
`profile_picture` BLOB,
`last_login_time` DATETIME NOT NULL,
`is_deleted` TINYINT(1)
); 

INSERT INTO users (`username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES
('Gosho', 'pass2', 'asd', '2018-01-27 16:12:06', 1),
('Ivan', 'pass1', 'asd', '2018-01-27 16:12:06', 1),
('Ana', 'pass3', 'asd', '2018-01-27 16:12:06', 0),
('Ivo', 'pass4', 'asd', '2018-01-27 16:12:06', 1),
('Gergana', 'pass5', 'asd', '2018-01-27 16:12:06', 0);

-- ----------------------------------------------------------------------
-- 9.Change Primary Key --->

ALTER TABLE `users`ALTER `id` DROP DEFAULT;
ALTER TABLE `users`
	CHANGE COLUMN `id` `id` INT(11) NOT NULL FIRST,
	DROP PRIMARY KEY;

ALTER TABLE users
ADD CONSTRAINT PRIMARY KEY(id, username);

-- ----------------------------------------------------------------------
-- 10.Set Default Value of a Field --->

ALTER TABLE users
CHANGE COLUMN `last_login_time` `last_login_time` 
	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- ----------------------------------------------------------------------
-- 11.Set Unique Field --->  

ALTER TABLE users 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
	CHANGE COLUMN `username` `username` VARCHAR(30) UNIQUE NOT NULL;
    
-- ----------------------------------------------------------------------
-- 12.Movies Database ---> 

CREATE DATABASE movies;
USE movies;
 
CREATE TABLE `movies`.`directors` (
id INT NOT NULL PRIMARY KEY, 
director_name VARCHAR(50) NOT NULL, 
notes TEXT);

CREATE TABLE `movies`.`genres` (
id INT NOT NULL PRIMARY KEY, 
genre_name VARCHAR(50) NOT NULL, 
notes TEXT);

CREATE TABLE `movies`.`categories` (
id INT NOT NULL PRIMARY KEY, 
category_name VARCHAR(50) NOT NULL, 
notes TEXT);

CREATE TABLE `movies`.`movies` (
id INT NOT NULL PRIMARY KEY, 
title VARCHAR(50) NOT NULL, 
director_id INT NOT NULL,
copyright_year DATE,
length NVARCHAR(50),
genre_id INT NOT NULL,
category_id INT NOT NULL,
rating INT(11),
notes TEXT);

ALTER TABLE `movies`.`movies` 
ADD INDEX `FK_movie_director_idx` (`director_id` ASC),
ADD INDEX `FK_movie_categories_idx` (`category_id` ASC),
ADD INDEX `FK_movie_genres_idx` (`genre_id` ASC);
ALTER TABLE `movies`.`movies` 
ADD CONSTRAINT `FK_movie_director`
  FOREIGN KEY (`director_id`)
  REFERENCES `movies`.`directors` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_movie_categories`
  FOREIGN KEY (`category_id`)
  REFERENCES `movies`.`categories` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_movie_genres`
  FOREIGN KEY (`genre_id`)
  REFERENCES `movies`.`genres` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO directors (`id`, `director_name`, `notes`)
VALUES
(1, 'Gosho', 'something notes'),
(2, 'Ivan', 'vasdvsavsdav'),
(3, 'Ana', 'bla bla bla'),
(4, 'Ivo', 'ADs'),
(5, 'Gergana', 'asdasdasd');

INSERT INTO genres (`id`, `genre_name`, `notes`)
VALUES
(1, 'comedy', 'something notes'),
(2, 'tragedy', 'vasdvsavsdav'),
(3, 'action', 'bla bla bla'),
(4, 'porno', 'ADs'),
(5, 'full_come-tragedy', 'asdasdasd');

INSERT INTO categories (`id`, `category_name`, `notes`)
VALUES
(1, 'category1', 'something notes'),
(2, 'category2', 'vasdvsavsdav'),
(3, 'category3', 'bla bla bla'),
(4, 'category4', 'ADs'),
(5, 'category5', 'asdasdasd');

INSERT INTO movies (`id`, 
					`title`, 
					`director_id`, 
					`genre_id`, 
					`category_id`, 
					`rating`)
VALUES
(1, 'ScaryMovie', 3, 1, 4, 10),
(2, 'ScaryMovie', 2, 2, 1, 10),
(3, 'ScaryMovie', 4, 5, 3, 10),
(4, 'ScaryMovie', 1, 3, 2, 10),
(5, 'ScaryMovie', 5, 4, 5, 10);

-- ----------------------------------------------------------------------
-- 12.Movies Database 100/100 --->

CREATE TABLE directors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    director_name VARCHAR(30) not null,
    notes BLOB
);
insert into directors(id,director_name)
values
	(1,'dasdasd'),
	(2,'dasdasd'),
	(3,'dasdasd'),
	(4,'dasdasd'),
	(5,'dasdasd');

CREATE TABLE genres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    genre_name VARCHAR(30) not null not null,
    notes BLOB
);
insert into genres(id,genre_name)
values
	(2,'dasdad'),
	(1,'dasdad'),
    (3,'dasdad'),
    (4,'dasdad'),
    (5,'dasdad');


CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(30) not null,
    notes BLOB
);
insert into categories(id,category_name)
values 
	(1,'wi-fi'),
    (2,'wi-fi'),
    (3,'wi-fi'),
    (4,'wi-fi'),
    (5,'wi-fi');

CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30) not null,
    director_id INT,
    copyright_year DATETIME not null,
    length INT not null,
    genre_id INT not null,
    category_id INT not null,
    rating INT,
    notes BLOB
);
 insert into movies(id,title,copyright_year,`length`,genre_id,category_id)
 values
	(11,'dasdasda','2016-12-12',23,1,2),
	(10,'dasdasda','2016-12-12',23,1,2),
	(13,'dasdasda','2016-12-12',23,1,2),
	(14,'dasdasda','2016-12-12',23,1,2),
	(15,'dasdasda','2016-12-12',23,1,2);

-- ----------------------------------------------------------------------
-- 13.Car Rental Database --->

CREATE DATABASE `car_rental`;

USE `car_rental`;

CREATE TABLE categories (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	category VARCHAR(50) NOT NULL,
	daily_rate DECIMAL(5,2),
	weekly_rate DECIMAL(5,2),
	monthly_rate DECIMAL (5,2),
	weekend_rate DECIMAL(5,2) 
);

INSERT INTO categories(`category`)
VALUES
    ('firstC'),
	('secondC'),
	('thirdC'),
	('fourC'),
	('fiveC');

CREATE TABLE cars(
	id INT PRIMARY KEY AUTO_INCREMENT,
	plate_number INT UNIQUE NOT NULL,
	make VARCHAR(50) NOT NULL,
	model VARCHAR(50),
	car_year DATE,
	category_id INT,
	doors INT,
	picture BLOB,
	car_condition VARCHAR(50),
	available BIT);

INSERT INTO cars(`plate_number`, `make`, `model`) 
VALUES 
	(3, 'Mercedes', 'C220'),
	(2, 'Mercedes', 'S500'),
	(1, 'Audi', 'A8'),
	(4, 'Audi', 'A6'),
	(5, 'BMW', '320');

CREATE TABLE employees(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	first_name VARCHAR(50) NOT NULL, 
	last_name VARCHAR(50), 
	title VARCHAR(50), 
	notes TEXT);

INSERT INTO employees(`first_name`)
VALUES
	('Ivo Jicata'),
	('Gosho Chernia'),
	('Paco Mekiq'),
	('Strahil'),
	('Tosho Jivotnoto');

CREATE TABLE customers(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	driver_licence_number VARCHAR(20), 
	full_name VARCHAR(50),
	address VARCHAR(50), 
	city VARCHAR(20) NOT NULL, 
	zip_code VARCHAR(20), 
	notes TEXT);

INSERT INTO customers(`full_name`, `city`)
VALUE 
	('Petar Stefanof', 'Varna'),
	('Glarus Glarusov', 'Burgas'),
	('Chorbar Govedarov', 'Sofia'),
	('Tran Transki', 'Plovdiv'),
	('Gosho Petrov', 'Plovdiv');

CREATE TABLE rental_orders(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	employee_id INT, 
	customer_id INT,
	car_id INT, 
	car_condition VARCHAR(50), 
	tank_level DECIMAL(20,2), 
	kilometrage_start INT,
	kilometrage_end INT, 
	total_kilometrage INT, 
	start_date DATE, 	
	end_date DATE,
	total_days INT,
	rate_applied VARCHAR(50),
	tax_rate INT, 
	order_status VARCHAR(50),
	notes TEXT);
 
 INSERT INTO rental_orders(`employee_id`, `customer_id`, `car_id`) 
 VALUES 
	(1, 2, 1),
    (2, 2, 1),
    (3, 2, 1),
    (4, 2, 1),
    (5, 2, 1);

-- ----------------------------------------------------------------------
-- 14.Hotel Database --->

CREATE DATABASE hotel;

USE hotel;

CREATE TABLE employees(
	id INT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50),
	title VARCHAR(50),
	notes BLOB,
    PRIMARY KEY (id)
    );
    
INSERT INTO employees(`id`, `first_name`)
VALUES
	(1,'aaaa'),
    (2,'bbbb'),
    (3,'vvvv');

CREATE TABLE customers (
	account_number INT NOT NULL,
	first_name VARCHAR(50) NOT NULL, 
	last_name VARCHAR(50),
	phone_number VARCHAR(10),
	emergency_name VARCHAR(50),
	emergency_number VARCHAR(10),
	notes BLOB,
	PRIMARY KEY(account_number));
    
INSERT INTO customers(`account_number`, `first_name`)
VALUES
	(1,'Tosho'),
    (2,'Gosho'),
    (3,'Pesho');
    
CREATE TABLE room_status (
   room_status INT NOT NULL,
   notes BLOB,
   PRIMARY KEY(room_status)
   );
   
INSERT INTO room_status(`room_status`)
VALUES
	(1),
    (2),
    (3);

CREATE TABLE room_types(
   room_type INT NOT NULL,
   notes BLOB NOT NULL,
   PRIMARY KEY(room_type)
   );
   
INSERT INTO room_types(`room_type`, `notes`)
VALUES
	(1,'somone notes'),
    (2,'another notes'),
    (3,'bla bla bla');

CREATE TABLE bed_types(
  bed_type INT NOT NULL,
  notes BLOB NOT NULL,
  PRIMARY KEY(bed_type)
);

INSERT INTO bed_types(`bed_type`, `notes`)
VALUES
	(1,'tralala'),
    (2,'trala'),
    (3,'tralalala');

CREATE TABLE rooms(
   room_number INT NOT NULL, 
   room_type VARCHAR(50),
   bed_type VARCHAR(50),
   rate VARCHAR(50), 
   room_status VARCHAR(50), 
   notes BLOB NOT NULL, 
   PRIMARY KEY(room_number)
);

INSERT INTO rooms(`room_number`, `notes`)
VALUES
	(1,'caca'),
    (2,'fdsdsf'),
    (3,'dadsda');

CREATE TABLE payments(
   id INT NOT NULL,
   employee_id INT,
   payment_date DATETIME, 
   account_number VARCHAR(10), 
   first_date_occupied DATETIME, 
   last_date_occupied DATETIME, 
   total_days INT, 
   amount_charged DOUBLE(10,2), 
   tax_rate DOUBLE(10,2), 
   tax_amount DOUBLE(10,2), 
   payment_total DOUBLE(10,2), 
   notes BLOB NOT NULL, 
   PRIMARY KEY(id)
);

INSERT INTO payments(`id`, `notes`)
VALUES
	(1,'asd'),
    (2,'asd'),
    (3,'asd');

CREATE TABLE occupancies(
  id INT, 
  employee_id INT,
  date_occupied DATETIME,
  account_number VARCHAR(10),
  room_number INT,
  rate_applied VARCHAR(10), 
  phone_charge VARCHAR(10), 
  notes BLOB NOT NULL, 
  PRIMARY KEY(id)
);

INSERT INTO occupancies(`id`, `notes`)
VALUES
	(1,'adasdas'),
    (2,'asdasd'),
    (3,'asdasd');
    
-- ----------------------------------------------------------------------
-- 15.Create SoftUni Database --->  
    
CREATE DATABASE soft_uni;

USE soft_uni;

CREATE TABLE towns(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50)
    );

CREATE TABLE addresses(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    address_text VARCHAR(50), 
    town_id INT,
    CONSTRAINT fk_addresses_towns FOREIGN KEY(town_id) REFERENCES towns(id)
    );

CREATE TABLE departments(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(20)
    );

CREATE TABLE employees(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    first_name VARCHAR(20), 
    middle_name VARCHAR(20), 
    last_name VARCHAR(20), 
    job_title VARCHAR(20), 
    department_id INT,
    hire_date DATE, 
    salary DOUBLE(10,2), 
    address_id INT,
    CONSTRAINT FK_address_id FOREIGN KEY(address_id) REFERENCES addresses(id),
	CONSTRAINT FK_department_id FOREIGN KEY(department_id) REFERENCES  departments(id) 
    ); 

-- ----------------------------------------------------------------------
-- 17.	Basic Insert ---> 

INSERT INTO soft_uni.towns(`name`)
VALUES 
	('Sofia'),
	('Plovdiv'),
	('Varna'),
	('Burgas');

INSERT INTO soft_uni.departments(`name`)
VALUES 
	('Engineering'),
	('Sales'),
	('Marketing'),
	('Software Development'),
	('Quality Assurance');

INSERT INTO soft_uni.employees(`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`)
VALUES 
	('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01','3500.00'),
	('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02','4000.00'),
	('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28','525.25'),
	('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09','3000.00'),
	('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28','599.88');

-- ----------------------------------------------------------------------
-- 18.Basic Select All Fields --->

 SELECT * FROM towns;
 
 SELECT * FROM departments;
 
 SELECT * FROM employees;
 
-- ----------------------------------------------------------------------
-- 19.Basic Select All Fields and Order Them --->

SELECT * FROM towns 
GROUP BY name;

SELECT * FROM departments 
GROUP BY name;

SELECT * FROM employees 
ORDER BY salary DESC;

-- ----------------------------------------------------------------------
-- 20.Basic Select Some Fields --->

SELECT t.name FROM towns AS t 
GROUP BY name;

SELECT d.name FROM departments AS d 
GROUP BY name;

SELECT e.first_name, e.last_name, e.job_title, e.salary 
	FROM employees AS e
ORDER BY salary DESC;    
    
-- ----------------------------------------------------------------------
-- 21.Increase Employees Salary --->    
    
UPDATE employees
SET employees.salary = employees.salary + employees.salary * 0.1;

SELECT e.salary FROM employees AS e;

-- ----------------------------------------------------------------------
-- 22.	Decrease Tax Rate --->

USE hotel;

UPDATE payments
SET payments.tax_rate = payments.tax_rate * 0.97;

SELECT p.tax_rate FROM payments AS p; 

-- ----------------------------------------------------------------------
-- 23.	Delete All Records --->

DELETE FROM occupancies;

-- or

TRUNCATE occupancies;

-- ----------------------------------------------------------------------     
    
    
    
    
    
    
    
    
