package br.com.arquivin.dto;

import java.io.Serializable;

public class SignedUrlDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7216556659223959587L;

	private String url;

	private String storageFileName;

	private String fileName;

	public SignedUrlDTO(String url, String storageFileName, String fileName) {
		this.url = url;
		this.storageFileName = storageFileName;
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStorageFileName() {
		return storageFileName;
	}

	public void setStorageFileName(String storageFileName) {
		this.storageFileName = storageFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
