package com.nossaclinica.api.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nossaclinica.api.enums.StatusDoAgendamento;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agenda")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agenda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idAgenda;
	
	private LocalDate dataDaConsulta;
	
	private LocalDate dataDoRetorno;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private MedicoEspecialista medico;
	
	@Column(name = "status_da_ida")
	@Enumerated(EnumType.ORDINAL)
	private StatusDoAgendamento statusDaIda;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status_da_volta")
	private StatusDoAgendamento statusDaVolta;
	
	
	private Boolean concluiuProcesso;
	
	public LocalDate getDataDoRetorno() {
		if (this.dataDoRetorno != null) {
			this.dataDoRetorno.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return dataDoRetorno;	
	}
	
	public LocalDate getDataDaConsulta() {
		if (this.dataDaConsulta != null) {
			this.dataDaConsulta.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return dataDaConsulta;	
	}
	
	
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
