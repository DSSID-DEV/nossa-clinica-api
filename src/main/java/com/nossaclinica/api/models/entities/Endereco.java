package com.nossaclinica.api.models.entities;

import java.io.Serializable;

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

import com.nossaclinica.api.enums.TipoDeRua;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enderecos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_endereco")
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
	@SequenceGenerator(name = "endereco_id_seq",sequenceName = "endereco_id_seq", initialValue = 1, allocationSize = 1)
	private Long idEndereco;
	
	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "tipo_de_rua")
	@Enumerated(EnumType.ORDINAL)
	private TipoDeRua tipoLogradouro;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cep")
	private String cep;
	
	@OneToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

}
