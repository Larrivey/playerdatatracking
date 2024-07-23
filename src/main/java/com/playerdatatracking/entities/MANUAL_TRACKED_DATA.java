package com.playerdatatracking.entities;




import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MANUAL_TRACKED_DATA", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class MANUAL_TRACKED_DATA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private float nota;

    @Column(nullable = false)
    private String club;

    private String posicion;

    @ElementCollection
    @CollectionTable(name = "PLAYER_QUALITIES", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "quality")
    private List<String> qualities;

    private String mostLikeDestination;
    private String likeable;

    @Temporal(TemporalType.DATE)
    private Date date;

    // Getters y setters
    public Long getId() {
        return id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
