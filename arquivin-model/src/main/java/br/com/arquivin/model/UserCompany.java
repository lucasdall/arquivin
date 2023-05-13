package br.com.arquivin.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_company")
public class UserCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7124702393094047L;

	@EmbeddedId
	private UserCompanyID userCompanyID;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_perfil", referencedColumnName = "id_perfil", insertable = true, updatable = true)
	private Perfil perfil;
	
	public UserCompany() {}
	
	public UserCompany(UserCompanyID userCompanyID) {
		this.userCompanyID = userCompanyID;
	}

	public UserCompanyID getUserCompanyID() {
		return userCompanyID;
	}

	public void setUserCompanyID(UserCompanyID userCompanyID) {
		this.userCompanyID = userCompanyID;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
