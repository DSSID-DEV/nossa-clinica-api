package com.nossaclinica.api.models.filters;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

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
	@CPF(message = "Este CPF é inválido, favor informe o CPF correto!")
	private String cpf;
	private String rg;
	private String cartaoSus;
	private LocalDate nascidoEm;

}
