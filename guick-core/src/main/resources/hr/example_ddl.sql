--alter session set _ORACLE_SCRIPT=true;
DROP USER sa CASCADE;
CREATE USER sa IDENTIFIED BY sa;
GRANT UNLIMITED TABLESPACE TO sa;
GRANT create session to sa;
alter session set current_schema=sa;


/* CREATE TABLES*/
CREATE TABLE PESSOA
(
  id    integer GENERATED BY DEFAULT ON NULL AS IDENTITY,
  nome varchar2(20),
  cpf varchar2(14)
);

CREATE TABLE CURSO
(
  id    integer GENERATED BY DEFAULT ON NULL AS IDENTITY,
  nome       varchar2(20)
);

CREATE TABLE MENSALIDADE
(
  id    integer GENERATED BY DEFAULT ON NULL AS IDENTITY,
  curso_id      integer,
  pessoa_id    integer,
  valor           number(5,2)
);

/* CREATE KEYS */
alter table Mensalidade add constraints pessoa_fk FOREIGN KEY(pessoa_id) references Pessoa(id);
alter table Mensalidade add constraints curso_fk FOREIGN KEY(curso_id) references Curso(id);

/* INSERT LINES */
Insert into Pessoa values(1,'Eliézio Mesquita','11109865424');
Insert into Pessoa values(2,'Maria Joaquina','93104465334');
Insert into Pessoa values(3,'José da Silva','14109835424');

Insert into curso values(1,'Sistemas');
Insert into curso values(2,'Administração');
Insert into curso values(3,'Filosofia');

Insert into mensalidade values(1,1,1,345.45);
Insert into mensalidade values(2,2,3,500);
Insert into mensalidade values(3,3,2,831.28);

GRANT SELECT, UPDATE, INSERT, DELETE ON pessoa TO ESCO;
GRANT SELECT, UPDATE, INSERT, DELETE ON curso TO ESCO;
GRANT SELECT, UPDATE, INSERT, DELETE ON mensalidade TO ESCO;
