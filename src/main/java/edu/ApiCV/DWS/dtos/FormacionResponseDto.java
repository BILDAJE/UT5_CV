package edu.ApiCV.DWS.dtos;

import java.time.LocalDate;

/**
 * DTO para devolver los datos de una formación.
 */
public record FormacionResponseDto(
        Long id,
        String titulacion,
        String centro,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String descripcion,
        Long cvId
) {
}