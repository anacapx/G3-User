create database bduser;

create table tb_user (
	user_id serial not null primary key,
  	user_name varchar(100) not null,
  	user_cpf varchar(11) not null unique,
  	user_phone varchar(11) not null,
    user_birthday date not null,
    user_email varchar(100) not null unique
);