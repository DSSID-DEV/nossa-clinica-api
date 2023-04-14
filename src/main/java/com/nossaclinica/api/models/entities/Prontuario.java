package com.nossaclinica.api.models.entities;

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
public class Prontuario {
	
	@EqualsAndHashCode.Include
	private Long id;
	
	private MedicoEspecialista medico;
	
	private Cliente cliente;
	
	private List<HistoricoDoProntuario> historicos;

}
