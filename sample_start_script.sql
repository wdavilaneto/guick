CREATE TABLE escola.ESC_PESSOA
(
  id_pessoa   integer,
  nome         varchar2(20),
  cpf            varchar2(14)  
);

CREATE TABLE escola.ESC_CURSO
(
  id_curso   integer,
  nome       varchar2(20)
);

CREATE TABLE escola.ESC_MENSALIDADE
(
  id_curso      integer,
  id_Pessoa    integer,
  valor           number(5,2)  
);

Insert into escola.ESC_Pessoa values(1,'Eliézio Mesquita','11109865424');
Insert into escola.ESC_Pessoa values(2,'Maria Joaquina','93104465334');
Insert into escola.ESC_Pessoa values(3,'José da Silva','14109835424');

Insert into escola.ESC_curso values(1,'Sistemas');
Insert into escola.ESC_curso values(2,'Administração');
Insert into escola.ESC_curso values(3,'Filosofia');

Insert into escola.ESC_mensalidade values(1,1,345.45);
Insert into escola.ESC_mensalidade values(2,3,500);
Insert into escola.ESC_mensalidade values(3,2,831.28);

Alter table escola.ESC_Pessoa add constraint ID_PESSOA_PK primary key (ID_PESSOA);
Alter table escola.ESC_Curso add constraint ID_CURSO_PK primary key (ID_CURSO);
alter table escola.ESC_Mensalidade add constraints ID_PESSOA_FK FOREIGN KEY(Id_Pessoa) references escola.ESC_Pessoa(Id_Pessoa);
alter table escola.ESC_Mensalidade add constraints ID_CURSO_FK FOREIGN KEY(Id_Curso) references escola.ESC_Curso(Id_Curso);