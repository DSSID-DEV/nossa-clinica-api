package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.responses.servicosmedico.ResponseServicoMedico;
import com.nossaclinica_api.models.dtos.ServicosMedicoDTO;
import com.nossaclinica_api.models.entities.ServicosMedico;
import org.modelmapper.ModelMapper;

public class ServicoMedicoConverte {
    public static ServicosMedico praEntity(ServicosMedicoDTO dto) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(dto, ServicosMedico.class);
    }

    public static ServicosMedicoDTO paraDTO(ServicosMedico dto) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(dto, ServicosMedicoDTO.class);
    }

    public static ResponseServicoMedico paraResponseServicosMedicos(ServicosMedico servicosMedico) {
        return ResponseServicoMedico.builder()
                .idServicoMedico(servicosMedico.getIdServicoMedico())
                .idEspecialista(servicosMedico.getEspecialista().getIdEspecialista())
                .idServico(servicosMedico.getServico().getIdServico())
                .nome(servicosMedico.getServico().getNome())
                .descricaoServico(servicosMedico.getServico().getDescricao())
                .descricao(servicosMedico.getDescricao())
                .preco(servicosMedico.getPreco())
                .build();
    }
}
