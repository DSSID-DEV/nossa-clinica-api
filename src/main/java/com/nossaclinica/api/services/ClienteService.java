package com.nossaclinica.api.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.nossaclinica.api.models.entities.Cliente;
import com.nossaclinica.api.models.filters.ClienteFilter;
import com.nossaclinica.api.models.tdos.ClienteDTO;
import com.nossaclinica.api.repositories.ClienteRepository;

@Service
@Component
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private Logger logger;
	
	private ModelMapper modelMapper;
	
	private ClienteDTO toDTO(Cliente entity) {
		modelMapper = new ModelMapper();
		return modelMapper.map(entity, ClienteDTO.class);
	}
	
	
	private Cliente toEntity(ClienteDTO dto) {
		modelMapper = new ModelMapper();
		return modelMapper.map(dto, Cliente.class);
	}
	
	public ResponseEntity<ClienteDTO> salvar(ClienteDTO dto) {
		
		try{
			return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(toEntity(dto)));
		}catch (BadRequest e) {
			return ResponseEntity.badRequest().build();
		}catch (Exception e) {	
			logger.error(String.format("Erro ao tentar salvar novo cliente", e.getMessage()));
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<Boolean> atualizar(ClienteDTO cliente) {
		
		try{
			repository.save(toEntity(cliente));
			return ResponseEntity.ok(Boolean.TRUE);
		}catch (BadRequest e) {
			return ResponseEntity.badRequest().build();
		}catch (Exception e) {
			logger.error(String.format("Erro ao tentar atualizar novo cliente", e.getMessage()));
			return ResponseEntity.internalServerError().build();
		}
	}

	
	public ResponseEntity<ClienteDTO> buscarPorFiltro(ClienteFilter filtro) {
		try {
//			return ResponseEntity.ok(toDTO(repository.buscarPorFiltro(filtro)));	
			return ResponseEntity.ok(toDTO(repository.getById(null)));
		} catch (NoResultException e) {
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error(String.format("Erro ao tentar buscar cliente por filtro", e.getMessage()));
			return ResponseEntity.internalServerError().build();
		}
		
	}
	
	public ResponseEntity<List<ClienteDTO>> findAll() {
		try {
			return ResponseEntity.ok(new ArrayList<ClienteDTO>());
		} catch (NoResultException e) {
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error(String.format("Erro ao tentar listar todos os clientes", e.getMessage()));
			return ResponseEntity.internalServerError().build();
		}
	}


	public ResponseEntity<Boolean> remover(Long id) {
		try{
			return ResponseEntity.ok(Boolean.TRUE);
		}catch (BadRequest e) {
			return ResponseEntity.badRequest().build();
		}catch (Exception e) {	
			logger.error(String.format("Erro ao tentar excluir cliente", e.getMessage()));
			return ResponseEntity.internalServerError().build();
		}
	}

	

}
