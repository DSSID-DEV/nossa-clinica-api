package com.nossaclinica.api.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PerfilDTO {
	
	private Long id;
	private String path;
}
