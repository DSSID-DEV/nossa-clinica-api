package com.nossaclinica.api.models.summaries.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgDeEnvioDaSenhaProvisoriaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String linkDeRecuperacao;
	private String msg;

}
