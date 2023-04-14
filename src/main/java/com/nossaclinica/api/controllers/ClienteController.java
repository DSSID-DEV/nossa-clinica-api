package com.nossaclinica.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nossaclinica.api.models.dtos.ClienteDTO;
import com.nossaclinica.api.models.dtos.ContatoDTO;
import com.nossaclinica.api.models.dtos.EnderecoDTO;
import com.nossaclinica.api.models.dtos.UsuarioDTO;
import com.nossaclinica.api.models.filters.ClienteFilter;
import com.nossaclinica.api.models.summaries.dtos.IdNomeDTO;
import com.nossaclinica.api.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired 
	private ClienteService service;
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteDTO cliente) {
		return service.salvar(cliente);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> atualizar(@Valid @RequestBody ClienteDTO cliente) {
		return service.atualizar(cliente);
	}
	
	@PutMapping(value = "/{id}/update/endereco", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> atualizarEndereco(@PathVariable(name = "id") Long id, @RequestBody EnderecoDTO endereco) {
		return service.atualizarEndereco(id, endereco);		
	}
	
	@PutMapping(value = "/{id}/update/contato", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> atualizarContato(@PathVariable(name = "id") Long id, @RequestBody ContatoDTO contato) {
		return service.atualizarContato(id, contato);		
	}
	
	@PutMapping(value = "/{id}/update/usuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> atualizarUsuario(@PathVariable(name = "id") Long id, @RequestBody UsuarioDTO usuario) {
		return service.atualizarUsuario(id, usuario);		
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> listarTodos() {
		return service.listarTodos();
	}
		
	
	@GetMapping(path = "/do", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> buscarPorFiltro(@Valid @RequestBody ClienteFilter filter) {
				return service.buscarPorFiltro(filter);		
	}
	
	@GetMapping(path = "/resumo/id-nome", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<IdNomeDTO>> buscarIdNomeDoCliente(@Valid @RequestBody ClienteFilter filter) {
				return service.buscarIdNomeDoCliente(filter);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable(value = "id") Long id){
		return service.remover(id);
	}

}
