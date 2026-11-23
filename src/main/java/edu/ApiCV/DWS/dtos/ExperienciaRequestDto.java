package edu.ApiCV.DWS.dtos;

import java.time.LocalDate;

/**
 * DTO para recibir los datos de una experiencia laboral.
 */
public record ExperienciaRequestDto(
        String puesto,
        String empresa,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String descripcion,
        Long cvId
) {
}