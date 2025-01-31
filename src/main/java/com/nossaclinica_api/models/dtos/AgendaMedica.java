package com.nossaclinica_api.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nossaclinica_api.utils.Utils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgendaMedica {

    @JsonIgnore
    public static final int ID_ESPECIALISTA = 0;
    @JsonIgnore
    public static final int SEXO = 1;
    @JsonIgnore
    public static final int MEDICO = 2;
    @JsonIgnore
    public static final int TELEFONE = 3;
    @JsonIgnore
    public static final int TIPO_ATENDIMENTO = 4;
    @JsonIgnore
    public static final int STATUS = 5;
    @JsonIgnore
    public static final int TOTAL_AGENDADO = 6;

    private Long idEspecialista;
    private String sexo;
    private String medico;
    private String telefone;
    private String tipoAtendimento;
    private String status;
    private int total;

    public String toString() {
        return String.format("Temos %s %s %s.", this.total,
                Utils.CorrigirSeUSG(this.tipoAtendimento), Utils.FormatarStatus(this.total, this.status));
    }


}
