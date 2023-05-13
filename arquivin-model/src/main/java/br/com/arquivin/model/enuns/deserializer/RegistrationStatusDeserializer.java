package br.com.arquivin.model.enuns.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.arquivin.model.enuns.RegistrationStatus;

public class RegistrationStatusDeserializer extends StdDeserializer<RegistrationStatus> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096362715782242985L;

	public RegistrationStatusDeserializer() {
		super(RegistrationStatus.class);
	}
	
	@Override
	public RegistrationStatus deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		jp.nextValue();
		for(RegistrationStatus st : RegistrationStatus.values()) {
			if(st.name().equals(jp.getText())) {
				return st;
			}
		}		
		return null;
	}

}