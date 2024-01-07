create database blog_application;
use blog_application;

create table blog(
                     id int auto_increment primary key,
                     name varchar(50),
                     content varchar(255),
                     image varchar(255)
);