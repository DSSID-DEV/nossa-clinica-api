package com.nossaclinica_api.services;


import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.converters.ExameConverte;
import com.nossaclinica_api.models.dtos.requests.exames.RequestExame;
import com.nossaclinica_api.models.dtos.requests.instrucao.RequestInstrucaoId;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.responses.exames.ResponseExame;
import com.nossaclinica_api.models.entities.Exame;
import com.nossaclinica_api.repositories.EspecialidadeRepository;
import com.nossaclinica_api.repositories.ExameRepository;
import com.nossaclinica_api.repositories.InstrucaoRepository;
import com.nossaclinica_api.repositories.custom.RepositoryExameCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExameService {

    private final ExameRepository repository;
    private final RepositoryExameCustom repositoryCustom;
    private final InstrucaoRepository instrucaorepository;

    public boolean existsExema(RequestExame exame) {
        return repository.existsExame(exame.getMaterial(),
                exame.getDescricao(), exame.getSigla(),
                exame.getMeioDeColeta());
    }

    @Transactional
    public ResponseId registrarNovoExame(RequestExame requestExame) {
        var exame = ExameConverte.paraEntity(requestExame);
        repository.save(exame);
        if(!requestExame.getInstrucoes().isEmpty()) {
            for(var instrucao : requestExame.getInstrucoes()) {
                relacionarExameNaInstrucao(exame, instrucao);
            }
        }
        return ConverteResponseId.doExame(exame);
    }


    public ResponseId atualizarExame(Long idExame, RequestExame requestExame) {
        var entity = repository.getById(idExame);
        ExameConverte.atualizarCampos(entity, requestExame);
        repository.save(entity);
        if(!requestExame.getInstrucoes().isEmpty()) {
            for(var instrucao : requestExame.getInstrucoes()) {
                relacionarExameNaInstrucao(entity, instrucao);
            }
        }
        return ConverteResponseId.doExame(entity);
    }

    private void relacionarExameNaInstrucao(Exame exame, RequestInstrucaoId request) {
        var instrucao = instrucaorepository.getById(request.getIdInstrucao());
        instrucao.setExame(exame);
        instrucaorepository.save(instrucao);
    }

    public boolean exists(Long idExame) {
        return repository.existsById(idExame);
    }

    public ResponseExame buscarExamePorId(Long idExame) {
        return ExameConverte.paraResponseExame(repository.getById(idExame));
    }

    public List<ResponseExame> listarExames(String tipoExame, String params) {
        return repositoryCustom.lstarExames(tipoExame, params);
    }

    @Transactional
    public void deletarExame(Long idExame) {
        removerInstrucoesDoExame(idExame);
        repository.deleteById(idExame);
    }

    private void removerInstrucoesDoExame(Long idExame) {
        var instrucoes = instrucaorepository.instrucoesDoExame(idExame);
        for(var instrucao : instrucoes) {
            instrucaorepository.delete(instrucao);
        }
    }
}
