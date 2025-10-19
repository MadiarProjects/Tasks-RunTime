-- Задача: База данных для библиотеки
--
-- Описание:
-- Необходимо спроектировать базу данных для библиотеки, которая хранит информацию о книгах, авторах, читателях и выдаче книг.
--
-- Требования к структуре базы данных:
-- 1. Авторы: Каждый автор может написать несколько книг.
-- 2. Книги: Каждая книга написана одним автором, но может быть выдана нескольким читателям.
-- 3. Читатели: Каждый читатель может взять несколько книг.
-- 4. Выдача книг: Хранит информацию о том, какие книги были выданы читателям и когда.
--
-- Связи:
-- - Один автор → много книг.
-- - Одна книга → много записей о выдаче.
-- - Один читатель → много записей о выдаче.
--
-- Задача:
-- Создайте SQL-скрипт для создания таблиц с соответствующими полями и связями (включая первичные и внешние ключи). Опишите структуру таблиц и дайте пример заполнения данными
create table persons
(
    id   serial primary key,
    name varchar(100)
);
create table books
(
    id          serial primary key,
    discreption varchar(100) not null,
    author_id   int references persons (id)
);
create table library
(
    id_of_book           int references books (id),
    data_when_book_given date not null ,
    id_who_took_book     int references persons (id)
);
insert into persons(name)
values ('John'),
       ('Alex'),
       ('Eduard'),
       ('Tom');
insert into books (discreption, author_id)
values ('Beast and Princess', 1),
       ('Mini Prince', 1),
       ('Romeo and Juelette', 2),
       ('The way to red planet', 2),
       ('Prince Percie', 3);
insert into library (id_of_book, data_when_book_given, id_who_took_book)
values (1, '2021-10-10', 4),
       (1, '2021-11-10', 3),
       (3, '2021-10-14', 3);
select persons.name as reader, library.id_of_book as idOfBook ,library.data_when_book_given as data, books.discreption as nameOfBook
from persons
         join library on persons.id = library.id_who_took_book
         join books on library.id_of_book = books.id;