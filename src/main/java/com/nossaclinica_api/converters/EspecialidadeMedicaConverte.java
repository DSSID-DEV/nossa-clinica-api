package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.EspecialidadeMedicaDTO;
import com.nossaclinica_api.models.dtos.requests.especialidadesmedica.RequestEspecialidadeMedica;
import com.nossaclinica_api.models.dtos.responses.especialidadesmedica.ResponseEspecialidadeMedica;
import com.nossaclinica_api.models.entities.EspecialidadeMedica;
import org.modelmapper.ModelMapper;

public class EspecialidadeMedicaConverte {


    public static EspecialidadeMedica paraEntity(RequestEspecialidadeMedica especialidadeMedica) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(especialidadeMedica, EspecialidadeMedica.class);
    }

    public static EspecialidadeMedicaDTO paraDTO(EspecialidadeMedica especialidadeMedica) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(especialidadeMedica, EspecialidadeMedicaDTO.class);
    }

    public static ResponseEspecialidadeMedica paraResonseEspecialidadeMedicaDTO(EspecialidadeMedica especialidadeMedica) {
        return ResponseEspecialidadeMedica.builder()
                .idEspecialidadeMedica(especialidadeMedica.getIdEspecialidadeMedica())
                .idEspecialidade(especialidadeMedica.getEspecialidade().getIdEspecialidade())
                .descricao(especialidadeMedica.getEspecialidade().getDescricao())
                .rqe(especialidadeMedica.getRqe())
                .areaDeAtuacao(especialidadeMedica.getAreaDeAtuacao())
                .build();
    }
}
