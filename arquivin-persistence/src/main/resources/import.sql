insert into perfil (ID_PERFIL, NAME) values (1, 'ADMIN');
insert into perfil (ID_PERFIL, NAME) values (2, 'USUÁRIO INTERNO');
insert into perfil (ID_PERFIL, NAME) values (3, 'USUÁRIO EXTERNO');

--------------------------------------------------------------------------------------------------------------------------------------------
--									ANDES									
--------------------------------------------------------------------------------------------------------------------------------------------
insert into company (ID_COMPANY, CNPJ, NAME, MAINCOLOR, HEADERCOLOR, SUBDOMAIN, EMAIL) values ((select nextval('seq_company')), '13677300000151', 'Andes Geologia', 'RED', 'BLACK', 'andes', 'contato@andesgeologia.com.br');
insert into users (id_user, disabled, email, name, pwd) values ((select nextval('seq_user')), false, 'a@a.com', 'Usuário Teste', 'r0kR7SqOczgilDBgz1Iv+ld/409iwn5PcZzby9sSK9s=');
insert into user_company (fk_user, fk_company, fk_perfil) values (1, 1, 1);

--------------------------------------------------------------------------------------------------------------------------------------------
--									UNIT Arquitetura										
--------------------------------------------------------------------------------------------------------------------------------------------

insert into company (ID_COMPANY, CNPJ, NAME, MAINCOLOR, HEADERCOLOR, SUBDOMAIN, EMAIL) values ((select nextval('seq_company')), '16543713000131', 'UNIT ARQUITETOS ASSOCIADOS', 'BLACK', 'BLACK', 'unit', 'unit.arquitetos@gmail.com');
insert into users (id_user, disabled, email, name, pwd) values ((select nextval('seq_user')), false, 'unit.arquitetos@gmail.com', 'Igor Hideo Hirama', 'r0kR7SqOczgilDBgz1Iv+ld/409iwn5PcZzby9sSK9s=');
insert into user_company (fk_user, fk_company, fk_perfil) values (1, 1, 1);

--------------------------------------------------------------------------------------------------------------------------------------------
--									ALPES									
--------------------------------------------------------------------------------------------------------------------------------------------

insert into company (ID_COMPANY, CNPJ, NAME, MAINCOLOR, HEADERCOLOR, SUBDOMAIN, EMAIL, PHONE) values ((select nextval('seq_company')), '18692929000194', 'Alpes Laboratório', 'BLUE', 'BLACK', 'alpes', 'contato@alpeslaboratorio.com.br', '4132741955');
insert into users (id_user, disabled, email, name, pwd) values ((select nextval('seq_user')), false, 'niton@alpeslaboratorio.com.br', 'Nilton', 'r0kR7SqOczgilDBgz1Iv+ld/409iwn5PcZzby9sSK9s=');
insert into user_company (fk_user, fk_company, fk_perfil) values (5, 4, 1);

--------------------------------------------------------------------------------------------------------------------------------------------
--									BASE ENGENHARIA									
--------------------------------------------------------------------------------------------------------------------------------------------
insert into company (ID_COMPANY, CNPJ, NAME, MAINCOLOR, HEADERCOLOR, SUBDOMAIN, EMAIL, PHONE) values ((select nextval('seq_company')), '20522113000164', 'Base Engenharia', 'BLUE', 'BLACK', 'base', 'rodrigodagnol@hotmail.com', '15997268722');
insert into users (id_user, disabled, email, name, pwd) values ((select nextval('seq_user')), false, 'rodrigodagnol@hotmail.com', 'Rodrigo', 'r0kR7SqOczgilDBgz1Iv+ld/409iwn5PcZzby9sSK9s=');
insert into user_company (fk_user, fk_company, fk_perfil) values (5, 4, 1);
