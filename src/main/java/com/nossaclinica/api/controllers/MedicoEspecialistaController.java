package com.nossaclinica.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nossaclinica.api.models.dtos.MedicoEspecialistaDTO;
import com.nossaclinica.api.services.MedicoEspecialistaService;

@RestController
@RequestMapping("/medicos")
public class MedicoEspecialistaController {
	
	@Autowired
	private MedicoEspecialistaService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicoEspecialistaDTO> salvar(@RequestBody MedicoEspecialistaDTO medico) {
		return service.salvar(medico);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicoEspecialistaDTO>> listarTodos() {
		return service.listarTodos();
	}
	
	@GetMapping(value = "/por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicoEspecialistaDTO>> ListarPorNome(@RequestParam(value = "nome") String nome) {
		return service.listarPorNome(nome);
	}
	
	@GetMapping(value = "/da/especialidade", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicoEspecialistaDTO>> listarPorEspecialidade(@RequestParam(value = "idEspecialidade") Long idEspecialidade) {
		return service.listarPorEspecialidade(idEspecialidade);
	}
	
	@GetMapping(value = "/por-id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicoEspecialistaDTO> buscarPorId(@RequestParam(value = "idMedico") Long idMedico) {
		return service.buscarPorId(idMedico);
	}
	

}
