package com.nossaclinica.api.models.summaries.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosMascaradosParaRecuperacaoDaSenhaDTO {
	
	private Long idUsuario;
	
	private String Nome;
	
	private String email;
	
	private String whatsApp;
	
	private String sms;
	
	
}
