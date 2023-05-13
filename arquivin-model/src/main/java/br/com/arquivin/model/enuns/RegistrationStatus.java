package br.com.arquivin.model.enuns;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.arquivin.model.enuns.deserializer.RegistrationStatusDeserializer;
import br.com.arquivin.model.enuns.serializer.RegistrationStatusSerializer;

@JsonSerialize(using = RegistrationStatusSerializer.class)
@JsonDeserialize(using = RegistrationStatusDeserializer.class)
public enum RegistrationStatus {
	
	INVITED("Convidado"), COMPLETE("Completo");

	private String desc;
	
	RegistrationStatus(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
	
}
