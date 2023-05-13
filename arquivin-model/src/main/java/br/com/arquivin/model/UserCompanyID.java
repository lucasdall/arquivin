package br.com.arquivin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserCompanyID implements Serializable {

	private static final long serialVersionUID = -3030084552086474929L;

	@Column(name = "fk_user")
	private Long idUser;

	@Column(name = "fk_company")
	private Long idCompany;

	public UserCompanyID() {
	}

	public UserCompanyID(Long idUser, Long idCompany) {
		this.idUser = idUser;
		this.idCompany = idCompany;
	}
	
	public UserCompanyID(User user, Company company) {
		this.idUser = user.getIdUser();
		this.idCompany = company.getIdCompany();
	}


	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

}