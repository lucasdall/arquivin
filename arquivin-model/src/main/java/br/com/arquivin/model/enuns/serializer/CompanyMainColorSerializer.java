package br.com.arquivin.model.enuns.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.arquivin.model.enuns.CompanyMainColor;

public class CompanyMainColorSerializer extends StdSerializer<CompanyMainColor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096362715782242985L;

	public CompanyMainColorSerializer() {
		super(CompanyMainColor.class);
	}

	@Override
	public void serialize(CompanyMainColor credentialStatus, JsonGenerator generator, SerializerProvider provider)
			throws IOException {
		generator.writeStartObject();
		generator.writeFieldName("name");
		generator.writeString(credentialStatus.name());
		generator.writeFieldName("description");
		generator.writeString(credentialStatus.toString());
		generator.writeFieldName("css");
		generator.writeString(credentialStatus.getCss());
		generator.writeFieldName("color");
		generator.writeString(credentialStatus.getColor());
		generator.writeEndObject();
	}

}