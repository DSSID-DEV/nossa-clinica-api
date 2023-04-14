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
@Table(name = "perfis")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Perfil {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_perfil")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_id_seq")
	@SequenceGenerator(name = "perfil_id_seq",sequenceName = "perfil_id_seq", initialValue = 1, allocationSize = 1)
	private Long idPerfil;
	
	@Column(name = "urlImg")
	private String urlImg;
}
