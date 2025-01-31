-- enderecos definição

CREATE SEQUENCE seq_endereco_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

CREATE TABLE enderecos (
	id_endereco                 BIGINT PRIMARY KEY DEFAULT nextval('seq_endereco_id'),
	cidade                      VARCHAR(50),
	uf                          VARCHAR(3),
	numero                      VARCHAR(10),
	bairro                      VARCHAR(30),
	tp_logradouro               VARCHAR(10),
	logradouro                  VARCHAR(50),
	cep                         VARCHAR(15),
	complemento                 VARCHAR(30));

CREATE SEQUENCE seq_especialidade_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória


-- especi"alidades definição
CREATE TABLE especialidades (
    id_especialidade            BIGINT PRIMARY KEY DEFAULT nextval('seq_especialidade_id'),
    descricao                   VARCHAR(70)
);

-- servicos definição
CREATE SEQUENCE seq_servico_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

CREATE TABLE servicos (
    id_servico                  BIGINT PRIMARY KEY DEFAULT nextval('seq_servico_id'),
    nome                        VARCHAR(70),
    descricao                   VARCHAR(1500),
    orientacao                  VARCHAR(500)
);

-- users definição
CREATE SEQUENCE seq_user_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;

CREATE TABLE users (
    id_user                     BIGINT PRIMARY KEY DEFAULT nextval('seq_user_id'),
    nome                        VARCHAR(80),
    username                    VARCHAR(255),
    email                       VARCHAR(100),
    senha                       VARCHAR(200),
    ultima_senha                VARCHAR(200),
    nascido_em                  DATE,
    telefone                    VARCHAR(15),
    whatsapp                    BOOLEAN DEFAULT FALSE,
    permissao                   VARCHAR(20) DEFAULT ('CLIENTE'),
    avatar                      VARCHAR(255),
    ativo                       BOOLEAN DEFAULT TRUE
    );

-- clientes definição
CREATE SEQUENCE seq_cliente_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

CREATE TABLE clientes (
	id_cliente                  BIGINT PRIMARY KEY DEFAULT nextval('seq_cliente_id'),
	nome                        VARCHAR(255),
	cpf                         VARCHAR(14),
	documento                   VARCHAR(15),
	orgao_emissor               VARCHAR(10),
	telefone                    VARCHAR(15),
	whatsapp                    BOOLEAN DEFAULT FALSE,
	nascido_em                  DATE,
	email                       VARCHAR(30),
	id_user                     BIGINT,
	id_endereco                 BIGINT,
	ativo                       BOOLEAN DEFAULT TRUE,
	CONSTRAINT clientes_user_FK FOREIGN KEY (id_user) REFERENCES users(id_user),
	CONSTRAINT clientes_enderecos_FK FOREIGN KEY (id_endereco) REFERENCES enderecos(id_endereco)
);

-- especiladidades_medica definição
CREATE SEQUENCE seq_dependente_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

CREATE TABLE depedentes (
	id_dependente               BIGINT PRIMARY KEY DEFAULT nextval('seq_dependente_id'),
	id_titular                  BIGINT,
	id_cliente                  BIGINT,
	grau_parentesco             VARCHAR(80),
    CONSTRAINT id_titular_dependente_fk FOREIGN KEY(id_titular) REFERENCES clientes(id_cliente),
    CONSTRAINT id_cliente_dependente_fk FOREIGN KEY(id_cliente) REFERENCES clientes(id_cliente)
    );

-- medicos definição
CREATE SEQUENCE seq_especialista_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

