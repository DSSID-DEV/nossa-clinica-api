package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.AgendaDoPaciente;
import com.nossaclinica_api.models.dtos.AgendaMedica;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseAgendamento;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseAgendamentoParaEdicao;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseDadosParaPagamento;
import com.nossaclinica_api.utils.Utils;

public class ConverteAgendamento {

    public static ResponseAgendamento paraResponseAtendimento(Object agenda) {

        return ResponseAgendamento.builder()
                .idAgendamento(Utils.parseLogn(((Object[]) agenda), ResponseAgendamento.ID_AGENDAMENTO))
                .servico(
                        Utils.CorrigirSeUSG(
                        Utils.parseString(((Object[]) agenda), ResponseAgendamento.SERVICO)
                        )
                )
                .descricaoServico(Utils.parseString((Object[]) agenda, ResponseAgendamento.DESCRICAO_SERVICO))
                .especialidade(Utils.parseString(((Object[]) agenda), ResponseAgendamento.ESPECIALIDADE))
                .especialista(Utils.parseString((Object[]) agenda, ResponseAgendamento.ESPECIALISTA))
                .status(Utils.parseString((Object[]) agenda, ResponseAgendamento.STATUS))
                .agendadoPara(Utils.parseLocalDate((Object[]) agenda, ResponseAgendamento.AGENDADO_PARA))
                .paciente(Utils.parseString((Object[]) agenda, ResponseAgendamento.PACIENTE))
                .marcadoPara(Utils.parseString((Object[]) agenda, ResponseAgendamento.MARCADO_PARA))
                .preco(Utils.parseBigDecimal((Object[]) agenda, ResponseAgendamento.PRECO))
                .pago(Utils.parseBoolean((Object[]) agenda, ResponseAgendamento.PAGO))
                .ultimaMestruacao(Utils.parseLocalDate((Object[]) agenda, ResponseAgendamento.ULTIMA_MESTRUACAO))
                .build();
    }

    public static ResponseAgendamentoParaEdicao paraEdicao(Object agenda) {
        return ResponseAgendamentoParaEdicao.builder()
                .idAgendamento(Utils.parseLogn((Object[]) agenda, ResponseAgendamentoParaEdicao.ID_AGENDAMENTO))
                .idEspecialista(Utils.parseLogn((Object[]) agenda, ResponseAgendamentoParaEdicao.ID_ESPECIALISTA))
                .idServico(Utils.parseLogn((Object[]) agenda, ResponseAgendamentoParaEdicao.ID_SERVICO))
                .idCliente(Utils.parseLogn((Object[]) agenda, ResponseAgendamentoParaEdicao.ID_CLIENTE))
                .idEspecialidade(Utils.parseLogn((Object[]) agenda, ResponseAgendamentoParaEdicao.ID_ESPECIALIDADE))
                .status(Utils.parseString((Object[]) agenda, ResponseAgendamentoParaEdicao.STATUS))
                .agendadoPara(Utils.parseLocalDate((Object[]) agenda, ResponseAgendamentoParaEdicao.AGENDADO_PARA))
                .marcadoPara(Utils.parseString((Object[]) agenda, ResponseAgendamentoParaEdicao.MARCADO_PARA))
                .ultimaMestruacao(Utils.parseLocalDate((Object[]) agenda, ResponseAgendamentoParaEdicao.ULTIMA_MESTRUACAO))
                .build();
    }

