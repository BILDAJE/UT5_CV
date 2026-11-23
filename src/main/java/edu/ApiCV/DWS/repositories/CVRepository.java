package edu.ApiCV.DWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ApiCV.DWS.entities.CV;

/**
 * Repositorio para gestionar los CV.
 */
public interface CVRepository extends JpaRepository<CV, Long> {

}