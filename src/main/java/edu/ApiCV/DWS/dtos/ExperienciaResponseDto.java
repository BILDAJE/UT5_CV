package edu.ApiCV.DWS.dtos;

import java.time.LocalDate;

/**
 * DTO para devolver los datos de una experiencia laboral.
 */
public record ExperienciaResponseDto(
        Long id,
        String puesto,
        String empresa,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String descripcion,
        Long cvId
) {
}