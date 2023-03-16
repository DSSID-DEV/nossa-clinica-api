CREATE SEQUENCE IF NOT EXISTS endereco_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1;

CREATE TABLE enderecos (
	id_endereco	 			BIGINT DEFAULT NEXTVAL('endereco_id_seq'),
	logradouro				VARCHAR(200)  NOT NULL,
	tipo_de_rua				SMALLINT,
	numero	 				VARCHAR(20)   UNIQUE NOT NULL,
	bairro					VARCHAR(200)  UNIQUE NOT NULL,	
	cep						VARCHAR(20)   UNIQUE NOT NULL,
	cidade_id				BIGINT NOT NULL,
	
	PRIMARY KEY (id_endereco),
	FOREIGN KEY (cidade_id) REFERENCES cidades(id_cidade)
);
