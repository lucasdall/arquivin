package br.com.arquivin.dto;

import java.io.Serializable;

public class ValidationDTO implements Serializable {

	public ValidationDTO(ValidationMsgTypeDTO type, Long code, String msg) {
		super();
		this.type = type;
		this.code = code;
		this.msg = msg;
	}

	public ValidationDTO(ValidationMsgTypeDTO type, String msg) {
		super();
		this.type = type;
		this.msg = msg;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 952963241260147667L;

	private ValidationMsgTypeDTO type;
	
	private Long code;
	
	private String msg;

	public ValidationMsgTypeDTO getType() {
		return type;
	}

	public void setType(ValidationMsgTypeDTO type) {
		this.type = type;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
