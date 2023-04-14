package com.nossaclinica.api.models.summaries.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nossaclinica.api.enums.ModoDeRecuperacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdUsuarioModoDeRecuperacaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	private ModoDeRecuperacao recuperarPor;
	
	private String recuperarComEsseDado;
	
}
