package edu.ApiCV.DWS.dtos;

/**
 * DTO para devolver los datos de un CV.
 */
public record CVResponseDto(
        Long id,
        Long alumnoId,
        Long profesorId
) {
}