package com.playerdatatracking.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<E> {

	
	int code;
	String description;
	E entity;
	
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
	
	public E getEntity() {
		return entity;
	}
	public void setEntity(E entity) {
		this.entity = entity;
	}
	
	@Override
	public String toString() {
		return "ManualDataResponse [CODE=" + code + ", description=" + description + ", " + entity.getClass() + "= " + entity.toString() +"]";
	}
	
	
}