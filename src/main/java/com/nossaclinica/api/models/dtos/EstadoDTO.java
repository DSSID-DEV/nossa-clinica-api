package com.nossaclinica.api.models.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idEstado;

	private String nome;

	private String uf;

}
