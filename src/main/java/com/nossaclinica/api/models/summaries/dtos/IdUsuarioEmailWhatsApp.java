package com.nossaclinica.api.models.summaries.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdUsuarioEmailWhatsApp {
	
	private Long idUsuario;
	
	private String senhaProvisoria;
	
	private String email;
	
	private String whatsApp;
	
	private boolean viaEmail;
	
	private boolean viaWhatsApp;


}
