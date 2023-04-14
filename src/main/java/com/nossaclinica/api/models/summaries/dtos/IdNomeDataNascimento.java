package com.nossaclinica.api.models.summaries.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nossaclinica.api.config.json.LocalDateSerialize;

import lombok.Data;

@Data
public class IdNomeDataNascimento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String contato;
	
	@JsonSerialize(using = LocalDateSerialize.class)
	private LocalDate aniversario;

}
