create database project;
use project;

create table member
(
    id           varchar(40) primary key,
    password     varchar(40)   not null,
    name         varchar(20)   not null,
    email        varchar(60)   not null,
    phone        varchar(20)   not null,
    zipcode      varchar(10)   not null,
    roadAddress varchar(100) not null,
    jibunAddress varchar(100) not null,
    detailAddress varchar(100),
    extraAddress varchar(100),
    register_day varchar(50)
);

select * from member;