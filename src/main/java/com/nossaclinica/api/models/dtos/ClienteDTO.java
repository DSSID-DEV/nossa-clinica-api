package com.nossaclinica.api.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nossaclinica.api.validations.anotations.FullName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;


	private Long idCliente;
	
	private ContatoDTO contato;

	private EnderecoDTO endereco;
	
	@FullName
	private String nome;
	
	@CPF(message = "Favor informe o número do CPF válido!")
	private String cpf;
	
	private String rg;
	
	private String orgaoEmissor;
	
	private LocalDate dataDeNascimento;
	
	private UsuarioDTO usuario;

}
