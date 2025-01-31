package com.nossaclinica_api.repositories.queries;

public class AgendamentoQueries {
    public static final String EXISTS_AGENDAMENTO = """
             select count(id_agendamento) > 0
                from agendamentos where id_cliente = :idCliente
                and id_especialista = :idEspecialista
                and id_especialidade = :idEspecialidade 
                and id_servico = :idServico
                and agendado_para = :agendadoPara
            """;
    public static final String BUSCAR_POR_ID = """
            select 
            agenda.id_agendamento as idAgendamento, servico.descricao as servico,
                especialidade.descricao as especialidade, 
                especialista.nome as especialista, agenda.status as status, agenda.agendado_para as agendadoPara, 
                cliente.nome as paciente, agenda.marcado_para as marcadoPara, servico_medico.preco as preco 
                from agendamentos agenda 
                join especialistas especialista on (especialista.id_especialista = agenda.id_especialista)
                join especialidades especialidade on especialidade.id_especialidade = agenda.id_especialidade 
                join servicos servico on (servico.id_servico = agenda.id_servico) 
                join clientes cliente on (cliente.id_cliente = agenda.id_cliente) 
                join servicos_medicos servico_medico on (servico_medico.id_servico = servico.id_servico
                and servico_medico.id_especialista = especialista.id_especialista) where agenda.id_agendamento = :idAgendamento
            """;


