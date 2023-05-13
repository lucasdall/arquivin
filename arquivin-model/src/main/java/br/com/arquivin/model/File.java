package br.com.arquivin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class File implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 79683635934736815L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_file")
	@SequenceGenerator(sequenceName = "seq_file", allocationSize = 1, name = "seq_file")
	@Column(name = "id_file")
	private Long idFile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_project", referencedColumnName = "id_project", insertable = true, updatable = true)
	private Project project;

	private String name;

	private String storageName;

	private String bucket;
	
	private Date createAt;
	
	private Date updateAt;
	
	private String version;
	
	private Boolean removed = Boolean.FALSE;
	
	private Date removedAt;
	
	private Long size;
	
	private Long downloadCount = 0l;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_file", referencedColumnName = "id_file", insertable = true, updatable = true)
	private File parentFile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user", referencedColumnName = "id_user", insertable = true, updatable = true)
	private User uploadedBy;
	
	public Long getIdFile() {
		return idFile;
	}

	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public File getParentFile() {
		return parentFile;
	}

	public void setParentFile(File parentFile) {
		this.parentFile = parentFile;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFile == null) ? 0 : idFile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File other = (File) obj;
		if (idFile == null) {
			if (other.idFile != null)
				return false;
		} else if (!idFile.equals(other.idFile))
			return false;
		return true;
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

	public User getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(User uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}
	
	public void increaseDownloadCount() {
		this.downloadCount++;
	}

}
