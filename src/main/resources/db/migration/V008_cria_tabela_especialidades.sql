CREATE SEQUENCE IF NOT EXISTS especialidade_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1;

CREATE TABLE especialidade (
	id_especialidade		BIGINT DEFAULT NEXTVAL('especialidade_id_seq'),
	descricao				VARCHAR(50)  NOT NULL,
	crm						VARCHAR(20)  NOT NULL,
	
	PRIMARY KEY(id_especialidade)
);