package com.nossaclinica.api.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nossaclinica.api.enums.StatusDoAgendamento;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AgendaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idAgenda;
	
	private LocalDate dataDaConsulta;
	
	private LocalDate dataDoRetorno;
	
	private ClienteDTO cliente;
	
	private MedicoEspecialistaDTO medico;
	
	private StatusDoAgendamento statusDaIda;
	
	private StatusDoAgendamento statusDaVolta;
	
	private Boolean concluiuProcesso;
	
	public Boolean isConcluiuProcesso() {
		this.concluiuProcesso = statusDaIda.getDescricao()
				.equals(StatusDoAgendamento.COMPARECEU.getDescricao()) && 
				(
						(statusDaVolta.getDescricao().equals(StatusDoAgendamento.COMPARECEU.getDescricao()) 
					||	(statusDaVolta.getDescricao().equals(StatusDoAgendamento.AGENDOU.getDescricao()) && 
							LocalDate.now().compareTo(dataDaConsulta) > 15))
						); 
		return this.concluiuProcesso;
		
	}
}
