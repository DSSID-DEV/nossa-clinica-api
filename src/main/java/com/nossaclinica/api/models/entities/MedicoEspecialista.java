package com.nossaclinica.api.models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedicoEspecialista {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medico_id_seq")
	@SequenceGenerator(name = "medico_id_seq", sequenceName = "medico_id_seq", 
	initialValue = 1, allocationSize = 1)
	@Column(name = "id_medico")
	private Long idMedico;
	
	private String nome;
	
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "medicos_fotos", joinColumns = 
	@JoinColumn(name="medico_id"), 
	inverseJoinColumns = @JoinColumn(name="foto_id"))
	private List<Foto> fotos;
	
	@OneToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
	private Usuario usuario;
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name = "medicos_especialidades", 
	joinColumns = @JoinColumn(name="medico_id"),
	inverseJoinColumns = @JoinColumn(name = "especialista_id"))
	private List<Especialidade> especialidades;

}
