create table questBoard(
num int auto_increment primary key, 
writer varchar(20),
email varchar(50),
subject varchar(50),
password varchar(10),
reg_date datetime default now(), 
ref int,
re_step int, 
re_level int,
readcount int default 0, 
content varchar(1000)
);