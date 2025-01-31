package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.responses.exames.ResponseExame;
import com.nossaclinica_api.utils.Utils;

public class ConverteExame {
    public static Object paraResponseExame(Object object) {
        return ResponseExame.builder()
                .idExame(Utils.parseLogn(((Object[]) object), ResponseExame.ID_EXAME))
                .descricao(Utils.parseString(((Object[]) object), ResponseExame.DESCRICAO))
                .sigla(Utils.parseString(((Object[]) object), ResponseExame.SIGLA))
                .tipoExame(Utils.parseString(((Object[]) object), ResponseExame.TIPO_EXAME))
                .custo(Utils.parseBigDecimal(((Object[]) object), ResponseExame.CUSTO))
                .preco(Utils.parseBigDecimal(((Object[]) object), ResponseExame.PRECO))
                .material(Utils.parseString(((Object[]) object), ResponseExame.MATERIAL))
                .meioDeColeta(Utils.parseString(((Object[]) object), ResponseExame.MEDIO_DE_COLETA))
                .volume(Utils.parseString(((Object[]) object), ResponseExame.VOLUME))
                .prazo(Utils.parseString(((Object[]) object), ResponseExame.PRAZO))
                .interpretacao(Utils.parseString(((Object[]) object), ResponseExame.INTERPRETACAO))
                .doencasRelacionadas(Utils.parseString(((Object[]) object), ResponseExame.DOENCAS_RELACIONADAS))
                .build();
    }

}