CREATE TABLE especialistas (
	id_especialista             BIGINT PRIMARY KEY DEFAULT nextval('seq_especialista_id'),
	nome                        VARCHAR(100),
	nome_social                 VARCHAR(75),
	conselho                    VARCHAR(30),
	registro                    VARCHAR(30),
	uf                          VARCHAR(3),
	avatar                      VARCHAR(255),
	telefone                    VARCHAR(15),
	email                       VARCHAR(70),
	sexo                        VARCHAR(1),
	id_user                     BIGINT,
	whatsapp                    BOOLEAN DEFAULT FALSE,
	nascido_em                  DATE,
	ativo                       BOOLEAN DEFAULT TRUE,
	CONSTRAINT medicos_users_fk FOREIGN KEY (id_user) REFERENCES users(id_user));

 -- servicos_medico definição
 CREATE SEQUENCE seq_servico_medico_id
     START WITH 1        -- Inicia em 1
     INCREMENT BY 1      -- Incremento de 1
     NO MINVALUE         -- Sem valor mínimo
     NO MAXVALUE         -- Sem valor máximo
     CACHE 1;            -- Quantos valores a sequence mantém na memória

 CREATE TABLE servicos_medicos (
 	id_servico_medico           BIGINT PRIMARY KEY DEFAULT nextval('seq_servico_medico_id'),
    id_especialista             BIGINT,
 	id_servico                  BIGINT,
 	descricao                   VARCHAR(100),
 	receita                     DECIMAL(6,2) DEFAULT 0.0,
 	percentagem                 DECIMAL(6,2) DEFAULT 0.0,
    preco                       DECIMAL(9,2),
 	CONSTRAINT servicos_medicos_medicos_fk FOREIGN KEY (id_especialista) REFERENCES especialistas(id_especialista),
 	CONSTRAINT servicos_medicos_servicos_fk FOREIGN KEY (id_servico) REFERENCES servicos(id_servico)
 );

-- especiladidades_medica definição
CREATE SEQUENCE seq_especialidade_medica_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

CREATE TABLE especialidades_medica (
	id_especialidade_medica     BIGINT PRIMARY KEY DEFAULT nextval('seq_especialidade_medica_id'),
	id_especialista             BIGINT,
	id_especialidade            BIGINT,
	rqe                         VARCHAR(10),
    area_de_atuacao             VARCHAR(50),
    CONSTRAINT especialidades_medicas_especialistas_fk FOREIGN KEY(id_especialista) REFERENCES especialistas(id_especialista),
    CONSTRAINT especialidades_medicas_especialidades_fk FOREIGN KEY(id_especialidade) REFERENCES especialidades(id_especialidade)
    );

 -- agendamentos definição
 CREATE SEQUENCE seq_agendamento_id
     START WITH 1        -- Inicia em 1
     INCREMENT BY 1      -- Incremento de 1
     NO MINVALUE         -- Sem valor mínimo
     NO MAXVALUE         -- Sem valor máximo
     CACHE 1;            -- Quantos valores a sequence mantém na memória

 CREATE TABLE agendamentos (
 	id_agendamento              BIGINT PRIMARY KEY DEFAULT nextval('seq_agendamento_id'),
 	id_especialista             BIGINT,
 	id_servico                  BIGINT,
 	id_cliente                  BIGINT,
 	id_especialidade            BIGINT DEFAULT NULL,
 	agendado_para               DATE,
 	marcado_para                VARCHAR(10),
 	status                      VARCHAR(30),
 	ultima_mestruacao           DATE,
 	ultimo_atendimento          DATE,
 	desconto                    DECIMAL(6,2) DEFAULT 0.0,
 	pago                        BOOLEAN DEFAULT FALSE,
 	CONSTRAINT agendamentos_servico_fk FOREIGN KEY (id_servico) REFERENCES servicos(id_servico),
 	CONSTRAINT agendamentos_clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
 	CONSTRAINT agendamentos_medicos_fk FOREIGN KEY (id_especialista) REFERENCES especialistas(id_especialista),
    CONSTRAINT agendamento_especialidade_FK FOREIGN KEY (id_especialidade) REFERENCES especialidades(id_especialidade)
 );

 -- exames definição
 CREATE SEQUENCE seq_exame_id
     START WITH 1        -- Inicia em 1
     INCREMENT BY 1      -- Incremento de 1
     NO MINVALUE         -- Sem valor mínimo
     NO MAXVALUE         -- Sem valor máximo
     CACHE 1;            -- Quantos valores a sequence mantém na memória

 CREATE TABLE exames (
 	id_exame                    BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('seq_exame_id'),
 	descricao                   VARCHAR(50) NOT NULL,
 	sigla                       VARCHAR(5),
 	tipo_exame                  VARCHAR(20),
 	custo                       DECIMAL(9,2),
 	preco                       DECIMAL(9,2),
 	material                    VARCHAR(50),
 	meio_de_coleta              VARCHAR(50),
    volume                      VARCHAR(30),
    prazo                       VARCHAR(20),
    interpretacao               VARCHAR(500),
    doencas_relacionadas        VARCHAR(300));

 -- instrucoes definição
 CREATE SEQUENCE seq_instrucao_id
     START WITH 1        -- Inicia em 1
     INCREMENT BY 1      -- Incremento de 1
     NO MINVALUE         -- Sem valor mínimo
     NO MAXVALUE         -- Sem valor máximo
     CACHE 1;            -- Quantos valores a sequence mantém na memória
 CREATE TABLE instrucoes (
 	id_instrucao                BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('seq_instrucao_id'),
 	tipo_instrucao              TEXT NOT NULL,
 	descricao                   TEXT NOT NULL,
 	id_exame                    BIGINT,
 	CONSTRAINT instrucoes_exames_fk FOREIGN KEY (id_exame) REFERENCES exames(id_exame));
	
	
