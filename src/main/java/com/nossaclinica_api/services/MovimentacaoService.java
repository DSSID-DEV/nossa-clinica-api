package com.nossaclinica_api.services;


import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.converters.EntradaExameConverter;
import com.nossaclinica_api.models.dtos.FormaDePagamentoDTO;
import com.nossaclinica_api.models.dtos.requests.movimentacoes.RequestEntradaExame;
import com.nossaclinica_api.models.dtos.requests.movimentacoes.RequestEstorno;
import com.nossaclinica_api.models.dtos.requests.movimentacoes.RequestMovimentacao;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.responses.movimentacoes.ResponseConfirmacaoEstorno;
import com.nossaclinica_api.models.dtos.responses.movimentacoes.ResponseFormaDePagamento;
import com.nossaclinica_api.models.entities.Agendamento;
import com.nossaclinica_api.models.entities.FormaDePagamento;
import com.nossaclinica_api.models.entities.Movimentacao;
import com.nossaclinica_api.models.entities.NumeroAtendimento;
import com.nossaclinica_api.models.enums.StatusFormaDePagamento;
import com.nossaclinica_api.repositories.*;
import com.nossaclinica_api.repositories.custom.NumeroAtendimentoRepsitoryCustom;
import com.nossaclinica_api.repositories.custom.RepositoryMovimentacaoCustom;
import com.nossaclinica_api.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private static final String CANCELADO = "Cancelado";

    private final MovimentacaoRepository repository;
    private final AgendamentoRepository agendamentoRepository;
    private final RepositoryMovimentacaoCustom repositoryCustom;
    private final EntradaDeExameRepository entradaExameRepository;
    private final NumeroAtendimentoRepsitory numeroRegistroRepository;
    private final NumeroAtendimentoRepsitoryCustom numeroRegistroCustom;
    private final FormaDePagamentoRespository formaDePagamentoRepository;


    @Transactional
    public ResponseId registrarNovaMovimentacao(RequestMovimentacao request) {
        var agenda = agendamentoRepository.getById(request.getIdAgendamento());
        var movimentacao = Movimentacao.builder()
                .pago(true)
                .agendamento(agenda)
                .prestador(agenda.getEspecialista().getNomeSocial())
                .servico(request.getServico()).build();
        repository.save(movimentacao);
        registrarFormasDePagamento(request.getFormasDePagamento(), movimentacao);
        autalizarAgenda(request.getIdAgendamento());
        criarNumeroDeAtendimento(agenda);
        return ConverteResponseId.doMovimentacao(movimentacao);
    }

    @Transactional
    private void criarNumeroDeAtendimento(Agendamento agenda) {
        var ultimoRegistro = numeroRegistroCustom
                .obterUltimoNumeroAtendimento(agenda);

        var atendimento = new NumeroAtendimento();
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        atendimento.setDataDoAtendimento(LocalDateTime.of(agenda.getAgendadoPara(), LocalTime.now(zoneId)));
        atendimento.setEspecialidade(agenda.getEspecialidade());
        atendimento.setEspecialista(agenda.getEspecialista());
        atendimento.setCliente(agenda.getCliente());
        atendimento.setTipoDeAtendimento(agenda.getServico().getNome());
        atendimento.setNumeroAtendimento(ultimoRegistro);
        numeroRegistroRepository.save(atendimento);
    }

    private void autalizarAgenda(Long idAgendamento) {
        var agenda = agendamentoRepository.getById(idAgendamento);
        agenda.setPago(true);
    }

    private void registrarFormasDePagamento(List<FormaDePagamentoDTO> pagamentosCom, Movimentacao movimentacao) {
        for(var pagamentoCom : pagamentosCom) {
            var formaDePagamento = new FormaDePagamento();
            formaDePagamento.setMovimentacao(movimentacao);
            formaDePagamento.setStatus(StatusFormaDePagamento.CONSOLIDADO.getDescricao());
            formaDePagamento.setValor(Utils.convertToBigDecimal(pagamentoCom.getValor()));
            formaDePagamento.setPagaCom(pagamentoCom.getCom());
            formaDePagamentoRepository.save(formaDePagamento);
        }
    }

    public boolean pagamentoJaRealizado(RequestMovimentacao movimentacao) {
        var agenda = agendamentoRepository.getById(movimentacao.getIdAgendamento());
        return (boolean) repositoryCustom.pagamentoJaRealizado(movimentacao.getIdAgendamento(),
                movimentacao.getServico(),
                agenda.getEspecialista().getNomeSocial());
    }

    public ResponseConfirmacaoEstorno estornarPagamento(RequestEstorno estorno) {
        var formasDePagamento = formaDePagamentoRepository.findInById(estorno.getIdsFormaDePagamento());
         List<String> pagamentos = new ArrayList<>();
        for(var formaDePagamento : formasDePagamento) {
            if(formaDePagamento.getHashAutorizacao()
                    .equalsIgnoreCase(estorno.getHashAutorizacao())) {
                formaDePagamento.setStatus(StatusFormaDePagamento.ESTORNADO.getDescricao());
                formaDePagamentoRepository.save(formaDePagamento);
                pagamentos.add(formaDePagamento.toString());
            }
        }
        Long idMovimentacao = formasDePagamento.get(0).getMovimentacao().getIdMovimentacao();
        var movimentacao = repository.getById(idMovimentacao);
        movimentacao.setPago(false);
        repository.save(movimentacao);

        cancelarAgenda(movimentacao.getAgendamento().getIdAgendamento());

        return ResponseConfirmacaoEstorno.builder()
                .mensagem("Estorno realizado com sucesso!")
                .estornados(pagamentos)
                .build();
    }

    private void cancelarAgenda(Long idAgendamento) {
        var agenda = agendamentoRepository.getById(idAgendamento);
        agenda.setStatus(CANCELADO);
        agenda.setPago(false);
        agendamentoRepository.save(agenda);
    }

    public ResponseFormaDePagamento listarPagamentosRealizados(Long idAgenda) {
        return repositoryCustom.listarPagamentos(idAgenda);
    }

    public void registrarEntradaDeExame(RequestEntradaExame entradaExame) {
        var entity = EntradaExameConverter.toEntity(entradaExame);
        entity.setRealizadoEm(LocalDateTime.now());
        entradaExameRepository.save(entity);
    }
}
