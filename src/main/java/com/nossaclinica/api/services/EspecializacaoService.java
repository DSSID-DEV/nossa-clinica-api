package com.nossaclinica.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nossaclinica.api.models.entities.Especializacao;
import com.nossaclinica.api.models.tdos.EspecializacaoDTO;
import com.nossaclinica.api.repositories.EspecializacaoRepository;

@Service
public class EspecializacaoService {

	@Autowired
	private EspecializacaoRepository repository;

	private ModelMapper modelMapper;

	public ResponseEntity<List<EspecializacaoDTO>> listarTodos() {

		List<Especializacao> especializacoes = repository.findAll();

		if (especializacoes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(especializacoes.stream().map(e -> toDTO(e)).collect(Collectors.toList()));

	}

	public ResponseEntity<List<EspecializacaoDTO>> listarPorDescricao(String descricao) {
		List<Especializacao> especializacoes = repository.findAllByDescricaoContainingIgnoreCase(descricao);

		if (especializacoes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(especializacoes.stream().map(e -> toDTO(e)).collect(Collectors.toList()));
	}

	public EspecializacaoDTO toDTO(Especializacao entidade) {
		this.modelMapper = new ModelMapper();
		return this.modelMapper.map(entidade, EspecializacaoDTO.class);
	}

	public Especializacao toEntity(EspecializacaoDTO representacao) {
		this.modelMapper = new ModelMapper();
		return this.modelMapper.map(representacao, Especializacao.class);
	}

}