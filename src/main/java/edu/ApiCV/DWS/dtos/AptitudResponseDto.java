package edu.ApiCV.DWS.dtos;

/**
 * DTO para devolver los datos de una aptitud.
 */
public record AptitudResponseDto(
        Long id,
        String nombre,
        String nivel,
        String descripcion,
        Long cvId
) {
}