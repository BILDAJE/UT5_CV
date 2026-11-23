package edu.ApiCV.DWS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ApiCV.DWS.dtos.AlumnoRequestDto;
import edu.ApiCV.DWS.dtos.AlumnoResponseDto;
import edu.ApiCV.DWS.services.AlumnoService;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<AlumnoResponseDto>> listarAlumnos() {
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> buscarPorId(@PathVariable Long id) {

        Optional<AlumnoResponseDto> alumno = alumnoService.buscarPorId(id);

        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AlumnoResponseDto> guardarAlumno(
            @RequestBody AlumnoRequestDto alumno) {

        AlumnoResponseDto alumnoGuardado =
                alumnoService.guardarAlumno(alumno);

        return ResponseEntity.ok(alumnoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {

        alumnoService.eliminarAlumno(id);

        return ResponseEntity.noContent().build();
    }
}