/* mysql */
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

/* oracle */
create user root identified by 1234;
grant dba to root;

create sequence exhbn_seq start with 1 increment by 1;

create table exhbn
(
    e_id        int          not null primary key,
    title       varchar(45)  not null,
    category    varchar(45)  not null,
    description varchar(45)  null,
    price       varchar(45)  not null,
    location    varchar(45)  not null,
    period 		varchar(45) 	 not null,
    time        varchar(45)  not null,
    image       varchar(100) null
);

create sequence bbs_seq start with 1 increment by 1;
create sequence board_seq start with 1 increment by 1;

create table BBS
(
    NUM       NUMBER         not null primary key,
    WRITER    VARCHAR2(30)   not null,
    SUBJECT   VARCHAR2(100)  not null,
    CONTENT   VARCHAR2(2048) not null,
    READCOUNT NUMBER default 0,
    PASSWORD  VARCHAR2(20)   not null,
    REG_DATE  DATE   default sysdate,
    IP        VARCHAR2(30)   not null,
    REF       NUMBER         not null,
    RE_STEP   NUMBER         not null,
    RE_LEVEL  NUMBER         not null
);