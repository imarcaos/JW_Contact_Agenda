create database dbagenda;

use dbagenda;

create table contatos (
	idcon int primary key auto_increment,
	nome varchar(50) not null,
	telefone varchar(15) not null,
	email varchar(50)
);

show tables;
describe contatos;


/* CRUD CREATE */
insert into contatos (nome, telefone, email) values ('John', '999333222', 'john@email.com');


/* CRUD READ */
select * from contatos;
select * from contatos order by nome;
select * from contatos where idcon = 2;

/* CRUD UPDATE */
update contatos set nome="Bruce Wayne" where idcon=5; 
update contatos set nome="Bruce Wayne Junior", telefone="9123456321", email="brucewj@email.com" where idcon=5; 

/* CRUD DELETE */
delete from contatos where idcon = 5;


