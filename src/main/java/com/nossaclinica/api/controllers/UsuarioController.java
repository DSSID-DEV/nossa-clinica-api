package com.nossaclinica.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nossaclinica.api.models.summaries.dtos.UserNameNascidoEm;
import com.nossaclinica.api.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("/senha/recuperar")
	public ResponseEntity<UserNameNascidoEm> recuperarSenha(@RequestBody UserNameNascidoEm dados) {
		return service.recuperarSenha(dados);
	}
	
	@PostMapping("/senha/provisoria")
	public ResponseEntity<UserNameNascidoEm> senhaProvisoria(@RequestBody UserNameNascidoEm dados) {
		return service.senhaProvisoria(dados);
	}
	
}
