create table users
(
    id    serial PRIMARY KEY,
    login varchar(100) not null
);
create table posts
(
    id          serial primary key,
    descreption varchar(100) NOT NULL,
    author_id int references users(id)
);
create table comments
(
    id serial primary key ,
    author_id int references users(id),
    post_id int references posts(id),
    text varchar(200) not null
);

insert into users( login) values ('lady@gmail.com'),
                                 ('dante@gmail.com');
insert into posts ( descreption, author_id) values ('cooking',1),
                                                   ('desrt',1),
                                                   ('adventure',2);
insert into comments ( author_id, post_id, text) values (1,3,'cool'),
                                                        (2,1,'nice'),
                                                        (2,1,'awesome');


--joins
select users.login as login, posts.descreption from posts
join users on posts.author_id = users.id;
select users.login as login, comments.text as comment
from users
join comments on users.id = comments.author_id;
select users.login as login,posts.descreption as descreption
from users
join posts on users.id=posts.author_id;