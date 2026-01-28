-- DB(스키마이름) springBootDB

create database springBootDB;

use springBootDB;


create table board(
id int auto_increment primary key,
title varchar(50) not null,
content varchar(1000) not null,
writer varchar(20) not null,
createdAt datetime default now() -- user의 등록일 check
);