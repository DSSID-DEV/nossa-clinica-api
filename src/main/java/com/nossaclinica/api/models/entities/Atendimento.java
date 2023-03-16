package com.nossaclinica.api.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.convert.JodaTimeConverters.LocalDateTimeToDateConverter;

import com.nossaclinica.api.enums.ConsultaOuRetorno;
import com.nossaclinica.api.enums.NaoSim;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atendimento implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private Long idAtendimento;
	
	private LocalDate atendidoEm;
	
	private LocalDate retornouEm;
	
	private String numeroDoProntuario;
	
	private String procedimento;
	
	private ConsultaOuRetorno consultaOuRetorno;
	
	private Cliente cliente;
	
	private MedicoEspecialista medico;
	
	private NaoSim temVolta;
	
	
	public NaoSim getTemVolta() {
		if (this.temVolta != null) {
			this.temVolta = LocalDate.now().compareTo(atendidoEm) > 15 ? NaoSim.N : NaoSim.S;
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
