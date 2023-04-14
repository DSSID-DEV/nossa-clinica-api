package com.nossaclinica.api.models.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CidadeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idCidade;

	private String nome;
	
	private String uf;
	
}
