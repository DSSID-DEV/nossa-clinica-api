package com.nossaclinica_api.services;

import com.nossaclinica_api.models.dtos.requests.solicitacoes.RequestSolicitacaoEstornoDePagamento;
import com.nossaclinica_api.models.entities.Autorizacao;
import com.nossaclinica_api.models.entities.User;
import com.nossaclinica_api.models.enums.StatusFormaDePagamento;
import com.nossaclinica_api.repositories.AutorizacaoRepository;
import com.nossaclinica_api.repositories.FormaDePagamentoRespository;
import com.nossaclinica_api.repositories.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorizacaoService {

    private final WhatsappService whatsapp;
    private final RepositoryUser userRepository;
    private final AutorizacaoRepository repository;
    private final FormaDePagamentoRespository formaDePagamentoRepository;


    public boolean existeSolicitacao(List<Long> formasDePagamento) {
        return false;
    }

    @Transactional
    public void solicitarEstornoDePagamento(RequestSolicitacaoEstornoDePagamento solicitacao) {
        String hasAutorizacao = UUID.randomUUID().toString().toUpperCase().substring(0, 6).toLowerCase();
        var autorizador = buscarAutorizador();
        var solicitadoEm = LocalDateTime.now();
        var autorizacao = carregarAutorizacao(solicitacao);
        for(var idFormaDePagamento : solicitacao.getFormasDePagamento()) {
            autorizacao.setHashAutorizacao(hasAutorizacao);
            autorizacao.setAutorizadoPor(autorizador.getNome());
            autorizacao.setSolicitadoEm(solicitadoEm);
            repository.save(autorizacao);
            alterarStatusParaSolicitadoEstorno(idFormaDePagamento, hasAutorizacao);
        }
        whatsapp.sendSolicitacaoEstornoDePagamento(autorizador, autorizacao, solicitacao.getFormasDePagamento());
    }

    private void alterarStatusParaSolicitadoEstorno(Long idFormaDePagamento, String hasAutorizacao) {
        var formaDePagamento = formaDePagamentoRepository.getById(idFormaDePagamento);
        formaDePagamento.setStatus(StatusFormaDePagamento.ESTORNO_SOLICITADO.getDescricao());
        formaDePagamento.setHashAutorizacao(hasAutorizacao);
        formaDePagamentoRepository.save(formaDePagamento);
    }

    private User buscarAutorizador() {
        return userRepository.buscarAutorizador();
    }

    private Autorizacao carregarAutorizacao(RequestSolicitacaoEstornoDePagamento solicitacao) {
        var autorizacao = new Autorizacao();
        autorizacao.setMotivo(solicitacao.getMotivo());
        autorizacao.setSolicitante(solicitacao.getSolicitante());
        autorizacao.setAutorizado(true);
        return autorizacao;
    }
}
