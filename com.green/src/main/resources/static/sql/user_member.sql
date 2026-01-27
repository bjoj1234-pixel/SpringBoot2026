-- DB(스키마이름) springBootDB

create database springBootDB;

use springBootDB;

-- 테이블 이름: user_member
create table user_member(
no int auto_increment primary key,
id varchar(20) not null unique,
pw varchar(100) not null,
mail varchar(50) not null,
phone varchar(50) not null,
reg_date datetime default now(), -- user의 등록일 check
mod_date datetime default now() -- user의 수정일 check

);


create table board(
id int auto_increment primary key,
title varchar(50) not null,
content varchar(1000) not null,
writer varchar(20) not null,
createdAt datetime default now() -- user의 등록일 check
);