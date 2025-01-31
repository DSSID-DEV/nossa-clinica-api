package com.nossaclinica_api.services;


import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.converters.EspecialidadeMedicaConverte;
import com.nossaclinica_api.models.entities.EspecialidadeMedica;
import com.nossaclinica_api.models.dtos.requests.especialidadesmedica.RequestRqeAreaDeAtuacao;
import com.nossaclinica_api.models.dtos.responses.especialidadesmedica.ResponseEspecialidadeMedica;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.requests.especialidadesmedica.RequestEspecialidadeMedica;
import com.nossaclinica_api.repositories.EspecialidadeMedicaRepository;
import com.nossaclinica_api.repositories.EspecialidadeRepository;
import com.nossaclinica_api.repositories.EspecialistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecialidadeMedicaServices {

    private final EspecialidadeMedicaRepository repository;
    private final EspecialistaRepository medicoRepository;
    private final EspecialidadeRepository especialidadeRepository;

    @Transactional
    public ResponseId registrarNovaEspecialidadeMedico(Long idMedico, RequestEspecialidadeMedica especialidadeMedica) {
       var especialidadeMedicaEntity = new EspecialidadeMedica();
        var especialidade = especialidadeRepository.getById(especialidadeMedica.getIdEspecialidade());
        var medico = medicoRepository.getById(idMedico);
        especialidadeMedicaEntity.setEspecialista(medico);
        especialidadeMedicaEntity.setEspecialidade(especialidade);
        especialidadeMedicaEntity.setRqe(especialidadeMedica.getRqe());
        especialidadeMedicaEntity.setAreaDeAtuacao(especialidadeMedica.getAreaDeAtuacao());

        return ConverteResponseId.daEspecialidadeMedica(repository.save(especialidadeMedicaEntity));
    }

    public ResponseEspecialidadeMedica atualizarEspecializacaoMedica(Long idEspecialidadeDoMedico,
                                                                     RequestRqeAreaDeAtuacao dto) {

        var entity = getEspecialidadeMedica(idEspecialidadeDoMedico);
        entity.setRqe(dto.getRqe());
        entity.setAreaDeAtuacao(dto.getAreaDeAtuacao());
        repository.save(entity);
        return EspecialidadeMedicaConverte.paraResonseEspecialidadeMedicaDTO(entity);
    }

    public EspecialidadeMedica getEspecialidadeMedica(Long idEspecialidadeDoMedico) {
        return repository.getById(idEspecialidadeDoMedico);
    }

    public boolean existsById(Long idEspecialidadeDoMedico) {
        return repository.existsById(idEspecialidadeDoMedico);
    }

    @Transactional
    public void removerEspecialidade(Long idEspecialidadeDoMedico) {
        var entity = getEspecialidadeMedica(idEspecialidadeDoMedico);
        entity.setEspecialista(null);
        entity.setEspecialidade(null);
        repository.save(entity);
        repository.delete(entity);
    }

    public List<ResponseEspecialidadeMedica> listarEspecialidadeDoMedico(Long idMedico) {
        return repository.listarEspecialidadesDoMedico(idMedico)
                .stream()
                .map(EspecialidadeMedicaConverte::paraResonseEspecialidadeMedicaDTO)
                .collect(Collectors.toList());
    }

    public ResponseEspecialidadeMedica buscarEspecialidadeMedicaPorId(Long idEspecialidadeDoMedico) {
        return EspecialidadeMedicaConverte.paraResonseEspecialidadeMedicaDTO(repository.getById(idEspecialidadeDoMedico));
    }

    public boolean exists(Long idMedico, RequestEspecialidadeMedica especialidadeMedica) {
        return repository.existeEsteServicoParaEsseMedico(idMedico,
                especialidadeMedica.getIdEspecialidade(), especialidadeMedica.getRqe(),
                especialidadeMedica.getAreaDeAtuacao());
    }
}
