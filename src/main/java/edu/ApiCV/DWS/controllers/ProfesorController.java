package edu.ApiCV.DWS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ApiCV.DWS.dtos.ProfesorRequestDto;
import edu.ApiCV.DWS.dtos.ProfesorResponseDto;
import edu.ApiCV.DWS.services.ProfesorService;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorResponseDto>> listarProfesores() {
        return ResponseEntity.ok(profesorService.listarProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponseDto> buscarPorId(@PathVariable Long id) {

        Optional<ProfesorResponseDto> profesor =
                profesorService.buscarPorId(id);

        if (profesor.isPresent()) {
            return ResponseEntity.ok(profesor.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProfesorResponseDto> guardarProfesor(
            @RequestBody ProfesorRequestDto profesor) {

        ProfesorResponseDto profesorGuardado =
                profesorService.guardarProfesor(profesor);

        return ResponseEntity.ok(profesorGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Long id) {

        profesorService.eliminarProfesor(id);

        return ResponseEntity.noContent().build();
    }
}