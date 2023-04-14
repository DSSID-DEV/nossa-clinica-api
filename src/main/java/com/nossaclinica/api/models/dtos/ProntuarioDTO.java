package com.nossaclinica.api.models.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProntuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;
	
	private MedicoEspecialistaDTO medico;
	
	private ClienteDTO cliente;
	
	private List<HistoricoDoProntuarioDTO> historicos;

}
