package com.nossaclinica.api.models.dtos;


import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nossaclinica.api.config.json.NaoSimDeserializer;
import com.nossaclinica.api.config.json.NaoSimSerialize;
import com.nossaclinica.api.enums.NaoSim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContatoDTO {
	
	private Long id;
	private String celular;
	@Email
	private String email;
	private String instagram;
	@JsonDeserialize(using = NaoSimDeserializer.class)
	@JsonSerialize(using = NaoSimSerialize.class)
	private NaoSim temWhatsApp;
	
	@JsonDeserialize(using = NaoSimDeserializer.class)
	@JsonSerialize(using = NaoSimSerialize.class)
	private NaoSim temTelegram;
}
