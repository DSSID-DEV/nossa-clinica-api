package com.nossaclinica.api.models.summaries.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class IdSiglaNomeRestrincaoValor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String sigla;
	
	private String nome;
	
	private String restrincao;
	
	private BigDecimal valor;
	
	

}
