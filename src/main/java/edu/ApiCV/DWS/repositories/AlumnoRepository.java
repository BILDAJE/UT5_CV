package edu.ApiCV.DWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ApiCV.DWS.entities.Alumno;

/**
 * Repositorio para gestionar los alumnos.
 */
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}