package edu.ApiCV.DWS.dtos;

/**
 * DTO para recibir los datos de una aptitud.
 */
public record AptitudRequestDto(
        String nombre,
        String nivel,
        String descripcion,
        Long cvId
) {
}