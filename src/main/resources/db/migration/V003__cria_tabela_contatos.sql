CREATE SEQUENCE IF NOT EXISTS contato_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1;

CREATE TABLE contatos (
	id_contato	 			BIGINT DEFAULT NEXTVAL('contato_id_seq'),
	celular					VARCHAR(15),  
	email					VARCHAR(60),
	instagram 				VARCHAR(60),
	tem_whatsapp			SMALLINT,	
	
	PRIMARY KEY (id_contato)
);