    public static final String LISTAR_AGENDAMENTOS = """
            select
            agenda.id_agendamento as idAgendamento, 
            servico.nome as servico,
            servico.descricao as descricaoServico, 
            especialidade.descricao as especialidade,
                    especialista.nome_social as especialista, agenda.status as status, agenda.agendado_para as agendadoPara, 
                    cliente.nome as paciente, agenda.marcado_para as marcadoPara, servico_medico.preco as preco, 
                    agenda.pago as pago,
                    agenda.ultima_mestruacao as ultimaMestruacao 
                from agendamentos agenda 
                join especialistas especialista on (especialista.id_especialista = agenda.id_especialista)
                left join especialidades especialidade 
                on especialidade.id_especialidade = agenda.id_especialidade 
                join servicos servico on (servico.id_servico = agenda.id_servico) 
                join clientes cliente on (cliente.id_cliente = agenda.id_cliente) 
                join servicos_medicos servico_medico on (servico_medico.id_servico = servico.id_servico
                and servico_medico.id_especialista = especialista.id_especialista) where agenda.id_agendamento > 0                 
            """;
    public static final String BUSCAR_AGENDAMENTO_PARA_EDICAO = """
            select agenda.id_agendamento as idAgendamento, 
            especialista.id_especialista as idespecialista, 
            servico.id_servico as idServico,
            cliente.id_cliente as idCliente, 
            especialidade.id_especialidade as idEspecialidade,
            agenda.status as status, 
            agenda.agendado_para as agendadoPara,
            agenda.marcado_para as marcadoPara,
            agenda.ultima_mestruacao as ultimaMestruacao 
                  from agendamentos agenda
                  join especialistas especialista on (especialista.id_especialista = agenda.id_especialista)
                  left join especialidades especialidade on especialidade.id_especialidade = agenda.id_especialidade
                  join servicos servico on (servico.id_servico = agenda.id_servico)
                  join clientes cliente on (cliente.id_cliente = agenda.id_cliente)
                  join servicos_medicos servico_medico on (servico_medico.id_servico = servico.id_servico
                  and servico_medico.id_especialista = especialista.id_especialista) 
                  where agenda.id_agendamento = :idAgendamento                              
            """;
    public static final String DADOS_PARA_PDF = """
                select distinct 
                     cliente.nome as paciente,
                        cliente.nascido_em as nascidoEm,
                        agenda.ultima_mestruacao as ultimaMestruacao,
                        agenda.ultimo_atendimento ultimoAtendimento,
                        endereco.tp_logradouro as tipoLogradouro,
                        endereco.logradouro as logradouro,
                        endereco.numero as numero,
                        cliente.telefone as contato,
                        case 
                           when (especialista.conselho = 'CRM') then true 
                           else false
                        end as isMedico,
                        especialista.sexo as sexoEspecialista,
                        especialista.nome_social as especialista,
                        especialista.conselho as conselho, 				
                        especialista.registro as registro,
                        especialista.uf as uf,
                        string_agg(em.rqe, ', ') as rqes,
                        atendimento.numero_atendimento                       
                               from agendamentos agenda
                               join especialistas especialista on (especialista.id_especialista = agenda.id_especialista)
                               left join especialidades_medica em on (especialista.id_especialista = em.id_especialista)
                               left join especialidades especialidade on (em.id_especialidade = especialidade.id_especialidade)
                               join servicos servico on (servico.id_servico = agenda.id_servico)
                               join clientes cliente on (cliente.id_cliente = agenda.id_cliente)
                               left join enderecos endereco on cliente.id_endereco = endereco.id_endereco
                               join servicos_medicos servico_medico on (servico_medico.id_servico = servico.id_servico
                               and servico_medico.id_especialista = especialista.id_especialista)
                               left join numero_atendimento atendimento on 
                               (atendimento.id_cliente = cliente.id_cliente 
                               and atendimento.data_atendimento = CURRENT_DATE 
                               and atendimento.id_especialista = especialista.id_especialista  
                               and atendimento.tipo_atendimento = servico.nome)
                       where agenda.id_agendamento = :idAgendamento
                             group by especialista.id_especialista, 
                             especialista.nome, 
                             especialista.conselho,
                             especialista.registro, 
                             especialista.uf,
                             cliente.nome, 
                             cliente.nascido_em, 
                             endereco.tp_logradouro, 
                             endereco.logradouro,
                             endereco.numero, 
                             cliente.telefone, 
                             agenda.ultima_mestruacao,
                             atendimento.numero_atendimento,
                             especialista.sexo,
                             agenda.ultimo_atendimento                       
            """;
            public static final String BUSCAR_NOME_PACIENTE = """
            select cliente.nome as paciente
                      from agendamentos agenda
                      join clientes cliente on (cliente.id_cliente = agenda.id_cliente)
                      where agenda.id_agendamento = :idAgendamento 
            """;
    public static final String CARREGAR_DADOS_PARA_EFETIVAR_PAGAMENTO = """
            select agenda.id_agendamento as idAgenda,
            	   cliente.id_cliente as idCliente,
            	   cliente.nome as paciente,
            	   especialista.id_especialista as idEspecialista,
            	   especialista.sexo as sexoDoEspecialista,
            	   especialista.nome_social as especialista,
            	   especialidade.descricao as especialidade,
            	   servico.id_servico as idServico,
            	   servico.nome as servico,
            	   servico_medico.preco as preco
            from clientes cliente
            	left join agendamentos agenda
            			on agenda.id_cliente = cliente.id_cliente 
            	left join especialistas especialista
            			on especialista.id_especialista = agenda.id_especialista
                left join especialidades especialidade 
                        on especialidade.id_especialidade = agenda.id_especialidade
            	left join servicos servico
            			on servico.id_servico = agenda.id_servico
            	left join servicos_medicos servico_medico
            			on (servico_medico.id_servico = servico.id_servico
            				and servico_medico.id_especialista = especialista.id_especialista)
            	where agenda.id_agendamento  = :idAgenda
            """;
    public static final String TEM_AGENDA_HOJE = """
            select count(agenda.id_agendamento) > 0 from agendamentos agenda
            	where agenda.id_especialista = :idEspecialista 
            	and agenda.status = 'Atendido' 
            	and agenda.pago is true
            	and agenda.agendado_para = CURRENT_DATE
            """;
      public static final String AGENDAS_DE_AMANHA_DO_MEDICO = """
              select especialista.id_especialista as idEspecialista, 
                     especialista.sexo as sexo, 
                     especialista.nome as especialista,
                     especialista.telefone as telefone,
                     servico.descricao as tipoDeAtendimento,
                     agenda.status as status,
                     COUNT(*) AS total
              from agendamentos agenda
              join servicos servico
              on servico.id_servico = agenda.id_servico
              join especialistas especialista 
              on especialista.id_especialista = agenda.id_especialista 
              WHERE status IN ('Agendado', 'Confirmado')
              and agenda.agendado_para = CURRENT_DATE + INTERVAL '1 day'
              GROUP BY agenda.status, servico.descricao, 
              especialista.id_especialista
              ORDER BY agenda.status, servico.descricao
              """;

