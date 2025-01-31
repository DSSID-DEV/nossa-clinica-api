package com.nossaclinica_api.services;

import com.nossaclinica_api.models.dtos.AgendaDoPaciente;
import com.nossaclinica_api.models.dtos.AgendaMedica;
import com.nossaclinica_api.models.dtos.MessageWhatsapp;
import com.nossaclinica_api.models.entities.Autorizacao;
import com.nossaclinica_api.models.entities.User;
import com.nossaclinica_api.reports.utils.ConstantesValues;
import com.nossaclinica_api.repositories.FormaDePagamentoRespository;
import com.nossaclinica_api.utils.Text;
import com.nossaclinica_api.utils.Utils;
import com.nossaclinica_api.webserviceclient.WhatsappClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WhatsappService {

    private final WhatsappClient message;
    private final FormaDePagamentoRespository formaDePagamento;

    @Value("${nossaclinica-api.config.whatsapp-api.destino}")
    private String destino;

    public void sendSolicitacaoEstornoDePagamento(User autorizador, Autorizacao autorizacao, List<Long> formasDePagamento) {
        String contatoFormatado = Utils.formatarContato(autorizador.getTelefone());
        String mensagem = montarMessagemDeSolicitacao(autorizacao, formasDePagamento);
        var whatsapp = criarInstanciaDaMensagem(contatoFormatado, mensagem);
        log.info(ConstantesValues.SOLICITACAO_ENVIADA_PARA
                .formatted(Utils.obterPrimeiroNome(autorizador.getNome())));
        message.post(whatsapp);
    }
    public void sendMensagemDeLembreteDaConsultaDoDiaSeguinte(List<AgendaDoPaciente> agendadosDeAmanha) {

        for(var agendado : agendadosDeAmanha) {
            var contatoFormatado = Utils.formatarContato(agendado.getTelefone());
            var mensagem = agendado.toString();
            var whatsapp = criarInstanciaDaMensagem(contatoFormatado, mensagem);
            log.info(ConstantesValues.MENSAGEM_ENVIADO_PARA
                    .formatted(agendado.getPaciente()));
            message.post(whatsapp);
            awaitGap(1500);
        }
    }

    public void sendMensagemDePassagemDaAgendaDoDiaSeguinte(Map<Long, List<AgendaMedica>> agendaDosMedicos) {
        String saudacoes = ConstantesValues.SAUDACAO_DE_BOM_DIA;
        for(var entry : agendaDosMedicos.entrySet()) {
            var contatoFormatado = Utils.formatarContato(entry.getValue().get(0).getTelefone());
            var mensagem = "";
            for(var agendaMedica: entry.getValue()) {
                mensagem += "\n".concat(agendaMedica.toString());
            }
            saudacoes = saudacoes.formatted(
                    Utils.definiPronomeDosProfissionais(entry.getValue().get(0).getSexo(), true),
                    entry.getValue().get(0).getMedico()
            );
            var mensagemMontada = saudacoes.concat(mensagem);
            var whatsapp = criarInstanciaDaMensagem(contatoFormatado, mensagemMontada);
            log.info(ConstantesValues.MENSAGEM_ENVIADO_PARA
                    .formatted(Utils.definiPronomeDosProfissionais(
                            entry.getValue().get(0).getSexo(),  true)+" "
                                    +entry.getValue().get(0).getMedico(), true));
            message.post(whatsapp);
            awaitGap(1500);
        }
    }


    private MessageWhatsapp criarInstanciaDaMensagem(String chatId, String message) {
        var whatsappMessage = new MessageWhatsapp();
        whatsappMessage.setChatId(chatId);
        whatsappMessage.setContentType(ConstantesValues.STRING);
        whatsappMessage.setContent(message);
        return whatsappMessage;
    }

    private String montarMessagemDeSolicitacao(Autorizacao autorizacao, List<Long> ids) {
        var message = new StringBuilder();
        message.append(Text.NEGRITO)
                .append(Text.TITULO_SOLICITACAO_ESTORNO_PAGAMENTO)
                .append(Text.NEGRITO).append(Text.QUEBRA_LINHA).append(Text.QUEBRA_LINHA)

                .append(Text.NEGRITO).append(Text.SOLICITADO_POR).append(Text.NEGRITO).append(Text.QUEBRA_LINHA)
                .append(Utils.formatarNomeSobrenome(autorizacao.getSolicitante())).append(Text.QUEBRA_LINHA).append(Text.QUEBRA_LINHA)

                .append(Text.NEGRITO).append(Text.MOTIVO).append(Text.NEGRITO).append(Text.QUEBRA_LINHA)
                .append(autorizacao.getMotivo()).append(Text.QUEBRA_LINHA).append(Text.QUEBRA_LINHA)

                .append(Text.NEGRITO).append(Text.IMPORTANCIA).append(Text.NEGRITO).append(Text.QUEBRA_LINHA);
        var importancias = formaDePagamento.findInById(ids);
        for(var importancia : importancias) {
            message.append(importancia.toString()).append(Text.QUEBRA_LINHA);
        }
        return message.append(Text.QUEBRA_LINHA)
                .append(Text.NEGRITO).append(Text.CODIGO_AUTORIACAO).append(Text.NEGRITO)
                .append(autorizacao.getHashAutorizacao()).toString();
    }
    private void awaitGap(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
