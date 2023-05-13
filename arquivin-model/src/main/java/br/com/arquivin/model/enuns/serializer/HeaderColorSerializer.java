package br.com.arquivin.model.enuns.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.arquivin.model.enuns.HeaderColor;

public class HeaderColorSerializer extends StdSerializer<HeaderColor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096362715782242985L;

	public HeaderColorSerializer() {
		super(HeaderColor.class);
	}

	@Override
	public void serialize(HeaderColor credentialStatus, JsonGenerator generator, SerializerProvider provider)
			throws IOException {
		generator.writeStartObject();
		generator.writeFieldName("name");
		generator.writeString(credentialStatus.name());
		generator.writeFieldName("description");
		generator.writeString(credentialStatus.toString());
		generator.writeFieldName("backGround");
		generator.writeString(credentialStatus.getBackGround());
		generator.writeEndObject();
	}

}