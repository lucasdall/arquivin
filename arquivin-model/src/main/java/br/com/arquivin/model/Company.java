package br.com.arquivin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.arquivin.model.enuns.CompanyMainColor;
import br.com.arquivin.model.enuns.HeaderColor;

@Entity
@Table(name = "company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 79683635934736815L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_company")
	@SequenceGenerator(sequenceName = "seq_company", allocationSize = 1, name = "seq_company")
	@Column(name = "id_company")
	private Long idCompany;

	@Column(length=50, nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private HeaderColor headerColor;
	
	@Enumerated(EnumType.STRING)
	private CompanyMainColor mainColor;
	
	private String fileName;
	
	private String urlLogo;
	
	@Column(length=50, nullable = false)
	private String email;
	
	@Column(length=15)	
	private String phone;
	
	@Column(length=15)	
	private String celphone;

	@Column(length=14, nullable = false)
	private String cnpj;
	
	@Column(unique = true, length = 20, nullable = false)
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

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
