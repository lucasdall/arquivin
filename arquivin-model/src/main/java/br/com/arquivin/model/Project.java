package br.com.arquivin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.com.arquivin.model.listener.ProjectCountListener;

@Entity
@Table(name = "project")
@EntityListeners(ProjectCountListener.class)
public final class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 79683635934736815L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project")
	@SequenceGenerator(sequenceName = "seq_project", allocationSize = 1, name = "seq_project")
	@Column(name = "id_project")
	private Long idProject;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_project", referencedColumnName = "id_project", insertable = true, updatable = true)
	private Project parentProject;

	@ManyToMany(mappedBy = "parentProject")
	@OrderBy("idProject DESC")
	private List<Project> childProjects = new ArrayList<>(0);

	private String name;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "project_permission", joinColumns = { @JoinColumn(name = "fk_project") }, inverseJoinColumns = { @JoinColumn(name = "fk_permission") }, uniqueConstraints = @UniqueConstraint(columnNames = { "fk_project", "fk_permission" }))
	private List<Perfil> allowedPermissions = new ArrayList<>(0);

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "project_user", joinColumns = { @JoinColumn(name = "fk_project") }, inverseJoinColumns = { @JoinColumn(name = "fk_user") }, uniqueConstraints = @UniqueConstraint(columnNames = { "fk_project", "fk_user" }))
	private List<User> allowedUsers = new ArrayList<>(0);

	private String description;

	private String imgUrl;
	
	@Lob
	private String fullDescription;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_company", referencedColumnName = "id_company", insertable = true, updatable = true)
	private Company company;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "project_file", joinColumns = { @JoinColumn(name = "fk_project") }, inverseJoinColumns = { @JoinColumn(name = "fk_file") }, uniqueConstraints = @UniqueConstraint(columnNames = { "fk_project", "fk_file" }))
	@OrderBy("updateAt DESC, createAt DESC")
	private List<File> files = new ArrayList<>(0);

	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	@Column(nullable = false)
	private Boolean disabled = Boolean.FALSE;	
	
	@Transient
	private Integer countUsers;

	@Transient
	private Integer countfiles;
	
	public Long getIdProject() {
		return idProject;
	}

	public Project getParentProject() {
		return parentProject;
	}

	public void setParentProject(Project parentProject) {
		this.parentProject = parentProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Perfil> getAllowedPermissions() {
		return allowedPermissions;
	}

	public void setAllowedPermissions(List<Perfil> allowedPermissions) {
		this.allowedPermissions = allowedPermissions;
	}

	public List<User> getAllowedUsers() {
		return allowedUsers;
	}

	public void setAllowedUsers(List<User> allowedUsers) {
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
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

	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	public List<Project> getChildProjects() {
		return childProjects;
	}

	public void setChildProjects(List<Project> childProjects) {
		this.childProjects = childProjects;
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
		if (!(obj instanceof Project)) {
			return false;
		}
		Project other = (Project) obj;
		if (idProject == null) {
			if (other.idProject != null) {
				return false;
			}
		} else if (!idProject.equals(other.idProject)) {
			return false;
		}
		return true;
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
