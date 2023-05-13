package br.com.arquivin.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.arquivin.dto.accessgroup.AccessGroupDTO;
import br.com.arquivin.model.enuns.RegistrationStatus;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 952963241260147667L;

	private String idUser;

	private String name;

	private String email;

	private String pwd;

	private Date lastLogin;

	private Boolean disabled = Boolean.FALSE;

	private String profileImage;

	private String profileImageContentType;

	private PerfilDTO perfil;

	private AccessGroupDTO accessGroup;

	@Enumerated(EnumType.STRING)
	private RegistrationStatus registrationStatus;

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageContentType() {
		return profileImageContentType;
	}

	public void setProfileImageContentType(String profileImageContentType) {
		this.profileImageContentType = profileImageContentType;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public AccessGroupDTO getAccessGroup() {
		return accessGroup;
	}

	public void setAccessGroup(AccessGroupDTO accessGroup) {
		this.accessGroup = accessGroup;
	}
}
