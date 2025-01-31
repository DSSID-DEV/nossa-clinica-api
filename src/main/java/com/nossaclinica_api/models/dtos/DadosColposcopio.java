package com.nossaclinica_api.models.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DadosColposcopio {
    public static final int AREA_DE_ANALISE = 0;
    public static final int DESCRICAO = 1;
    public static final int ACHADO = 2;

    private String analse;
    private String descricao;
    private boolean achado;
}
