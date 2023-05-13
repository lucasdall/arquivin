package br.com.arquivin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "access_group")
public class AccessGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5519960257989295404L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_access_group")
	@SequenceGenerator(sequenceName = "seq_access_group", allocationSize = 1, name = "seq_access_group")
	@Column(name = "id_access_group")
	private Long idAccessGroup;

	@Column(length = 60, nullable = false)
	private String name;

	@Column(length = 255, nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Boolean disabled = Boolean.FALSE;
	
	private Boolean writePermission;
	
	private Boolean readPermission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_company", referencedColumnName = "id_company", insertable = true, updatable = true)
	private Company company;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "accessgroup_project", joinColumns = { @JoinColumn(name = "fk_accessgroup") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_project") }, uniqueConstraints = @UniqueConstraint(columnNames = { "fk_accessgroup",
					"fk_project" }))
	private List<Project> projects = new ArrayList<>(0);

	public Long getIdAccessGroup() {
		return idAccessGroup;
	}

	public void setIdAccessGroup(Long idAccessGroup) {
		this.idAccessGroup = idAccessGroup;
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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Boolean getWritePermission() {
		return writePermission;
	}

	public void setWritePermission(Boolean writePermission) {
		this.writePermission = writePermission;
	}

	public Boolean getReadPermission() {
		return readPermission;
	}

	public void setReadPermission(Boolean readPermission) {
		this.readPermission = readPermission;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
