package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.DadosColposcopio;
import com.nossaclinica_api.models.dtos.responses.colposcopia.ResponseAnaliseColposcopio;
import com.nossaclinica_api.models.entities.AnaliseColposcopio;
import com.nossaclinica_api.utils.Utils;
import org.modelmapper.ModelMapper;

public class ConverteAnaliseColposcopio {
    public static ResponseAnaliseColposcopio paraResponseAnaliseColposcopio(AnaliseColposcopio analiseColposcopio) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(analiseColposcopio, ResponseAnaliseColposcopio.class);
    }

    public static DadosColposcopio paraDadosColpospio(Object analise) {
        return DadosColposcopio.builder()
                .analse(Utils.parseString((Object[]) analise, DadosColposcopio.AREA_DE_ANALISE))
                .descricao(Utils.parseString((Object[]) analise, DadosColposcopio.DESCRICAO))
                .achado(Utils.parseBoolean((Object[]) analise, DadosColposcopio.ACHADO))
                .build();
    }
}
