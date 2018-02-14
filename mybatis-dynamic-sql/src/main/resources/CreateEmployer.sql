drop table if exists employer;

create table employer(
    id int not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    birth_date date not null,
    employed varchar(3) not null,
    occupation varchar(30) null,
    primary key(id)
);

insert into employer values(1,"feifei","liu","1995-08-21","在职","Java开发工程师");