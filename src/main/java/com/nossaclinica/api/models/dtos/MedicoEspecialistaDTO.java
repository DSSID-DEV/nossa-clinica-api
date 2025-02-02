package com.nossaclinica.api.models.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEspecialistaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
private Long idMedico;
	
	private String nome;
	
	private List<FotoDTO> fotos;
	
	private UsuarioDTO usuario;
	
	private List<EspecialidadeDTO> especialidades;
}
