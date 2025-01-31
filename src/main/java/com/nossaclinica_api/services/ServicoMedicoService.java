package com.nossaclinica_api.services;


import com.nossaclinica_api.controllers.common.CheckConflito;
import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.converters.ServicoMedicoConverte;
import com.nossaclinica_api.models.dtos.requests.servicosmedico.RequestServicoMedico;
import com.nossaclinica_api.models.dtos.requests.servicosmedico.RequestServicoPreco;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.responses.servicosmedico.ResponseServicoMedico;
import com.nossaclinica_api.models.entities.ServicosMedico;
import com.nossaclinica_api.repositories.EspecialistaRepository;
import com.nossaclinica_api.repositories.ServicoMedicoRepository;
import com.nossaclinica_api.repositories.ServicoRepository;
import com.nossaclinica_api.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoMedicoService {

    private final ServicoMedicoRepository repository;
    private final ServicoRepository servicoRepository;
    private final EspecialistaRepository medicoRepository;

    @Transactional
    public ResponseId registrarNovoServicoDoEspecialista(Long idMedico, RequestServicoMedico request) {
        var medicoEntity = medicoRepository.getById(idMedico);
        var servicoEntity = servicoRepository.getById(request.getIdServico());
        var entity = new ServicosMedico();
        entity.setEspecialista(medicoEntity);
        entity.setServico(servicoEntity);
        entity.setDescricao(request.getDescricao());
        entity.setPercentagem(Utils.convertToDouble(request.getPercentagem()));
        entity.setReceita(Utils.convertToBigDecimal(request.getReceita()));
        entity.setPreco(Utils.convertToBigDecimal(request.getPreco()));
        return ConverteResponseId.doServicoMedico(repository.save(entity));
    }


    public boolean existServicoMedico(Long idServicoMedico) {
        return repository.existsById(idServicoMedico);
    }

    public ResponseServicoMedico atualizarServicoMedico(Long idServicoMedico, RequestServicoPreco dto) {
        var entity = repository.getById(idServicoMedico);
        entity.setDescricao(dto.getDescricao());
        entity.setReceita(Utils.convertToBigDecimal(dto.getReceita()));
        entity.setPercentagem(Utils.convertToDouble(dto.getPercentagem()));
        entity.setPreco(Utils.convertToBigDecimal(dto.getPreco()));
        return ServicoMedicoConverte.paraResponseServicosMedicos(repository.save(entity));
    }

    public ResponseServicoMedico buscarPorId(Long idServicoMedico) {
        var entity = repository.getById(idServicoMedico);
        return ServicoMedicoConverte.paraResponseServicosMedicos(entity);
    }

    public List<ResponseServicoMedico> listarServicosDoMedico(Long idMedico) {
        return repository.listarServicosDoMedico(idMedico)
                .stream()
                .map(ServicoMedicoConverte::paraResponseServicosMedicos)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removerServico(Long idServicoMedico) {
        var servicoMedico = repository.getById(idServicoMedico);
        servicoMedico.setEspecialista(null);
        servicoMedico.setServico(null);
        repository.save(servicoMedico);
        repository.deleteById(idServicoMedico);
    }

    public CheckConflito checarConflito(Long idMedico, RequestServicoMedico dto) {
        var checarConflito = new CheckConflito();
        checarConflito.setExiste(false);
        if(repository.existsServicoMedico(idMedico, dto.getIdServico(), dto.getDescricao())) {
            checarConflito.setExiste(true);
            checarConflito.setConflito("Já consta em nossa base de dados desse serviço para esse médico");
            checarConflito.setMsgLog(String.format("Já consta em nossa base de dados do serviço: %s para esse médico: %s", dto.getIdServico(), idMedico));
        }
        return checarConflito;
    }

    public boolean existsMedico(Long idMedico) {
        return medicoRepository.existsById(idMedico);
    }
}
