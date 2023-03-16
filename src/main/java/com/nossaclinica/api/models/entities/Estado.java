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
@Table(name = "estados")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_estado")
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_id_seq")
	@SequenceGenerator(name = "estado_id_seq", sequenceName = "estado_id_seq", 
	initialValue = 1, allocationSize = 1)
	private Long idEstado;
	
	private String nome;
	
	private String uf;

}
