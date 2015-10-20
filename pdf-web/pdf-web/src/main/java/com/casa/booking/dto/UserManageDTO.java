package com.casa.booking.dto;

import java.util.List;

public class UserManageDTO {

	private List<UserDTO> data;
	private List<FieldErrorDTO> fieldErrors;
	private String error;
	
	public List<UserDTO> getData() {
		return data;
	}
	public void setData(List<UserDTO> data) {
		this.data = data;
	}
	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
