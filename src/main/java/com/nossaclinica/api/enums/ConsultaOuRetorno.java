package com.nossaclinica.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConsultaOuRetorno {

	CONSULTA(0, "CONSULTA"),
	RETORNO(1, "RETORNO");
	
	private int codigo;
	private String descricao;
}
