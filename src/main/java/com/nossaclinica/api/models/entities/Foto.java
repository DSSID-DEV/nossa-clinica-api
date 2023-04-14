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
@Table(name = "fotos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)  
public class Foto {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_foto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foto_id_seq")
	@SequenceGenerator(name = "foto_id_seq", sequenceName = "foto_id_seq", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@Column(name = "urlImg")
	private String urlImg;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;
	
}
