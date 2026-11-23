package edu.ApiCV.DWS.dtos;

/**
 * DTO para recibir los datos de un CV.
 */
public record CVRequestDto(
        Long alumnoId,
        Long profesorId
) {
}