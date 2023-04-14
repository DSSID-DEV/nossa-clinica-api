package com.nossaclinica.api.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HistoricoDoProntuarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;
	
	private LocalDate atendidoEm;
	
	private String descricao;
	
	public LocalDate getAtendidoEm() {
		if (this.atendidoEm != null) {
			this.atendidoEm.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return this.atendidoEm;
	}
}
