--Ограничения (Constraints)
--DDL - create ,drop,alter atp
--DML - constraint , insert ,update atp
CREATE TABLE persons
(
--primary key он может существовать лишь 1 раз в 1 таблице для лишь 1 столбца
    id         SERIAL      NOT NULL PRIMARY KEY,
    last_name  VARCHAR(20) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    age        INTEGER,
--Чтобы определить ограничение UNIQUE для нескольких столбцов, используйте следующий запрос(CREATE TABLE):
--     unique (first_name,last_name),
    CONSTRAINT uc_person UNIQUE (last_name, first_name)
--для определения ограничения PRIMARY KEY для нескольких столбцов, используйте следующий запрос:
--     primary key (id,last_name),
--     CONSTRAINT pk_person PRIMARY KEY (id, last_name)
);
--Ограничение NOT NULL (ALTER TABLE)
ALTER TABLE persons
    ALTER COLUMN age SET NOT NULL;
--Ограничение UNIQUE (ALTER TABLE)
ALTER TABLE persons
    ADD UNIQUE (id);
--Удаление ограничения UNIQUE (ALTER TABLE)
ALTER TABLE persons
    DROP CONSTRAINT uc_person;
--Первичный ключ (PRIMARY KEY) (ALTER TABLE)
ALTER TABLE persons
    ADD PRIMARY KEY (id);
-- Чтобы разрешить определение ограничения PRIMARY KEY для нескольких столбцов, используйте
-- следующий запрос:
ALTER TABLE persons
    ADD CONSTRAINT pk_person PRIMARY KEY (id, last_name);
--удаление ограничение PRIMARY KEY (ALTER TABLE)
ALTER TABLE persons
    DROP CONSTRAINT pk_person;
--Ограничение FOREIGN KEY(внешний ключ)
--Внешний ключ (CREATE TABLE)
-- Следующий запрос создает FOREIGN KEY в столбце «customer_id» при создании таблицы «Orders»:
CREATE TABLE customers
(
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(20)
);
CREATE TABLE orders
(
    order_id SERIAL PRIMARY KEY,
    dish_name   VARCHAR(20),
    --определение значение столбца используя другую таблицу:
    customer_id INTEGER REFERENCES customers(id)
);
-- Внешний ключ (ALTER TABLE)
--  Добавление внешнего ключа с помощью оператора ALTER TABLE:
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_customers FOREIGN KEY
    (customer_id) REFERENCES customers (id);
-- Внешний ключ (DROP)
-- удаление ограничение FOREIGN KEY(DROP):
ALTER TABLE orders
    DROP CONSTRAINT fk_orders_customers;
--ограничение CHECK(CREATE TABLE)


--операторы AND OR NOT

--ORDER BY сортировка. desc- в обратном порядке