      public static final String AGENDA_DO_DIA_SEGUINTE_DOS_PACIENTES = """
              select servico.descricao as tipoDeAtendimento, 
                     agenda.status as status,
                     especialista.sexo as sexo, 
                     especialista.nome as especialista,  
                     agenda.agendado_para as agendadoPara, 
                     agenda.marcado_para as marcadoPara,
                     cliente.nome as paciente, 
                     cliente.telefone as telefone,  
                     cliente.nascido_em as nascidoEm
              from agendamentos agenda
              join servicos servico
              on servico.id_servico = agenda.id_servico
              join especialistas especialista 
              on especialista.id_especialista = agenda.id_especialista 
              join clientes cliente 
              on cliente.id_cliente = agenda.id_cliente
              WHERE agenda.agendado_para = CURRENT_DATE + INTERVAL '1 day' 
              and agenda.status = 'Agendado' or agenda.status = 'Confirmado'
              """;

    public static final String CARREGAR_DADOS_PARA_FECHAMENTO_E_PAGAMENTO_DO_ATENDENDIMENTO = """
            SELECT
                 especialista.id_especialista AS idOrigem,
                 COUNT(servico.nome) AS totalDeAtendimentos,
                 servico.nome AS tipoAtendimento,                      
                  ROUND(
                        SUM(
                            CASE 
                                WHEN servicoMedico.preco > 0 
                                THEN servicoMedico.preco - (servicoMedico.preco * agenda.desconto) 
                                ELSE formaPagamento.valor - (formaPagamento.valor * agenda.desconto)
                            END), 2) AS somaDoAtendimento,   
                 servicoMedico.percentagem AS porcentagem,
                 servicoMedico.receita as receita,
                 ROUND(
                     SUM(
                         CASE 
                             WHEN servicoMedico.percentagem = 0.0
                             THEN CASE 
                                 WHEN servicoMedico.preco > 0 
                                        THEN (servicoMedico.preco - servicoMedico.receita)
                                        ELSE (formaPagamento.valor - (formaPagamento.valor * agenda.desconto))
                                 END
                            ELSE
                            CASE 
                                 WHEN servicoMedico.preco > 0 
                                        THEN (servicoMedico.preco - (servicoMedico.preco * agenda.desconto)) * servicoMedico.percentagem
                                        ELSE (formaPagamento.valor - (formaPagamento.valor * agenda.desconto)) * servicoMedico.percentagem
                                 END                 
                         END
                     )
                     , 2) AS calculoDoAtendimento
             FROM agendamentos agenda
             JOIN servicos servico
                 ON servico.id_servico = agenda.id_servico
             JOIN especialistas especialista
                 ON especialista.id_especialista = agenda.id_especialista
             JOIN servicos_medicos servicoMedico
                 ON (servicoMedico.id_especialista = especialista.id_especialista
                     AND servicoMedico.id_servico = servico.id_servico)
             LEFT JOIN movimentacoes movimentacao
                 ON movimentacao.id_agendamento = agenda.id_agendamento
             LEFT JOIN formas_de_pagamento formaPagamento
                 ON formaPagamento.id_movimentacao = movimentacao.id_movimentacao 
                 WHERE agenda.status = 'Atendido'
             and (movimentacao.pago is true
             or formaPagamento.pago_com = 'Credito da casa')
             and especialista.id_especialista = :idEspecialista
             and agenda.agendado_para = CURRENT_DATE
             GROUP BY servico.descricao, servicoMedico.percentagem, 
             especialista.id_especialista, servicoMedico.receita, 
             servico.nome
             ORDER BY servico.nome
            """;

}
