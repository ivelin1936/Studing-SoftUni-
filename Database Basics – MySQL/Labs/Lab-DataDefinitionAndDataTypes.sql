******************************************************************************************************************
************************************ Lab: Data Definition And Data Types *****************************************
******************************************************************************************************************
-- --------------------------------------------------------------------------------------------
-- 1.1 Database gamebar ---> ------------------------------------------------------------------

CREATE DATABASE gamebar;

USE gamebar;

-- --------------------------------------------------------------------------------------------
-- 1.2 Create Tables in gamebar ---> ----------------------------------------------------------

CREATE TABLE employees(
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50)
  );

CREATE TABLE categories(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL
  );

CREATE TABLE products(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  category_id INT,
  CONSTRAINT FK_category_id FOREIGN KEY(category_id) REFERENCES  categories(id)
  );
  
-- --------------------------------------------------------------------------------------------
-- 1.3 Insert Data in Tables ---> -------------------------------------------------------------

INSERT INTO employees(`first_name`, `last_name`)
VALUES
	('Ivelin', 'Dimitrov'),
	('Angelina', 'Nqkoqsi'),
	('Koicho', 'Koichev');

-- --------------------------------------------------------------------------------------------
-- 1.4 Editing Data ---> ----------------------------------------------------------------------

UPDATE employees
SET employees.first_name = 'Ohciok'
Where id = 3;

-- --------------------------------------------------------------------------------------------
-- 1.5 Deleting Data ---> ---------------------------------------------------------------------

DELETE FROM employees
WHERE id = 1;

-- --------------------------------------------------------------------------------------------
-- 1.6 Dropping Tables ---> -------------------------------------------------------------------

DROP TABLE employees;

-- --------------------------------------------------------------------------------------------
-- 1.7 Dropping the Database ---> -------------------------------------------------------------

DROP DATABASE gamebar;

-- --------------------------------------------------------------------------------------------
-- 2.1 Dropping the Database ---> -------------------------------------------------------------

CREATE DATABASE gamebar;

USE gamebar;
-- --------------------------------------------------------------------------------------------
-- 2.2 Create Tables ---> ---------------------------------------------------------------------

CREATE TABLE employees(
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL
	);

CREATE TABLE categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
	);
	
CREATE TABLE products(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	category_id INT NOT NULL
	);

-- --------------------------------------------------------------------------------------------
-- 2.3 Insert Data in Tables ---> -------------------------------------------------------------

INSERT INTO employees(`first_name`, `last_name`)
VALUES
	('Someone', 'Toichev'),
	('Secondcho', 'asdasd'),
	('thirdcho', 'das');

-- --------------------------------------------------------------------------------------------
-- 2.4 Altering Tables ---> -------------------------------------------------------------------

ALTER TABLE employees
ADD COLUMN middle_name VARCHAR(50) NOT NULL;

-- --------------------------------------------------------------------------------------------
-- 2.5 Adding Constraints ---> ----------------------------------------------------------------

ALTER TABLE categories 
ADD column category_id INT;

ALTER TABLE categories
ADD CONSTRAINT fk_category_product FOREIGN KEY(category_id)
REFERENCES products(id);

-- --------------------------------------------------------------------------------------------
-- 2.6 Modifying Columns ---> -----------------------------------------------------------------

ALTER TABLE employees
MODIFY employees.middle_name VARCHAR(100);





