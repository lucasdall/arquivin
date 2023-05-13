package br.com.arquivin.model.enuns;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.arquivin.model.enuns.deserializer.CompanyMainColorDeserializer;
import br.com.arquivin.model.enuns.serializer.CompanyMainColorSerializer;

@JsonSerialize(using = CompanyMainColorSerializer.class)
@JsonDeserialize(using = CompanyMainColorDeserializer.class)
public enum CompanyMainColor {
	
	RED("Vermelho", "{background-color: #db2828 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #db2828", "#db2828"), 
	ORANGE("Laranja", "{background-color: #f2711c !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #f2711c", "#f2711c"),
	YELLOW("Amarelo", "{background-color: #fbbd08 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #fbbd08", "#fbbd08"),
	OLIVER("Verde-oliva", "{background-color: #b5cc18 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #b5cc18", "#b5cc18"),
	GREEN("Verde", "{background-color: #21ba45 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #21ba45", "#21ba45"),
	TEAL("Verde-azulado", "{background-color: #00b5ad !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #00b5ad", "#00b5ad"),
	BLUE("Azul", "{background-color: #2185d0 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #2185d0", "#2185d0"),
	VIOLET("Violeta", "{background-color: #6435c9 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #6435c9", "#6435c9"),
	PURPLE("Roxo", "{background-color: #a333c8 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #a333c8", "#a333c8"),
	PINK("Rosa", "{background-color: #e03997 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #e03997", "#e03997"),
	BROWN("Marron", "{background-color: #a5673f !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #a5673f", "#a5673f"),
	GREY("Cinza", "{background-color: #767676 !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #767676", "#767676"),
	BLACK("Preto", "{background-color: #1b1c1d !important; color: #fff !important; text-shadow: none !important; background-image: none !important;}", "background-color: #1b1c1d", "#1b1c1d");

	private String desc;
	
	private String css;
	
	private String backGround;
	
	private String color;
	
	CompanyMainColor(String desc, String css, String backGround, String color) {
		this.desc = desc;
		this.css = css;
		this.backGround = backGround;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}

	public String getCss() {
		return css;
	}

	public String getColor() {
		return color;
	}

	public String getBackGround() {
		return backGround;
	}

}
