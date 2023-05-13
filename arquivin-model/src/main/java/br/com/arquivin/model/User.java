package br.com.arquivin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.arquivin.converter.PasswordConverter;
import br.com.arquivin.model.enuns.RegistrationStatus;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4122007492078954867L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
	@SequenceGenerator(sequenceName = "seq_user", allocationSize = 1, name = "seq_user")
	@Column(name = "id_user")
	private Long idUser;

	@Column(length = 100, nullable = true)
	private String name;

	@Column(length = 255, nullable = true)
	@Convert(converter = PasswordConverter.class)
	private String pwd;

	@Column(length = 100, unique = true)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Date lastLogin;

	@Column(nullable = false)
	private Boolean disabled = Boolean.FALSE;

	@Column(name = "profile_image", columnDefinition = "TEXT")
	private String profileImage;

	private String profileImageContentType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_accessGroup", referencedColumnName = "id_access_group", insertable = true, updatable = true)
	private AccessGroup accessGroup;
	
	@Enumerated(EnumType.STRING)
	private RegistrationStatus registrationStatus;
	
	@Transient
	private Perfil perfil;
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public AccessGroup getAccessGroup() {
		return accessGroup;
	}

	public void setAccessGroup(AccessGroup accessGroup) {
		this.accessGroup = accessGroup;
	}

}
