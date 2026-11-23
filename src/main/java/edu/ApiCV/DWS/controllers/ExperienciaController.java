package edu.ApiCV.DWS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ApiCV.DWS.dtos.ExperienciaRequestDto;
import edu.ApiCV.DWS.dtos.ExperienciaResponseDto;
import edu.ApiCV.DWS.services.ExperienciaService;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaController {

    private final ExperienciaService experienciaService;

    public ExperienciaController(ExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienciaResponseDto>> listarExperiencias() {
        return ResponseEntity.ok(experienciaService.listarExperiencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaResponseDto> buscarPorId(
            @PathVariable Long id) {

        Optional<ExperienciaResponseDto> experiencia =
                experienciaService.buscarPorId(id);

        if (experiencia.isPresent()) {
            return ResponseEntity.ok(experiencia.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExperienciaResponseDto> guardarExperiencia(
            @RequestBody ExperienciaRequestDto experiencia) {

        ExperienciaResponseDto experienciaGuardada =
                experienciaService.guardarExperiencia(experiencia);

        return ResponseEntity.ok(experienciaGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarExperiencia(@PathVariable Long id) {

        experienciaService.eliminarExperiencia(id);

        return ResponseEntity.noContent().build();
    }
}