package com.nossaclinica.api.models.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nossaclinica.api.enums.TipoDeRua;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private TipoDeRua tipoLogradouro;
	private String logradouro;
	private String numero;
	private String cep;
	private CidadeDTO cidade;
	private String bairro;

}