    public static DadosParaProntuario paraDadosParaProntuario(Object agenda) {
        var rqes = Utils.parseString((Object[]) agenda, DadosParaProntuario.RQES);
        rqes = rqes != null ? rqes.replace(", ",  " ") : null;
        return DadosParaProntuario.builder()
                .paciente(Utils.parseString((Object[]) agenda, DadosParaProntuario.PACIENTE))
                .nascidoEm(Utils.parseLocalDate((Object[]) agenda, DadosParaProntuario.NASCIDO_EM))
                .ultimaMestruacao(Utils.parseLocalDate((Object[]) agenda, DadosParaProntuario.ULTIMA_MESTRUACAO))
                .ultimoAtendimento(Utils.parseLocalDate((Object[]) agenda, DadosParaProntuario.ULTIMO_ATENDIMENTO))
                .tipoLogradouro(Utils.parseString((Object[]) agenda, DadosParaProntuario.TIPO_LOGRADOURO))
                .logradouro(Utils.parseString((Object[]) agenda, DadosParaProntuario.LOGRADOURO))
                .numero(Utils.parseString((Object[]) agenda, DadosParaProntuario.NUMERO))
                .contato(Utils.parseString((Object[]) agenda, DadosParaProntuario.CONTATO))
                .medico(Utils.parseBoolean((Object[]) agenda, DadosParaProntuario.IS_MEDICO))
                .sexoEspecialista(Utils.parseString((Object[]) agenda, DadosParaProntuario.SEXO_ESPECIALISTA))
                .especialista(Utils.parseString((Object[]) agenda, DadosParaProntuario.ESPECIALISTA))
                .conselho(Utils.parseString((Object[]) agenda, DadosParaProntuario.CONSELHO))
                .registro(Utils.parseString((Object[]) agenda, DadosParaProntuario.REGISTRO))
                .uf(Utils.parseString((Object[]) agenda, DadosParaProntuario.UF))
                .rqes(rqes)
                .numeroDeAtendimento((Utils.formatarUltimoNumeroAtendimento((Object[]) agenda, DadosParaProntuario.NUMERO_DE_ATENDIMENTO)))
                .build();
    }

    public static ResponseDadosParaPagamento paraResponseDadosParaPagamento(Object object) {
        if(object == null) {
            return null;
        }
        return ResponseDadosParaPagamento.builder()
                .idAgenda(Utils.parseLogn((Object[]) object, ResponseDadosParaPagamento.ID_AGENDA))
                .idCliente(Utils.parseLogn((Object[]) object, ResponseDadosParaPagamento.ID_CLIENTE))
                .paciente(Utils.parseString((Object[]) object, ResponseDadosParaPagamento.PACIENTE))
                .idEspecialista(Utils.parseLogn((Object[]) object, ResponseDadosParaPagamento.ID_ESPECIALISTA))
                .sexoDoEspecialista(Utils.parseString((Object[]) object, ResponseDadosParaPagamento.SEXO_DO_ESPECIALISTA))
                .especialista(Utils.parseString((Object[]) object, ResponseDadosParaPagamento.ESPECIALISTA))
                .especialidade(Utils.parseString((Object[]) object, ResponseDadosParaPagamento.ESPECIALIDADE))
                .idServico(Utils.parseLogn((Object[]) object, ResponseDadosParaPagamento.ID_SERVICO))
                .servico(Utils.parseString((Object[]) object, ResponseDadosParaPagamento.SERVICO))
                .preco(Utils.parseBigDecimal((Object[]) object, ResponseDadosParaPagamento.PRECO))
                .build();
    }

    public static AgendaMedica paraAgendaMedica(Object agenda) {
        return AgendaMedica.builder()
                .sexo(Utils.parseString((Object[]) agenda, AgendaMedica.SEXO))
                .medico(Utils.parseString((Object[]) agenda, AgendaMedica.MEDICO))
                .telefone(Utils.parseString((Object[]) agenda, AgendaMedica.TELEFONE))
                .tipoAtendimento(Utils.parseString((Object[]) agenda, AgendaMedica.TIPO_ATENDIMENTO))
                .status(Utils.parseString((Object[]) agenda, AgendaMedica.STATUS))
                .total(Utils.parseInt((Object[]) agenda, AgendaMedica.TOTAL_AGENDADO))
                .build();
    }

    public static AgendaDoPaciente paraAgendaDoPaciente(Object agenda) {
        return AgendaDoPaciente.builder()
                .tipoDeAtendimento(Utils.parseString((Object[]) agenda, AgendaDoPaciente.TIPO_DE_ATENDIMENTO))
                .status(Utils.parseString((Object[]) agenda, AgendaDoPaciente.STATUS))
                .sexo(Utils.parseString((Object[]) agenda, AgendaDoPaciente.SEXO))
                .medico(Utils.parseString((Object[]) agenda, AgendaDoPaciente.MEDICO))
                .agendadoPara(Utils.parseLocalDate((Object[]) agenda, AgendaDoPaciente.AGENDADO_PARA))
                .marcado_para(Utils.parseString((Object[]) agenda, AgendaDoPaciente.MARCADO_PARA))
                .paciente(Utils.parseString((Object[]) agenda, AgendaDoPaciente.PACIENTE))
                .telefone(Utils.parseString((Object[]) agenda, AgendaDoPaciente.TELEFONE))
                .nascidoEm(Utils.parseLocalDate((Object[]) agenda, AgendaDoPaciente.NASCIDO_EM))
                .build();
    }
}
