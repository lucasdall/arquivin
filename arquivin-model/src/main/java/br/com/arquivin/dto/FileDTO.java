package br.com.arquivin.dto;

import java.io.Serializable;
import java.util.Date;

public class FileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 79683635934736815L;

	private Long idFile;

	private String name;

	private String storageName;

	private String bucket;
	
	private Date createAt;
	
	private Date updateAt;
	
	private String version;
	
	private FileDTO parentFile;
	
	private Boolean removed = Boolean.FALSE;
	
	private Date removedAt;
	
	private UserDTO uploadedBy;
	
	private Long size;

	public Long getIdFile() {
		return idFile;
	}

	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public FileDTO getParentFile() {
		return parentFile;
	}

	public void setParentFile(FileDTO parentFile) {
		this.parentFile = parentFile;
	}

	public Boolean getRemoved() {
		return removed;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}

	public Date getRemovedAt() {
		return removedAt;
	}

	public void setRemovedAt(Date removedAt) {
		this.removedAt = removedAt;
	}

	public UserDTO getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(UserDTO uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
