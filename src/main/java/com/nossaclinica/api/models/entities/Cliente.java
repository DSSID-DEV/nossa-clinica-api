package com.nossaclinica.api.models.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
			
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_seq")
	@SequenceGenerator(name = "cliente_id_seq",sequenceName = "cliente_id_seq", initialValue = 1, allocationSize = 1)
	private Long idCliente;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contato_id", nullable = true)
	private Contato contato;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = true)
	private Endereco endereco;
	
	@Column(name = "nome")
	private String nome;
	@CPF(message = "Favor informe o número do CPF válido!")
	@Column(name = "cpf", unique = true, length = 15)
	private String cpf;
	
	@Column(name = "rg", unique = true)
	private String rg;
	
	@Column(name = "emitido_por")
	private String orgaoEmissor;
	
	@Column(name = "nascido_em")
	private LocalDate dataDeNascimento;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "usuario_id", nullable = true)
	private Usuario usuario;
	
	public LocalDate getDataDeNascimento() {
		if (this.dataDeNascimento != null) 
			this.dataDeNascimento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return this.dataDeNascimento;
	}
	
}
