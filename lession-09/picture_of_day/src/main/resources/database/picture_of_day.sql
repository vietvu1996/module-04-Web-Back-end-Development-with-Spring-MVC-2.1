create database picture_of_day;
use picture_of_day;

create table picture(
    id int auto_increment primary key,
    author varchar(50),
    comment varchar(255),
    rate integer,
    likes integer
);