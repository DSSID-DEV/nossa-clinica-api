package com.nossaclinica.api.models.entities;

import java.io.Serializable;

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
@Table(name = "especializacoes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Especializacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_especializacao")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especializacao_id_seq")
	@SequenceGenerator(name = "especializacao_id_seq",sequenceName = "especializacao_id_seq", initialValue = 1, allocationSize = 1)
	private Long idEspecializacao;

	@Column(name = "descricao", nullable = false)
	private String descricao;

}
