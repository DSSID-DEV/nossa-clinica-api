package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.requests.instrucao.RequestInstrucao;
import com.nossaclinica_api.models.dtos.responses.instrucao.ResponseInstrucao;
import com.nossaclinica_api.models.entities.Instrucao;
import org.modelmapper.ModelMapper;

public class InstrucaoConverte {


    public static Instrucao paraEntity(RequestInstrucao instrucao) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(instrucao, Instrucao.class);
    }

    public static ResponseInstrucao paraResponseInstrucao(Instrucao entity) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(entity, ResponseInstrucao.class);
    }
}
