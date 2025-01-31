package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.requests.movimentacoes.RequestEntradaExame;
import com.nossaclinica_api.models.entities.EntradaExame;
import com.nossaclinica_api.models.entities.Movimentacao;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class EntradaExameConverter {


    public static EntradaExame toEntity(RequestEntradaExame entradaExame) {
        var modelMapper = new ModelMapper();
        var movimentacao = modelMapper.map(entradaExame.getMovimentacao(), Movimentacao.class);
        var idsExames = String.join(", ", entradaExame.getIdsExames());
        return EntradaExame.builder()
               .realizadoEm(LocalDateTime.now())
               .realizadoPor(entradaExame.getRealizadoPor())
               .movimentacao(movimentacao)
                .idExames(idsExames)
               .build();
    }
}
