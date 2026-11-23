package edu.ApiCV.DWS.dtos;

/**
 * DTO para devolver los datos de un profesor.
 */
public record ProfesorResponseDto(
        Long id,
        String nombre,
        String apellidos,
        String telefonoFijo,
        String telefonoMovil,
        String email,
        String emailSecundario,
        String localidad
) {
}