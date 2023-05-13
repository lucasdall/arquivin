package br.com.arquivin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idProject")
public class ProjectDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 79683635934736815L;

	private Long idProject;

	private ProjectDTO parentProject;

	private List<ProjectDTO> childProjects = new ArrayList<>(0);

	private String name;

	private List<PerfilDTO> allowedPermissions = new ArrayList<>(0);

	private List<UserDTO> allowedUsers = new ArrayList<>(0);

	private String description;

	private String fullDescription;

	private String imgUrl;
	
	private CompanyDTO company;

	private List<FileDTO> files = new ArrayList<>(0);

	private Date createAt;
	
	private Date updateAt;
	
	private Boolean cardActive = Boolean.FALSE;
	
	private Boolean disabled = Boolean.FALSE;
	
	public Long getIdProject() {
		return idProject;
	}

	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	public ProjectDTO getParentProject() {
		return parentProject;
	}

	public void setParentProject(ProjectDTO parentProject) {
		this.parentProject = parentProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PerfilDTO> getAllowedPermissions() {
		return allowedPermissions;
	}

	public void setAllowedPermissions(List<PerfilDTO> allowedPermissions) {
		this.allowedPermissions = allowedPermissions;
	}

	public List<UserDTO> getAllowedUsers() {
		return allowedUsers;
	}

	public void setAllowedUsers(List<UserDTO> allowedUsers) {
		this.allowedUsers = allowedUsers;
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

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public List<FileDTO> getFiles() {
		return files;
	}

	public void setFiles(List<FileDTO> files) {
		this.files = files;
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

	public List<ProjectDTO> getChildProjects() {
		return childProjects;
	}

	public void setChildProjects(List<ProjectDTO> childProjects) {
		this.childProjects = childProjects;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProject == null) ? 0 : idProject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProjectDTO)) {
			return false;
		}
		ProjectDTO other = (ProjectDTO) obj;
		if (idProject == null) {
			if (other.idProject != null) {
				return false;
			}
		} else if (!idProject.equals(other.idProject)) {
			return false;
		}
		return true;
	}	
	
}
