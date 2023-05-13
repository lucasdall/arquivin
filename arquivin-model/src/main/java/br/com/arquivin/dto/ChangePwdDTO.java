package br.com.arquivin.dto;

import java.io.Serializable;

public class ChangePwdDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5883064865322391952L;

	private String pwdNew;
	
	private String pwdOld;

	public String getPwdNew() {
		return pwdNew;
	}

	public void setPwdNew(String pwdNew) {
		this.pwdNew = pwdNew;
	}

	public String getPwdOld() {
		return pwdOld;
	}

	public void setPwdOld(String pwdOld) {
		this.pwdOld = pwdOld;
	}
	
	

}
