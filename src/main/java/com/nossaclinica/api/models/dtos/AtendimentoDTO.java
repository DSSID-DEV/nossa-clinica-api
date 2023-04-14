package com.nossaclinica.api.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.nossaclinica.api.enums.ConsultaOuRetorno;
import com.nossaclinica.api.enums.NaoSim;
import com.nossaclinica.api.validations.anotations.TemVolta;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AtendimentoDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long idAtendimento;
	
	private LocalDate atendidoEm;
	
	private LocalDate retornouEm;
	
	private ProntuarioDTO prontuario;
	
	private String procedimento;
	
	private ConsultaOuRetorno consultaOuRetorno;
	
	private ClienteDTO cliente;
	
	private MedicoEspecialistaDTO medico;
	
	private NaoSim temVolta;
	
	private int prazoPraVolta;
	
	@TemVolta(message = "Não tem direito a volta")
	public NaoSim getTemVolta() {
		if (this.temVolta != null) {
			this.temVolta = LocalDate.now().compareTo(atendidoEm) > this.prazoPraVolta ? NaoSim.N : NaoSim.S;
		}
		return this.temVolta;
	}
	
	public LocalDate getAtendidoEm() {
		if(this.atendidoEm != null) {
			this.atendidoEm.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return this.atendidoEm;
	}
	
	public LocalDate getRetornouEm() {
		if(this.retornouEm != null) {
			this.retornouEm.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return this.retornouEm;
	}

}
