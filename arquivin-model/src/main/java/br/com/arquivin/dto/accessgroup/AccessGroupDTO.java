package br.com.arquivin.dto.accessgroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.arquivin.dto.CompanyDTO;
import br.com.arquivin.dto.accessgroup.list.AccessGroupProjectDTO;

public class AccessGroupDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5519960257989295404L;

	private Long idAccessGroup;

	private String name;

	private String description;

	private Boolean disabled = Boolean.FALSE;
	
	private Boolean writePermission;
	
	private Boolean readPermission;
	
	private CompanyDTO company;
	
	private List<AccessGroupProjectDTO> projects = new ArrayList<>(0);

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

	public List<AccessGroupProjectDTO> getProjects() {
		return projects;
	}

	public void setProjects(List<AccessGroupProjectDTO> projects) {
		this.projects = projects;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

}
