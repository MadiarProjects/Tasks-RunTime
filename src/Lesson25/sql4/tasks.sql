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

--task 2 for group by
select users.login as name ,count(posts.id) as posts
from users
join posts on users.id = posts.author_id
group by name;
select users.login as name,count(comments.id)
from users
join comments on users.id = comments.author_id
group by name;
select category as name,count(product_name)
from sales
group by category;

--task 3 for многим ко многим связь
create table movies
(
    id serial primary key,
    name varchar(100) not null
);
create table genre
(
    id serial primary key ,
    genre varchar(100) not null
);
create table movies_genre
(
    movie_id int references movies(id),
    genre_id int references genre(id),
    primary key (movie_id,genre_id)
);
insert into movies(name)values ('Beast and Princess'),('Star wars'),('Prince Percie'),('Openheimer');
insert into genre(genre)values ('fantastic'),('drama'),('science'),('romantic');
insert into movies_genre(movie_id, genre_id) values (1,1),(1,2),(1,4),
                                                    (2,1),(2,4),
                                                    (3,1),(3,2),(3,4),
                                                    (4,3);
select movies.name ,genre.genre
from movies
join movies_genre on movies.id = movies_genre.movie_id
join genre on movies_genre.genre_id = genre.id;
select movies.name as Movie,count(genre.genre)
from movies
join movies_genre on movies.id = movies_genre.movie_id
join genre  on movies_genre.genre_id = genre.id
group by Movie;
select genre.genre as Genre,count(movies_genre.movie_id)
from genre
join movies_genre on genre.id = movies_genre.movie_id
group by Genre;