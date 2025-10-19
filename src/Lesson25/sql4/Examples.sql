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

