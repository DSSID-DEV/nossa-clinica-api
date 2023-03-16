CREATE SEQUENCE IF NOT EXISTS estado_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1; 
	
CREATE TABLE estados (
	id_estado 	 			BIGINT DEFAULT NEXTVAL('estado_id_seq'),
	nome					VARCHAR(200)  NOT NULL,
	uf		 				VARCHAR(2) NOT NULL,
	
	PRIMARY KEY (id_estado)
);	


CREATE SEQUENCE IF NOT EXISTS cidade_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1; 
	
CREATE TABLE cidades (
	id_cidade 	 			BIGINT DEFAULT NEXTVAL('cidade_id_seq'),
	nome					VARCHAR(200)  NOT NULL,
	uf		 				VARCHAR(2),
	
	PRIMARY KEY (id_cidade)
);


INSERT INTO estados (id_estado, nome, uf)
values (NEXTVAL('estado_id_seq'), 'PERNAMBUCO', 'PE');		 	


INSERT INTO cidades (id_cidade, nome, uf)
values (NEXTVAL('cidade_id_seq'), 'LAGOA DE ITAENGA', 'PE');	


