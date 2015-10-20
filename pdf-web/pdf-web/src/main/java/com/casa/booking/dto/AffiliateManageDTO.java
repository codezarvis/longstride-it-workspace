package com.casa.booking.dto;

import java.util.List;

public class AffiliateManageDTO {

	private List<AffiliateDTO> data;
	private List<FieldErrorDTO> fieldErrors;
	private String error;
	//private FilesJson files;
	private Upload upload;
	
	public List<AffiliateDTO> getData() {
		return data;
	}
	public void setData(List<AffiliateDTO> data) {
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
	/*
	public FilesJson getFiles() {
		return files;
	}
	public void setFiles(FilesJson files) {
		this.files = files;
	}
	*/
	public Upload getUpload() {
		return upload;
	}
	public void setUpload(Upload upload) {
		this.upload = upload;
	}
	
}
