************************************************************************
************************ Lab: Table Relations **************************
************************************************************************

-- 1.Mountains and Peaks -----------------------------------------------
-- ---------------------------------------------------------------------

CREATE DATABASE `mountain`;
USE `mountain`;

CREATE TABLE `mountains` (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    name VARCHAR(50)
);

CREATE TABLE `peaks` (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    name VARCHAR(50),
    mountain_id INT NOT NULL,
    CONSTRAINT fk_peaks_mountain FOREIGN KEY (mountain_id)
        REFERENCES mountains (id)
);

-- 2.Posts and Authors -------------------------------------------------
-- ---------------------------------------------------------------------

CREATE DATABASE `books_authors_OneToMany`;
USE `books_authors_OneToMany`;

CREATE TABLE `authors` (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE `books` (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    author_id INT NOT NULL,
    CONSTRAINT fk_books_authors FOREIGN KEY (author_id)
        REFERENCES authors (id)
        ON DELETE CASCADE
);

-- 3.Trip Organization -------------------------------------------------
-- ---------------------------------------------------------------------

USE `camp`;

SELECT 
    v.driver_id,
    v.vehicle_type,
    CONCAT_WS(' ', c.first_name, c.last_name) AS 'driver_name'
FROM
    `vehicles` AS v
        JOIN
    `campers` AS c ON v.driver_id = c.id;

-- 4.SoftUni Hiking ----------------------------------------------------
-- ---------------------------------------------------------------------

SELECT 
    r.starting_point AS 'route_starting_poin',
    r.end_point AS 'route_ending_point',
    r.leader_id,
    CONCAT_WS(' ', c.first_name, c.last_name) AS 'leader_name'
FROM
    `routes` AS r
        JOIN
    `campers` AS c ON r.leader_id = c.id;

-- 5.Project Management DB* --------------------------------------------
-- ---------------------------------------------------------------------

CREATE DATABASE `managment_db`;
USE `managment_db`;

CREATE TABLE `projects` (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    project_lead_id INT NOT NULL
);

CREATE TABLE `employees` (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    project_id INT NOT NULL,
    CONSTRAINT fk_employees_project FOREIGN KEY (project_id)
        REFERENCES projects (id)
);

CREATE TABLE `clients` (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    client_name VARCHAR(100) NOT NULL,
    project_id INT NOT NULL,
    CONSTRAINT fk_clients_projects FOREIGN KEY (project_id)
        REFERENCES projects (id)
);

ALTER TABLE `projects`
ADD CONSTRAINT fk_projects_employees FOREIGN KEY(project_lead_id)
REFERENCES employees(id),
ADD CONSTRAINT fk_projects_clients FOREIGN KEY(client_id) 
REFERENCES clients(id);

-- ---------------------------------------------------------------------