-- movimentacao definição

CREATE SEQUENCE seq_movimentacao_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

CREATE TABLE movimentacoes (
	id_movimentacao                 BIGINT PRIMARY KEY DEFAULT nextval('seq_movimentacao_id'),
	id_agendamento                  BIGINT,
	servico                         VARCHAR(500),
	prestador                       VARCHAR(250),
	pago                            BOOLEAN DEFAULT FALSE,
	CONSTRAINT movimentacao_agenda_FK FOREIGN KEY (id_agendamento) REFERENCES agendamentos(id_agendamento));

-- forma de pagamento definição

CREATE SEQUENCE seq_forma_de_pagamento_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória


CREATE TABLE formas_de_pagamento (
	id_forma_de_pagamento                 BIGINT PRIMARY KEY DEFAULT nextval('seq_forma_de_pagamento_id'),
	pago_com                              VARCHAR(50),
	valor                                 DECIMAL(9,2),
	hash_autorizacao_cancelamento         VARCHAR(255) DEFAULT NULL,
	status                                VARCHAR(30) DEFAULT 'CONSOLIDADO',
	id_movimentacao                       BIGINT,
	CONSTRAINT formas_de_pagamento_movimentacao_FK FOREIGN KEY (id_movimentacao) REFERENCES movimentacoes(id_movimentacao));

CREATE SEQUENCE seq_autorizacao_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória


CREATE TABLE autorizacoes (
	id_autorizacao                 BIGINT PRIMARY KEY DEFAULT nextval('seq_autorizacao_id'),
	hash_autorizacao               VARCHAR(100),
	motivo                         VARCHAR(255),
	solicitante                    VARCHAR(255),
	solcitado_em                   TIMESTAMP,
	autorizado                     BOOLEAN DEFAULT TRUE,
	autorizado_por                 VARCHAR(255) DEFAULT NULL);

--cria sequence da tabela clientes_users
CREATE SEQUENCE seq_user_cliente_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

--cria tabela users_clientes
CREATE TABLE users_clientes (
	id_user_cliente_id            BIGINT PRIMARY KEY DEFAULT nextval('seq_user_cliente_id'),
	id_user                       BIGINT,
	id_cliente                    BIGINT,
	responsavel                   BOOLEAN DEFAULT FALSE,
	grau_parentesco               VARCHAR(100),
	cobertado                     BOOLEAN DEFAULT FALSE,
	CONSTRAINT user_users_clientes_users_FK FOREIGN KEY (id_user) REFERENCES users(id_user),
    CONSTRAINT cliente_users_clientes_clientes_FK FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
	);

--cria sequence seq_numero_atendimento_id
CREATE SEQUENCE seq_numero_atendimento_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

