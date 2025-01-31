package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.requests.exames.RequestExame;
import com.nossaclinica_api.models.dtos.responses.exames.ResponseExame;
import com.nossaclinica_api.models.entities.Exame;
import com.nossaclinica_api.utils.Utils;
import org.modelmapper.ModelMapper;

public class ExameConverte {

    public static Exame paraEntity(RequestExame requestExame) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(requestExame, Exame.class);
    }

    public static Exame atualizarCampos(Exame entity, RequestExame exame) {
        entity.setDescricao(Utils.verificarString(entity.getDescricao(), exame.getDescricao()));
        entity.setMaterial(Utils.verificarString(entity.getMaterial(), exame.getMaterial()));
        entity.setInterpretacao(Utils.verificarString(entity.getInterpretacao(), exame.getInterpretacao()));
        entity.setPrazo(Utils.verificarString(entity.getPrazo(), exame.getPrazo()));
        entity.setSigla(Utils.verificarString(entity.getSigla(), exame.getSigla()));
        entity.setDoencasRelacionadas(Utils.verificarString(entity.getDoencasRelacionadas(), exame.getDoencasRelacionadas()));
        entity.setTipoExame(Utils.verificarString(entity.getTipoExame(), exame.getTipoExame()));
        entity.setMeioDeColeta(Utils.verificarString(entity.getMeioDeColeta(), exame.getMeioDeColeta()));
        entity.setVolume(Utils.verificarString(entity.getVolume(), exame.getVolume()));
        entity.setPreco(Utils.verificarBigDecimal(entity.getPreco(), exame.getPreco()));
        entity.setCusto(Utils.verificarBigDecimal(entity.getCusto(), exame.getCusto()));
        return entity;
    }

    public static ResponseExame paraResponseExame(Exame exame) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(exame, ResponseExame.class);
    }


}
