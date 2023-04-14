package com.nossaclinica.api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidades")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

	@Id
	@Column(name = "id_cidade")
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_id_seq")
	@SequenceGenerator(name = "cidade_id_seq", sequenceName = "cidade_id_seq", 
	initialValue = 1, allocationSize = 1)
	private Long idCidade;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "uf")
	private String uf;	

}
