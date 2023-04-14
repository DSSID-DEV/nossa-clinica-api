package com.nossaclinica.api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nossaclinica.api.config.json.NaoSimSerialize;
import com.nossaclinica.api.enums.NaoSim;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contatos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contato {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_contato")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_id_seq")
	@SequenceGenerator(name = "contato_id_seq",sequenceName = "contato_id_seq", initialValue = 1, allocationSize = 1)
	private Long idContato;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "instagram")
	private String instagram;
		
	@Column(name = "email")
	private String email;
	
	@Column(name = "tem_whatsapp")
	@JsonSerialize(using = NaoSimSerialize.class)
	@Enumerated(EnumType.ORDINAL)
	private NaoSim temWhatsApp;
	
	public NaoSim getIsWhatsApp() {
		if (this.temWhatsApp == null) 
			return this.temWhatsApp = NaoSim.N;
		return this.temWhatsApp;
	}

}
