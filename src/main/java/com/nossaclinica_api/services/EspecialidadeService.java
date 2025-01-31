package com.nossaclinica_api.services;


import com.nossaclinica_api.controllers.common.CheckConflito;
import com.nossaclinica_api.converters.EspecialidadeConverte;
import com.nossaclinica_api.models.dtos.requests.especialidades.EspecialidadeDTO;
import com.nossaclinica_api.repositories.EspecialidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecialidadeService {

    private final EspecialidadeRepository repository;

    public CheckConflito checarConflito(EspecialidadeDTO dto) {
        var checarConflito = new CheckConflito();
        checarConflito.setExiste(false);
        if(repository.existsEspecialidade(dto.getDescricao())) {
            checarConflito.setExiste(true);
            checarConflito.setConflito("Especialidade já existe!");
            checarConflito.setMsgLog("Especialidade já existe!");
        }
        return checarConflito;
    }

    public EspecialidadeDTO registrarNovaEspecialidade(EspecialidadeDTO dto) {
        var especialidade = EspecialidadeConverte.paraEntity(dto);
        repository.save(especialidade);
        return EspecialidadeConverte.paraDTO(especialidade);
    }

    public boolean existsById(Long idEspecialidade) {
        return repository.existsById(idEspecialidade);
    }

    public EspecialidadeDTO atualizarEspecialidade(Long idEspecialidade, EspecialidadeDTO dto) {
        var entity = repository.getById(idEspecialidade);
        EspecialidadeConverte.atualizarCampos(entity, dto);
        return EspecialidadeConverte.paraDTO(repository.save(entity));
    }

    public EspecialidadeDTO buscarPorId(Long idEspecialidade) {
        var entity = repository.getById(idEspecialidade);
        return EspecialidadeConverte.paraDTO(repository.save(entity));
    }

    public List<EspecialidadeDTO> listarEspecialidades() {
        return repository.findAll()
                .stream()
                .map(EspecialidadeConverte::paraDTO)
                .collect(Collectors.toList());
    }

    public void removerEspecialidade(Long idEspecialidade) {
        repository.deleteById(idEspecialidade);
    }

    public boolean existsEspecialidade(Long idEspecialidade) {
        return repository.existsById(idEspecialidade);
    }
}
