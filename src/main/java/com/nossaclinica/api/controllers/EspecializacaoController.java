package com.nossaclinica.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nossaclinica.api.models.dtos.EspecializacaoDTO;
import com.nossaclinica.api.services.EspecializacaoService;

@RestController
@RequestMapping("/especializacoes")
public class EspecializacaoController {
	
	@Autowired
	private EspecializacaoService service;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EspecializacaoDTO>> listarTodos() {
		return service.listarTodos();
	}
	
	
	@GetMapping(value = "/por", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EspecializacaoDTO>> listarPorDescricao(@RequestParam(name = "descricao") String descricao) {
		return service.listarPorDescricao(descricao);
	}

}
