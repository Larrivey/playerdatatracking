package com.playerdatatracking.responses;

public class GenericResponse {

	
	int code;
	String description;
	
	
	public GenericResponse() {
		super();
	}
	public GenericResponse(int cODE, String description) {
		super();
		code = cODE;
		this.description = description;
	}
	public int getCODE() {
		return code;
	}
	public void setCODE(int cODE) {
		code = cODE;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ManualDataResponse [CODE=" + code + ", description=" + description + "]";
	}
	
	
}