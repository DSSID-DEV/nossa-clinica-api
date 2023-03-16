CREATE SEQUENCE IF NOT EXISTS especializacao_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1; 
	
CREATE TABLE especializacoes (
	id_especializacao 	 			BIGINT DEFAULT NEXTVAL('especializacao_id_seq'),
	descricao						VARCHAR(200)  NOT NULL,
	
	PRIMARY KEY (id_especializacao)
);

INSERT INTO especializacoes (id_especializacao, descricao) values (1, 'ALERGOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (2, 'ANGIOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (3, 'CARDIOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (4, 'CLINICO GERAL');
INSERT INTO especializacoes (id_especializacao, descricao) values (5, 'CIRURGIAO_DENTISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (6, 'DERMATOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (7, 'ENDOCRINOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (8, 'GASTROENTEROLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (9, 'GERIATRA');
INSERT INTO especializacoes (id_especializacao, descricao) values (10, 'GINECOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (11, 'IMPLANTODONTISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (12, 'NEUROLOGISA');
INSERT INTO especializacoes (id_especializacao, descricao) values (13, 'NEUROLOGISA PEDRIATA');
INSERT INTO especializacoes (id_especializacao, descricao) values (14, 'NUTRICIONISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (15, 'NUTROLOGO');
INSERT INTO especializacoes (id_especializacao, descricao) values (16, 'OBSTETRA');
INSERT INTO especializacoes (id_especializacao, descricao) values (17, 'ORTOPEDISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (18, 'ORTOPEDISTA_PEDRIATA');
INSERT INTO especializacoes (id_especializacao, descricao) values (19, 'ORTODONTIA');
INSERT INTO especializacoes (id_especializacao, descricao) values (20, 'OTORRINOLARINGOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (21, 'PEDIATRA');
INSERT INTO especializacoes (id_especializacao, descricao) values (22, 'PSICOLOGO');
INSERT INTO especializacoes (id_especializacao, descricao) values (23, 'PSICOPEDAGOGA');
INSERT INTO especializacoes (id_especializacao, descricao) values (24, 'PSIQUIATRA');
INSERT INTO especializacoes (id_especializacao, descricao) values (25, 'REUMATOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (26, 'TRAUMATOLOGISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (27, 'ULTRASSONOGRAFISTA');
INSERT INTO especializacoes (id_especializacao, descricao) values (28, 'UROLOGISTA');