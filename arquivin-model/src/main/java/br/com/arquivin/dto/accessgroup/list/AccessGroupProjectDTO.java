package br.com.arquivin.dto.accessgroup.list;

import java.io.Serializable;
import java.util.Date;

public class AccessGroupProjectDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 79683635934736815L;

	private Long idProject;

	private String name;

	private String description;

	private String fullDescription;

	private String imgUrl;

	private Date createAt;

	private Date updateAt;

	private Boolean cardActive = Boolean.FALSE;

	private Boolean disabled = Boolean.FALSE;

	private Integer countUsers;

	private Integer countfiles;

	public Long getIdProject() {
		return idProject;
	}

	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Boolean getCardActive() {
		return cardActive;
	}

	public void setCardActive(Boolean cardActive) {
		this.cardActive = cardActive;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getCountUsers() {
		return countUsers;
	}

	public void setCountUsers(Integer countUsers) {
		this.countUsers = countUsers;
	}

	public Integer getCountfiles() {
		return countfiles;
	}

	public void setCountfiles(Integer countfiles) {
		this.countfiles = countfiles;
	}

}
