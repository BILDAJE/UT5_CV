package edu.ApiCV.DWS.dtos;

import java.time.LocalDate;

/**
 * DTO para recibir los datos de una formación.
 */
public record FormacionRequestDto(
        String titulacion,
        String centro,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String descripcion,
        Long cvId
) {
}