--оператор like
insert into persons (last_name, first_name, age)
values ('Jonathan','John',25),
       ('Steve','Lupora',35),
       ('Jayob','Danile',18);
update persons set first_name ='Steve' where id=2;
select id
from persons
where first_name ilike '%%';
-- 1. WHERE name LIKE 'a%'- Находит любые значения начинающиеся с «a»
-- 2. WHERE name LIKE '%a'- Находит любые значения, заканчивающиеся на «а»
-- 3. WHERE name LIKE '%at%' - Находит любые значения, у которых есть «at» в любой позиции.
-- 4. WHERE name LIKE '_r%' -Находит любые значения, во второй позиции которых стоит
-- буква «r».
-- 5. WHERE name LIKE 'a_%'-Находит любые значения, которые начинаются с «a» и имеют
-- длину не менее 2 символов.
-- 6. WHERE name LIKE 'a__%' - Находит любые значения, которые начинаются с «a» и имеют
-- длину не менее 3 символов.
-- 7. WHERE name LIKE 'a%o' - Находит любые значения, начинающиеся с «а» и
-- заканчивающиеся на «о»


--оператор IN
-- SELECT имя_столбца
-- FROM table_name
-- WHERE имя_столбца IN (значение1, значение2, ...);
select *
from persons
where age IN (25,35);
--NOT IN работает в обратку
SELECT *
from persons
where first_name IN (Select name from developers);

--оператор BETWEEN
select *
from persons
where age BETWEEN 25 and 35;
--NOT BETWEEN в обратку

select *
from persons
where age between 25 and 35
and id not in (1,3);


--joins

--inner joins
CREATE TABLE city
(
    id
         SERIAL PRIMARY KEY ,
    name VARCHAR(20) NOT NULL
);
INSERT INTO city (name) VALUES ('Москва');
INSERT INTO city (name) VALUES ('Санкт-Петербург');
INSERT INTO city (name) VALUES ('Казань');

CREATE TABLE person
(
    name
            VARCHAR(20) NOT NULL,
    city_Id INTEGER     NOT NULL
);
INSERT INTO person (name, city_id) VALUES ('Андрей', 1);
INSERT INTO person (name, city_id) VALUES ('Леонид', 2);
INSERT INTO person (name, city_id) VALUES ('Сергей', 1);
INSERT INTO person (name, city_id) VALUES ('Григорий', 4);
--left join
select person.name,city.name
from person
left join city on person.city_Id = city.id ;
--right join on
select city.name,person.name
from person
right join city on city.id = person.city_Id;
--full join
select *
from person
full join city on person.city_Id = city.id;
--UNION
select name
from person
union
select first_name from persons where first_name ilike '%o%';
--GROUP BY
select category as name,count(product_name)
from sales
group by category;
select product_name as name,sum(price) as total_price
from sales
group by name
order by total_price;
--предложение HAVING
select count(*)as num,category
from sales
group by category
having count(*)>6;
--string_agg для выведение всех название у одного обьекта

