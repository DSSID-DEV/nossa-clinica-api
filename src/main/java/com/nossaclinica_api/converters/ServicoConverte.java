package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.requests.servicos.RequestServico;
import com.nossaclinica_api.models.entities.Servico;
import com.nossaclinica_api.utils.Utils;
import com.nossaclinica_api.utils.Verificador;
import org.modelmapper.ModelMapper;

public class ServicoConverte {

    public static Servico paraEntity(RequestServico dto) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(dto, Servico.class);
    }

    public static RequestServico paraDTO(Servico entity) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(entity, RequestServico.class);
    }

    public static void atualizarCampo(Servico entity, RequestServico dto) {
        entity.setDescricao(Utils.verificarString(entity.getDescricao(), dto.getDescricao()));
    }

}
