create database project;
use project;

create table member
(
    id            varchar(40) primary key,
    password      varchar(40)  not null,
    name          varchar(20)  not null,
    email         varchar(60)  not null,
    phone         varchar(20)  not null,
    zipcode       varchar(10)  not null,
    roadAddress   varchar(100) not null,
    jibunAddress  varchar(100) not null,
    detailAddress varchar(100),
    extraAddress  varchar(100),
    register_day  varchar(50)
);

--select * from member;

CREATE TABLE bbs
(
    num       INT           not null auto_increment PRIMARY KEY,
    writer    VARCHAR(20)   NOT NULL,
    subject   VARCHAR(100)  NOT NULL,
    content   VARCHAR(2048) NOT NULL,
    readCount INT           NULL DEFAULT 0,
    password  VARCHAR(20)   NOT NULL,
    reg_date  VARCHAR(50)   NULL,
    ip        VARCHAR(30)   NOT NULL,
    ref       INT           NOT NULL,
    re_step   INT           NOT NULL,
    re_level  INT           NOT NULL
);

--select * from bbs;