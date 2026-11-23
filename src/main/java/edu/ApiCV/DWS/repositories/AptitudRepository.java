package edu.ApiCV.DWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ApiCV.DWS.entities.Aptitud;

/**
 * Repositorio para gestionar las aptitudes.
 */
public interface AptitudRepository extends JpaRepository<Aptitud, Long> {

}