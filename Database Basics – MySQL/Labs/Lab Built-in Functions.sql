************************************************************************
********************** Lab: Built-in Functions *************************
************************************************************************

USE `book_library`;

-- 1. Find Book Titles ----------------------------------------------
-- ------------------------------------------------------------------

SELECT 
    b.title
FROM
    `books` AS b
WHERE
    b.title LIKE ('The%')
ORDER BY b.id ASC;

-- 2. Replace Titles ------------------------------------------------
-- ------------------------------------------------------------------

UPDATE `books`
SET 
    title = REPLACE(title,'The','***')
WHERE
    SUBSTRING(title, 1, 3) = 'The';

SELECT 
    b.title
FROM
    `books` AS b
WHERE
    b.title LIKE ('***%')
ORDER BY b.id ASC;

-- 3. Sum Cost of All Books -----------------------------------------
-- ------------------------------------------------------------------

SELECT 
    CAST(SUM(books.cost) AS DECIMAL (20, 2))
FROM
    `books`;

-- 4. Days Lived ----------------------------------------------------
-- ------------------------------------------------------------------

SELECT 
    CONCAT_WS(' ', a.first_name, a.last_name) AS 'Full Name',
    TIMESTAMPDIFF(DAY, a.born, a.died) AS 'Days Lived'
FROM
    `authors` AS a;

-- 5. Harry Potter Books --------------------------------------------
-- ------------------------------------------------------------------

SELECT 
    title
FROM
    `books`
WHERE
    title LIKE ('%Harry%Potter%')
ORDER BY id;








































