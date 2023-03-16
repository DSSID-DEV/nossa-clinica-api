CREATE SEQUENCE IF NOT EXISTS usuario_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1;

CREATE TABLE usuarios (
	id_usuario	 			BIGINT DEFAULT NEXTVAL('usuario_id_seq'),
	user_name				VARCHAR(60),
	senha					VARCHAR(15),
	celular					VARCHAR(15),  
	email					VARCHAR(60),
	ativo					SMALLINT,
	permissao				SMALLINT,
	perfil_id				BIGINT,
	
	PRIMARY KEY (id_usuario),
	FOREIGN KEY (perfil_id) REFERENCES perfis(id_perfil)
);
