package com.nossaclinica_api.services;

import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.converters.InstrucaoConverte;
import com.nossaclinica_api.models.dtos.requests.instrucao.RequestInstrucao;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.responses.instrucao.ResponseInstrucao;
import com.nossaclinica_api.repositories.InstrucaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstrucaoService {

    private final InstrucaoRepository repository;

    public ResponseId registrarNovoinstrucao(RequestInstrucao instrucao) {
        var entity = InstrucaoConverte.paraEntity(instrucao);
        return ConverteResponseId.daInstrucaoExame(repository.save(entity));
    }

    public boolean exists(Long idInstrucao) {
        return repository.existsById(idInstrucao);
    }

    public ResponseId atualizarInstrucao(Long idInstrucao, RequestInstrucao instrucao) {
        var entity = repository.getById(idInstrucao);
        entity.setTipoInstrucao(instrucao.getTipoInstrucao());
        entity.setDescricao(instrucao.getDescricao());
        return ConverteResponseId.daInstrucaoExame(repository.save(entity));
    }

    public ResponseInstrucao buscarInstrucaoPorId(Long idInstrucao) {
        var entity = repository.getById(idInstrucao);
        return InstrucaoConverte.paraResponseInstrucao(entity);
    }

    public List<ResponseInstrucao> listaInstrucoes() {
        return repository.findAll().stream()
                .map(InstrucaoConverte::paraResponseInstrucao)
                .collect(Collectors.toList());
    }

    public List<ResponseInstrucao> listaInstrucoesDoExame(Long idExame) {
        return repository.instrucoesDoExame(idExame)
                .stream()
                .map(InstrucaoConverte::paraResponseInstrucao)
                .collect(Collectors.toList());
    }

    public void deletarinstrucao(Long idInstrucao) {
        repository.deleteById(idInstrucao);
    }
}
