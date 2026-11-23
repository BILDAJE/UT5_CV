package edu.ApiCV.DWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ApiCV.DWS.entities.Profesor;

/**
 * Repositorio para gestionar los profesores.
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

}