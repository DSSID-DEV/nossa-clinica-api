
ALTER TABLE usuarios DROP COLUMN celular;					

ALTER TABLE usuarios DROP COLUMN email;

ALTER TABLE usuarios ADD COLUMN contato_id BIGINT;

ALTER TABLE usuarios ADD FOREIGN KEY (contato_id) REFERENCES contatos (id_contato); 
	
