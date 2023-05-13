package br.com.arquivin.dto;

import java.io.Serializable;

public class PerfilDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8058722113741822198L;

	private Long idPerfil;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

}
