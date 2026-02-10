-- DB(스키마이름) springBootDB

create database springBootDB;

use springBootDB;

create table reviewBoard(
num int auto_increment primary key,
writer varchar(20),
title varchar(50),
writerPw varchar(100),
star int,
reg_date datetime default now(),
readcount int default 0,
content varchar(1000)
);
