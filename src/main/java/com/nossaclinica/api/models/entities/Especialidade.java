package com.nossaclinica.api.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "especialidades")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Especialidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_especialidade")
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especiladade_id_seq")
	@SequenceGenerator(name = "especiladade_id_seq", sequenceName = "especiladade_id_seq", 
	allocationSize = 1, initialValue = 1)
	private Long idEspecialidade;
	
	private String descricao;
	
	private String crm;
	
	@ManyToMany(mappedBy = "especialidades", fetch = FetchType.LAZY)
	private List<MedicoEspecialista> medicos;
	

}
