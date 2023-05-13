package br.com.arquivin.dto.accessgroup.list;

import java.io.Serializable;

public class AccessGroupListViewDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5519960257989295404L;

	private Long idAccessGroup;

	private String name;

	private String description;

	private Boolean disabled;

	private Boolean writePermission;

	private Boolean readPermission;

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

}
