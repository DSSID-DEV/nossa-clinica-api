package com.nossaclinica.api.models.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nossaclinica.api.enums.NaoSim;
import com.nossaclinica.api.enums.Permissao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long idUsuario;
	private ContatoDTO contato;
	private String userName;
	private String senha;
	private PerfilDTO perfil;
	private Permissao permissao;
	private NaoSim ativo;	
	
}
