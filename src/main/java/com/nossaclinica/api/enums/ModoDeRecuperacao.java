package com.nossaclinica.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModoDeRecuperacao {

	EMAIL(0, "EMAIL"),
	SMS(1, "SMS"),
	WHATSAPP(2, "WHATSAPP");
	
	private int codigo;
	private String descricao;	
	
}
