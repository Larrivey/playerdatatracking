package com.playerdatatracking.responses;

public class ManualDataResponse {

	
	int CODE;
	String description;
	
	
	public ManualDataResponse(int cODE, String description) {
		super();
		CODE = cODE;
		this.description = description;
	}
	public int getCODE() {
		return CODE;
	}
	public void setCODE(int cODE) {
		CODE = cODE;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ManualDataResponse [CODE=" + CODE + ", description=" + description + "]";
	}
	
	
}