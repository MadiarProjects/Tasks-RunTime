create table developers
(
    id         serial,
    name       varchar(50),
    job        varchar(100),
    salary     double precision,
    start_work date
);
-- коментарий
--drop table удаление таблицы
drop table developers;
--заполнение таблицы
insert into developers(name, job, salary, start_work)
values ('David', 'Java', 123.42, '2020-10-10');

insert into developers(name, job) values ('Steve','php');

insert into developers(name, job, salary, start_work)
values ('Kate','Swift',2314.23,'2021-10-12'),
       ('Norman','Job',23334.2,'2021-10-20');

--обновление значение в таблице
update developers set salary=1000.0 , start_work='2024-10-12' where id = 4;

--удаление
delete from developers where id =4;

--сортировка

--получение
select name from developers;

--добавление столбца в таблицу
alter table developers
add lastname varchar(50);
-- удаление столбца в таблице
alter table developers
drop column lastname;
