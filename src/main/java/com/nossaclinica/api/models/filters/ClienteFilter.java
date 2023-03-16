package com.nossaclinica.api.models.filters;

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
public class ClienteFilter {
	
	private String nome;
	private String cpf;
	private String rg;
	private String cartaoSus;
	private LocalDate nascidoEm;

}
