package com.playerdatatracking.requests;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRequest {
	private Long id;
    private String nombre;
    private float nota;
    private String club;
    private String posicion;
    private List<String> qualities;
    private String mostLikeDestination;
    private String likeable;
    private String age;
    private String date;
    private String birth;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public List<String> getQualities() {
        return qualities;
    }

    public void setQualities(List<String> qualities) {
        this.qualities = qualities;
    }

    public String getMostLikeDestination() {
        return mostLikeDestination;
    }

    public void setMostLikeDestination(String mostLikeDestination) {
        this.mostLikeDestination = mostLikeDestination;
    }

    public String getLikeable() {
        return likeable;
    }

    public void setLikeable(String likeable) {
        this.likeable = likeable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
