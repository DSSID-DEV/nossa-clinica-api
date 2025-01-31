package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.BaseDeCalculo;
import com.nossaclinica_api.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BaseDeCalculoConverter {
    public static List<BaseDeCalculo> paraBaseDeCalculo(List resultList) {
        List<BaseDeCalculo> valores = new ArrayList<>();
        for(var base: resultList) {
            var baseCalculo = BaseDeCalculo.builder()
                    .idOrigem(Utils.parseLogn((Object[]) base, BaseDeCalculo.ID_ORIGEM))
                    .qtdTipoServico(Utils.parseInt((Object[]) base, BaseDeCalculo.TOTAL_TIPO_SERVICO))
                    .servico(Utils.parseString((Object[]) base, BaseDeCalculo.SERVICO))
                    .percentagem(Utils.parseDouble((Object[]) base, BaseDeCalculo.PERCENTAGEM))
                    .receitaFixada(Utils.parseBigDecimal((Object[]) base, BaseDeCalculo.RECEITA_FIXADA))
                    .valorSomado(Utils.parseBigDecimal((Object[]) base, BaseDeCalculo.TOTAL_SOMA_TIPO_SERVICO))
                    .valorCalculado(Utils.parseBigDecimal((Object[]) base, BaseDeCalculo.CALCULO_DO_ATENDIMENTO))
                    .build();
            valores.add(baseCalculo);
        }
        return valores;
    }
}
