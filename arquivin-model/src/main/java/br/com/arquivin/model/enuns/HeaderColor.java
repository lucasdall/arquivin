package br.com.arquivin.model.enuns;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.arquivin.model.enuns.deserializer.HeaderColorDeserializer;
import br.com.arquivin.model.enuns.serializer.HeaderColorSerializer;

@JsonSerialize(using = HeaderColorSerializer.class)
@JsonDeserialize(using = HeaderColorDeserializer.class)
public enum HeaderColor {
	
	BLACK("Preto", "background-color: black"), 
	WHITE("Branco", "background-color: white");

	private String desc;
	
	private String backGround;
	
	HeaderColor(String desc, String backGround) {
		this.desc = desc;
		this.backGround = backGround;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}

	public String getBackGround() {
		return backGround;
	}

}
