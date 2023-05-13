package br.com.arquivin.model.enuns.deserializer;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.arquivin.model.enuns.CompanyMainColor;

public class CompanyMainColorDeserializer extends StdDeserializer<CompanyMainColor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096362715782242985L;

	public CompanyMainColorDeserializer() {
		super(CompanyMainColor.class);
	}
	
	@Override
	public CompanyMainColor deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(jp);
		JsonNode jnName = actualObj.get("name");
		if (jnName != null) {
			String name = jnName.asText();
			if (StringUtils.isNotEmpty(name)) {
				for(CompanyMainColor st : CompanyMainColor.values()) {
					if(st.name().equals(name)) {
						return st;
					}
				}		
			}
		} else {
			return CompanyMainColor.valueOf(actualObj.asText());
		}	
		return null;
	}

}