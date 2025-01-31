package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.requests.especialidades.EspecialidadeDTO;
import com.nossaclinica_api.models.entities.Especialidade;
import com.nossaclinica_api.utils.Utils;
import com.nossaclinica_api.utils.Verificador;
import org.modelmapper.ModelMapper;

public class EspecialidadeConverte {
    public static Especialidade paraEntity(EspecialidadeDTO especialidade) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(especialidade, Especialidade.class);
    }

    public static EspecialidadeDTO paraDTO(Especialidade especialidade) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(especialidade, EspecialidadeDTO.class);
    }

    public static void atualizarCampos(Especialidade entity, EspecialidadeDTO dto) {
        entity.setDescricao(Utils.verificarString(entity.getDescricao(), dto.getDescricao()));
    }

}
