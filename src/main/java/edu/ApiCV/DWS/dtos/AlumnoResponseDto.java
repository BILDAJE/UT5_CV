package edu.ApiCV.DWS.dtos;

/**
 * DTO para devolver los datos de un alumno.
 */
public record AlumnoResponseDto(
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