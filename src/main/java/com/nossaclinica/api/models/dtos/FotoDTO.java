package com.nossaclinica.api.models.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class FotoDTO {

	private Long id;
	private String path;
	private String titulo;
	private String descricao;
	private List<FotoDTO> galeriaDeFotos;
	
}
