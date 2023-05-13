package br.com.arquivin.dto;

import java.io.Serializable;

import br.com.arquivin.model.enuns.CompanyMainColor;
import br.com.arquivin.model.enuns.HeaderColor;

public class CompanyConfigDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3689174184344821593L;

	private HeaderColor headerColor;

	private CompanyMainColor mainColor;

	private String urlLogo;

	public HeaderColor getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(HeaderColor headerColor) {
		this.headerColor = headerColor;
	}

	public CompanyMainColor getMainColor() {
		return mainColor;
	}

	public void setMainColor(CompanyMainColor mainColor) {
		this.mainColor = mainColor;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

}
