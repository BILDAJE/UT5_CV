package edu.ApiCV.DWS.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa un curriculum vitae.
 */
@Entity
@Table(name = "cvs")
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_alumno", unique = true)
    private Alumno alumno;

    @OneToOne
    @JoinColumn(name = "id_profesor", unique = true)
    private Profesor profesor;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Formacion> formaciones = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Experiencia> experiencias = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Aptitud> aptitudes = new ArrayList<>();

    public CV() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Formacion> getFormaciones() {
        return formaciones;
    }

    public void setFormaciones(List<Formacion> formaciones) {
        this.formaciones = formaciones;
    }

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public List<Aptitud> getAptitudes() {
        return aptitudes;
    }

    public void setAptitudes(List<Aptitud> aptitudes) {
        this.aptitudes = aptitudes;
    }
}