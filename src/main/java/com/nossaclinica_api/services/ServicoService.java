package com.nossaclinica_api.services;


import com.nossaclinica_api.controllers.common.CheckConflito;
import com.nossaclinica_api.converters.ServicoConverte;
import com.nossaclinica_api.models.dtos.requests.servicos.RequestServico;
import com.nossaclinica_api.repositories.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository repository;

    public CheckConflito checarConflito(RequestServico dto) {
        var checarServico = new CheckConflito();
        return checarServico;
    }

    public RequestServico registrarNovoServico(RequestServico dto) {
        var entity =  ServicoConverte.paraEntity(dto);
        return ServicoConverte.paraDTO(repository.save(entity));
    }

    public boolean existsById(Long idServico) {
        return repository.existsById(idServico);
    }

    public RequestServico atualizarservico(Long idServico, RequestServico dto) {
        var entity = repository.getById(idServico);
        ServicoConverte.atualizarCampo(entity, dto);
        return ServicoConverte.paraDTO(repository.save(entity));
    }

    public RequestServico buscarPorId(Long idServico) {
        return ServicoConverte.paraDTO(repository.getById(idServico));
    }

    public List<RequestServico> listarServicos() {
        return repository.findAll()
                .stream()
                .map(ServicoConverte::paraDTO)
                .collect(Collectors.toList());
    }

    public void removerServico(Long idServico) {
        repository.deleteById(idServico);
    }
}
