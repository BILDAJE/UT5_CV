package edu.ApiCV.DWS.dtos;

/**
 * DTO para recibir los datos de un alumno.
 */
public record AlumnoRequestDto(
        String nombre,
        String apellidos,
        String telefonoFijo,
        String telefonoMovil,
        String email,
        String emailSecundario,
        String localidad
) {
}