--cria tabela numero_atendimento
CREATE TABLE numero_atendimento (
	id_numero_atendimento          BIGINT PRIMARY KEY DEFAULT nextval('seq_numero_atendimento_id'),
	id_cliente                     BIGINT,
	id_especialista                      BIGINT DEFAULT NULL,
	id_especialidade               BIGINT DEFAULT NULL,
	tipo_atendimento               VARCHAR(100),
	numero_atendimento             BIGINT,
	data_atendimento               DATE DEFAULT CURRENT_DATE,
    CONSTRAINT cliente_numero_atendimento_fk FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    CONSTRAINT medico_numero_atendimento_fk FOREIGN KEY (id_especialista) REFERENCES especialistas(id_especialista),
    CONSTRAINT especialidade_numero_atendimento_fk FOREIGN KEY (id_especialidade) REFERENCES especialidades(id_especialidade)
	);

  -- entradas_de_exame definição
  CREATE SEQUENCE seq_entrada_exame_id
      START WITH 1        -- Inicia em 1
      INCREMENT BY 1      -- Incremento de 1
      NO MINVALUE         -- Sem valor mínimo
      NO MAXVALUE         -- Sem valor máximo
      CACHE 1;

  CREATE TABLE entradas_de_exame (
  	id_entrada_exame                 BIGINT PRIMARY KEY DEFAULT nextval('seq_entrada_exame_id'),
  	realizado_por                    VARCHAR(50),
  	realizado_em                     DATE,
  	ids_exames                       VARCHAR(500),
  	id_movimentacao                  BIGINT,
  	has_entrada                      VARCHAR(500),
  	CONSTRAINT entradas_de_exame_movimentacao_fk FOREIGN KEY (id_movimentacao) REFERENCES movimentacoes(id_movimentacao)
  	);

  	CREATE SEQUENCE seq_uri_gerador_pdf_id
        START WITH 1        -- Inicia em 1
        INCREMENT BY 1      -- Incremento de 1
        NO MINVALUE         -- Sem valor mínimo
        NO MAXVALUE         -- Sem valor máximo
        CACHE 1;            -- Quantos valores a sequence mantém na memória

    CREATE TABLE uris_geradores_pdf (
    	id_uri_gerador_pdf                 BIGINT PRIMARY KEY DEFAULT nextval('seq_uri_gerador_pdf_id'),
    	id_servico						   BIGINT DEFAULT NULL,
    	tipo_documento					   INT2,
    	descricao	                       VARCHAR(50),
    	uri                     		   VARCHAR(100)
    	);

CREATE SEQUENCE seq_parametro_report_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;            -- Quantos valores a sequence mantém na memória

CREATE TABLE parametros_reports (
	id_parametro_report                 BIGINT PRIMARY KEY DEFAULT nextval('seq_parametro_report_id'),
	papel                               VARCHAR(100),
	largura                             VARCHAR(20),
	altura                              VARCHAR(20),
	largura_da_logo                     VARCHAR(20),
    margem_top_pagina                   VARCHAR(20),
    margem_direita_pagina               VARCHAR(20),
    margem_rodape_da_pagina             VARCHAR(20),
    margem_esquerda_da_pagina           VARCHAR(20),
    background_color_do_cabecalho       VARCHAR(40),
    background_color_do_rodape          VARCHAR(40),
    pixel_da_linha                      VARCHAR(20),
    cor_da_linha                        VARCHAR(40)
    );


-- cria tabelas analise_colposcopio e achados_colposcopio
CREATE SEQUENCE seq_analise_colposcopio_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;

CREATE TABLE analise_colposcipio (
	id_analise_colposcopio           BIGINT PRIMARY KEY DEFAULT nextval('seq_analise_colposcopio_id'),
	descricao                        VARCHAR(50),
	id_servico                       BIGINT DEFAULT 15);

CREATE SEQUENCE seq_achado_colposcopio_id
    START WITH 1        -- Inicia em 1
    INCREMENT BY 1      -- Incremento de 1
    NO MINVALUE         -- Sem valor mínimo
    NO MAXVALUE         -- Sem valor máximo
    CACHE 1;

CREATE TABLE achados_colposcipio (
	id_achado_colposcopio          BIGINT PRIMARY KEY DEFAULT nextval('seq_achado_colposcopio_id'),
	descricao                      VARCHAR(50),
	id_analise_colposcopio         BIGINT,
	CONSTRAINT colposcopio_fk FOREIGN KEY (id_analise_colposcopio)
	REFERENCES analise_colposcipio(id_analise_colposcopio)
	);
