package com.nossaclinica.api.models.entities;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nossaclinica.api.config.json.NaoSimSerialize;
import com.nossaclinica.api.config.json.PermissaoSerialize;
import com.nossaclinica.api.enums.NaoSim;
import com.nossaclinica.api.enums.Permissao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	@SequenceGenerator(name = "usuario_id_seq",sequenceName = "usuario_id_seq", initialValue = 1, allocationSize = 1)
	private Long idUsuario;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	
	@Column(name = "ativo")
	@JsonSerialize(using = NaoSimSerialize.class)
	@Enumerated(EnumType.ORDINAL)
	private NaoSim ativo;
	

	@Column(name = "permissao")
	@Enumerated(EnumType.ORDINAL)
	@JsonSerialize(using = PermissaoSerialize.class)
	private Permissao permissao;
	
	@OneToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;
	
	public NaoSim getAtivo() {
		if (this.ativo == null)
			return this.ativo = NaoSim.N;
		return this.ativo;
	}
	
	public String getSenha() {
		if (this.senha == null)
			return String.valueOf(new Random()).substring(0, 8);
		return this.senha;		
	}
		
}
