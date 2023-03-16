package com.nossaclinica.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusDoAgendamento {

	ADIOU(0, "ADIOU"),
	AGENDOU(1, "AGENDOU"),
	CANCELOU(2, "CANCELOU"),
	COMPARECEU(3, "COMPARECEU");
	
	private int codigo;
	private String descricao;
	
}
