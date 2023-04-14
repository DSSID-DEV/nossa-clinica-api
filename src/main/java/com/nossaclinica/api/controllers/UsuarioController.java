package com.nossaclinica.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nossaclinica.api.models.summaries.dtos.DadosMascaradosParaRecuperacaoDaSenhaDTO;
import com.nossaclinica.api.models.summaries.dtos.IdUsuarioModoDeRecuperacaoDTO;
import com.nossaclinica.api.models.summaries.dtos.MsgDeEnvioDaSenhaProvisoriaDTO;
import com.nossaclinica.api.models.summaries.dtos.UserNameRecuperarSenha;
import com.nossaclinica.api.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping(value = "/senha/recuperar", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DadosMascaradosParaRecuperacaoDaSenhaDTO>> recuperarSenha(@RequestBody UserNameRecuperarSenha dados) {
		return service.recuperarSenha(dados);
	}
	
	@PostMapping(value = "/senha/provisoria", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MsgDeEnvioDaSenhaProvisoriaDTO> senhaProvisoria(@RequestBody IdUsuarioModoDeRecuperacaoDTO dados) {
		return service.senhaProvisoria(dados);
	}
	
}
