package com.nossaclinica.api.models.entities;

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
public class HistoricoDoProntuario {

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
