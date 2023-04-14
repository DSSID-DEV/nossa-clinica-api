package com.nossaclinica.api.config.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.nossaclinica.api.enums.NaoSim;


public class NaoSimDeserializer extends StdDeserializer<NaoSim>{

	private static final long serialVersionUID = 1L;

	protected NaoSimDeserializer() {
		super(NaoSim.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public NaoSim deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if (p != null) {
			return p.getValueAsBoolean() ? NaoSim.S : NaoSim.N;
		}
		return null;
	}

}
