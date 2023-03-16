CREATE SEQUENCE IF NOT EXISTS cliente_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1;

CREATE TABLE clientes (
	id_cliente	 			BIGINT DEFAULT NEXTVAL('cliente_id_seq'),
	contato_id				BIGINT,
	endereco_id				BIGINT,
	nome					VARCHAR(300)  NOT NULL,
	cpf		 				VARCHAR(15),
	rg						VARCHAR(20),	
	emitido_por				VARCHAR(20),
	nascido_em				DATE,
	usuario_id				BIGINT NOT NULL,
	
	PRIMARY KEY(id_cliente),
	FOREIGN KEY(contato_id) REFERENCES contatos(id_contato),
	FOREIGN KEY(endereco_id) REFERENCES enderecos(id_endereco),
	FOREIGN KEY(usuario_id) REFERENCES usuarios(id_usuario)
);