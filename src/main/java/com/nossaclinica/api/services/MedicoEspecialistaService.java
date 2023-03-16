package com.nossaclinica.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nossaclinica.api.models.entities.MedicoEspecialista;
import com.nossaclinica.api.models.tdos.MedicoEspecialistaDTO;
import com.nossaclinica.api.repositories.MedicoEspecialistaRepository;

@Service
public class MedicoEspecialistaService {

	@Autowired
	private MedicoEspecialistaRepository repository;

	private ModelMapper modelMapper;

	public ResponseEntity<MedicoEspecialistaDTO> salvar(MedicoEspecialistaDTO medico) {
		return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(repository.save(toEntity(medico))));
	}

	public ResponseEntity<List<MedicoEspecialistaDTO>> listarTodos() {
		List<MedicoEspecialista> medicos = repository.findAll();

		if (medicos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medicos.stream().map(m -> toDTO(m)).collect(Collectors.toList()));
	}

	public ResponseEntity<List<MedicoEspecialistaDTO>> listarPorNome(String nome) {
		List<MedicoEspecialista> medicos = repository.findAllByNomeContainingIgnoreCase(nome);

		if (medicos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medicos.stream().map(m -> toDTO(m)).collect(Collectors.toList()));
	}

	public ResponseEntity<List<MedicoEspecialistaDTO>> listarPorEspecialidade(Long idEspecialidade) {
		List<MedicoEspecialista> medicos = 
				repository.listarPorEspecialidade(idEspecialidade);

		if (medicos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medicos.stream().map(m -> toDTO(m)).collect(Collectors.toList()));
	}

	public ResponseEntity<MedicoEspecialistaDTO> buscarPorId(Long idMedico) {
		MedicoEspecialista medico = repository.getById(idMedico);
		if (medico == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok().body(toDTO(medico));
	}

	private MedicoEspecialistaDTO toDTO(MedicoEspecialista entity) {
		this.modelMapper = new ModelMapper();
		return this.modelMapper.map(entity, MedicoEspecialistaDTO.class);
	}

	private MedicoEspecialista toEntity(MedicoEspecialistaDTO medico) {
		this.modelMapper = new ModelMapper();
		return this.modelMapper.map(medico, MedicoEspecialista.class);
	}



}
