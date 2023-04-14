package com.nossaclinica.api.models.summaries.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserNameRecuperarSenha implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String username;
	
	private Boolean recuperarSenha;
	
		
}
