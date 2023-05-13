package br.com.arquivin.dto;

import java.io.Serializable;

import br.com.arquivin.model.enuns.CompanyMainColor;
import br.com.arquivin.model.enuns.HeaderColor;

public class CompanyDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 79683635934736815L;

	private Long idCompany;

	private String name;

	private String cnpj;

	private HeaderColor headerColor;
	
	private CompanyMainColor mainColor;

	private String fileName;

	private String urlLogo;

	private String email;

	private String phone;

	private String celphone;
	
	private String subDomain;	

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public HeaderColor getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(HeaderColor headerColor) {
		this.headerColor = headerColor;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	public CompanyMainColor getMainColor() {
		return mainColor;
	}

	public void setMainColor(CompanyMainColor mainColor) {
		this.mainColor = mainColor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCelphone() {
		return celphone;
	}

	public void setCelphone(String celphone) {
		this.celphone = celphone;
	}

	public String getSubDomain() {
		return subDomain;
	}

	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}

}
