/*************************************************************/
/*                       SCHEMA ESCOLA                      */
/*************************************************************/

/* CAUTION The command below drops a user and all owned objects. */
--DROP USER ESCO CASCADE;
CREATE USER ESCO IDENTIFIED BY esco;
GRANT UNLIMITED TABLESPACE TO ESCO;
GRANT create session to ESCO;

/*CREATE TABLES*/
CREATE TABLE ESCO.ESCO_PESSOA
(
  EPES_DK integer,
  EPES_NM_PESSOA varchar2(20),
  EPES_CPF varchar2(14)
);

CREATE TABLE ESCO.ESCO_CURSO
(
  ECUR_DK   integer,
  ECUR_NM_CURSO       varchar2(20)
);

CREATE TABLE ESCO.ESCO_MENSALIDADE
(
  EMEN_DK integer,
  EMEN_ECUR_DK      integer,
  EMEN_EPES_DK    integer,
  EMEN_VALOR           number(5,2)
);

/* CREATE KEYS */
Alter table ESCO.ESCO_Pessoa add constraint EPES_PK primary key (EPES_DK);
Alter table ESCO.ESCO_Curso add constraint ECUR_PK primary key (ECUR_DK);
Alter table ESCO.ESCO_Mensalidade add constraint EMEN_PK primary key (EMEN_DK);
alter table ESCO.ESCO_Mensalidade add constraints EMEN_EPES_FK FOREIGN KEY(EMEN_EPES_DK) references ESCO.ESCO_Pessoa(EPES_DK);
alter table ESCO.ESCO_Mensalidade add constraints EMEN_ECUR_FK FOREIGN KEY(EMEN_ECUR_DK) references ESCO.ESCO_Curso(ECUR_DK);

/* INSERT LINES */
Insert into ESCO.ESCO_Pessoa values(1,'Eliézio Mesquita','11109865424');
Insert into ESCO.ESCO_Pessoa values(2,'Maria Joaquina','93104465334');
Insert into ESCO.ESCO_Pessoa values(3,'José da Silva','14109835424');

Insert into ESCO.ESCO_curso values(1,'Sistemas');
Insert into ESCO.ESCO_curso values(2,'Administração');
Insert into ESCO.ESCO_curso values(3,'Filosofia');

Insert into ESCO.ESCO_mensalidade values(1,1,1,345.45);
Insert into ESCO.ESCO_mensalidade values(2,2,3,500);
Insert into ESCO.ESCO_mensalidade values(3,3,2,831.28);

/* CREATE SEQUENCES */
CREATE SEQUENCE ESCO.ESCO_SQ_EPES_DK START WITH 4 INCREMENT BY   1;
CREATE SEQUENCE ESCO.ESCO_SQ_EMEN_DK START WITH 4 INCREMENT BY   1;
CREATE SEQUENCE ESCO.ESCO_SQ_ECUR_DK START WITH 4 INCREMENT BY   1;

GRANT SELECT, UPDATE, INSERT, DELETE ON ESCO.ESCO_pessoa TO ESCO;
GRANT SELECT, UPDATE, INSERT, DELETE ON ESCO.ESCO_curso TO ESCO;
GRANT SELECT, UPDATE, INSERT, DELETE ON ESCO.ESCO_mensalidade TO ESCO;
