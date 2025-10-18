CREATE TABLE movies
(
    id              SERIAL PRIMARY KEY,    -- Уникальный идентификатор фильма
    title           VARCHAR(255) NOT NULL, -- Название фильма
    rental_rate     FLOAT        NOT NULL, -- Стоимость аренды
    rental_duration INTEGER      NOT NULL, -- Длительность аренды (в днях)
    rating          VARCHAR(10)  NOT NULL  -- Рейтинг фильма (например, 'PG', 'PG-13', 'R')
);
INSERT INTO movies (title, rental_rate, rental_duration, rating)
VALUES ('The Godfather', 2.99, 7, 'R'),
       ('Casablanca', 1.99, 5, 'PG'),
       ('The Silence of the Lambs', 3.49, 4, 'R'),
       ('Jurassic Park', 2.49, 5, 'PG-13'),
       ('The Matrix', 2.99, 4, 'R'),
       ('Spirited Away', 1.99, 6, 'PG'),
       ('Saving Private Ryan', 3.99, 6, 'R'),
       ('The Lion King', 2.49, 4, 'G'),
       ('Back to the Future', 2.99, 5, 'PG'),
       ('Toy Story', 2.49, 4, 'G');
-- 1. Найти фильмы с рейтингом "R" или "NC-17", которые стоят меньше 3 долларов за аренду:
SELECT * from movies where  rental_rate<3 and (rating='R' OR rating ='NC-17') ;
--2. Вывести названия фильмов, которые либо имеют рейтинг "G", либо стоят больше 4 долларов за аренду, либо их продолжительность превышает
SELECT * from movies where rating = 'G' OR rental_rate>4 OR rental_duration>5;
--3. Найти фильмы с продолжительностью аренды от 3 до 5 дней включительно, но исключить фильмы с рейтингом "PG":
SELECT *from movies where (rental_duration >=3 and rental_duration<=5) and rating='PG';
--4. Вывести названия фильмов, которые не имеют рейтинг "PG-13" и стоят меньше 2.50 долларов за аренду:
select title from movies where not rating ='PG-13' and rental_rate<2.5;
--5. Найти фильмы с рейтингом "R", продолжительностью аренды не менее 7 дней и стоимостью аренды от 1.50 до 3.00 долларов:
select * from movies where rating='R' and rental_duration>=7 and (rental_rate>=1.5 AND rental_rate<=3);
--6. Найти фильмы, которые не имеют рейтинг "G" и не имеют рейтинг "PG-13", при этом их продолжительность аренды составляет ровно 5 дней:
select * from movies where rental_duration=5 and not( rating='G' OR rating ='PG-13')  ;


--task2
CREATE TABLE sales
(
    id           SERIAL PRIMARY KEY,
    product_name VARCHAR(100),  -- название товара
    category     VARCHAR(50),   -- название категорий
    price        DECIMAL(10, 2),  -- стоимость заказа
    quantity     INT,             -- количество
    sale_date    DATE,            -- дата продажи
    region       VARCHAR(50)      -- регион
);
INSERT INTO sales (product_name, category, price, quantity, sale_date, region)
VALUES ('Ноутбук', 'Электроника', 45000.00, 2, '2024-01-15', 'Север'),
       ('Мышь', 'Электроника', 1500.00, 5, '2024-01-16', 'Юг'),
       ('Клавиатура', 'Электроника', 3000.00, 3, '2024-01-17', 'Север'),
       ('Стул', 'Мебель', 8000.00, 1, '2024-01-18', 'Запад'),
       ('Стол', 'Мебель', 15000.00, 2, '2024-01-19', 'Восток'),
       ('Монитор', 'Электроника', 20000.00, 4, '2024-01-20', 'Север'),
       ('Лампа', 'Мебель', 2500.00, 6, '2024-01-21', 'Юг'),
       ('Ноутбук', 'Электроника', 45000.00, 1, '2024-01-22', 'Восток'),
       ('Шкаф', 'Мебель', 25000.00, 1, '2024-01-23', 'Запад'),
       ('Мышь', 'Электроника', 1500.00, 10, '2024-01-24', 'Север'),
       ('Диван', 'Мебель', 35000.00, 1, '2024-01-25', 'Юг'),
       ('Принтер', 'Электроника', 12000.00, 2, '2024-01-26', 'Восток'),
       ('Кресло', 'Мебель', 10000.00, 3, '2024-01-27', 'Запад');
--Посчитайте, сколько товаров стоят дороже 10000
-- Посчитайте среднюю цену товаров, которые стоят от 5000 до 20000
--Найдите количество продаж товаров дешевле 3000 в регионах "Юг" или "Запад"
select count(*) from sales where price>10000;
select avg(price) from sales where price>=5000 and price<=20000;
select count(*) from sales where (region='Юг' or  region='Запад') and price*quantity<=3000;