package br.com.arquivin.api.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.NestableRuntimeException;

import br.com.arquivin.dto.ValidationDTO;
import br.com.arquivin.dto.ValidationMsgTypeDTO;

public class ValidationException extends NestableRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6846507557895547206L;

	private List<ValidationDTO> validations = new ArrayList<>(0);
	
	public ValidationException() {}
	
	public ValidationException(ValidationMsgTypeDTO msgType, String msg) {
		this.validations.add(new ValidationDTO(msgType, msg));
	}

	public List<ValidationDTO> getValidations() {
		return validations;
	}

	public void setValidations(List<ValidationDTO> validations) {
		this.validations = validations;
	}

	public void addErrorMsg(String msg) {
		this.validations.add(new ValidationDTO(ValidationMsgTypeDTO.ERROR, msg));
	}

	public void addWarnMsg(String msg) {
		this.validations.add(new ValidationDTO(ValidationMsgTypeDTO.WARNING, msg));
	}

	public void addInfoMsg(String msg) {
		this.validations.add(new ValidationDTO(ValidationMsgTypeDTO.INFO, msg));
	}
	
}
