package edu.ApiCV.DWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ApiCV.DWS.entities.Formacion;

/**
 * Repositorio para gestionar las formaciones.
 */
public interface FormacionRepository extends JpaRepository<Formacion, Long> {

}