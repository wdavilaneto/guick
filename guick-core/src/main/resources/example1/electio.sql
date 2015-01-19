-- CREATE SEQUENCE cargo;
--drop table candidatura;
--drop table membro;

--drop table cargo;
--drop table eleicao;
--drop table candidatura;
--drop table membro;
--drop table funcionario;


--  CREATE SEQUENCE  electio_sq_id_dk ;
create table if not exists cargo (
	id Integer primary key,
	nome varchar(500) not null,
	descricao text
);

-- CREATE SEQUENCE IF NOT EXISTS electio_sq_
CREATE TABLE IF NOT EXISTS funcionario (
	id integer PRIMARY KEY,
	nome VARCHAR(250) not null,
	foto bytea,
	foto_type varchar(50),
	cargo integer references cargo
);

create table if not exists promotoria (
    id integer primary key,
    nome varchar(250) not null,
    descricao text
);

create table if not exists membro (
	id integer primary key references funcionario,
	titular integer references promotoria
) ;


create table if not exists eleicao (
    id integer primary key,
    nome varchar(400) not null,
    data_criacao timestamp not null default current_timestamp,
    inicio_votacao timestamp,
    fim_votacao timestamp
);

create table if not exists candidatura (
    id integer primary key,
    candidato integer references funcionario,
    eleicao integer references eleicao not null,
    descricao varchar(600),
    proposta text
);
