package br.com.arquivin.model.enuns.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.arquivin.model.enuns.RegistrationStatus;

public class RegistrationStatusSerializer extends StdSerializer<RegistrationStatus> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096362715782242985L;

	public RegistrationStatusSerializer() {
		super(RegistrationStatus.class);
	}

	@Override
	public void serialize(RegistrationStatus status, JsonGenerator generator, SerializerProvider provider)
			throws IOException {
		generator.writeStartObject();
		generator.writeFieldName("name");
		generator.writeString(status.name());
		generator.writeFieldName("description");
		generator.writeString(status.toString());
		generator.writeEndObject();
	}

}