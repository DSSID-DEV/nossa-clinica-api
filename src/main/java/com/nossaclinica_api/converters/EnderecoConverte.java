package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.EnderecoDTO;
import com.nossaclinica_api.models.entities.Endereco;
import org.modelmapper.ModelMapper;

public class EnderecoConverte {


    public static Endereco paraEntity(EnderecoDTO request) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(request, Endereco.class);
    }
}
