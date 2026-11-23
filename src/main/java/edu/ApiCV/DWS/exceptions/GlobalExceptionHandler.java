package edu.ApiCV.DWS.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Gestiona las excepciones de la API.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gestiona los recursos que no se encuentran.
     */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> gestionarRecursoNoEncontrado(
            RecursoNoEncontradoException excepcion) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(excepcion.getMessage());
    }

    /**
     * Gestiona los errores al intentar eliminar un recurso inexistente.
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> gestionarRegistroNoEncontrado(
            EmptyResultDataAccessException excepcion) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("El recurso que se intenta eliminar no existe.");
    }
}