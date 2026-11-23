package edu.ApiCV.DWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ApiCV.DWS.entities.Experiencia;

/**
 * Repositorio para gestionar las experiencias laborales.
 */
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {

}