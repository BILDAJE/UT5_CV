package edu.ApiCV.DWS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ApiCV.DWS.dtos.AptitudRequestDto;
import edu.ApiCV.DWS.dtos.AptitudResponseDto;
import edu.ApiCV.DWS.services.AptitudService;

@RestController
@RequestMapping("/api/aptitudes")
public class AptitudController {

    private final AptitudService aptitudService;

    public AptitudController(AptitudService aptitudService) {
        this.aptitudService = aptitudService;
    }

    @GetMapping
    public ResponseEntity<List<AptitudResponseDto>> listarAptitudes() {
        return ResponseEntity.ok(aptitudService.listarAptitudes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AptitudResponseDto> buscarPorId(
            @PathVariable Long id) {

        Optional<AptitudResponseDto> aptitud =
                aptitudService.buscarPorId(id);

        if (aptitud.isPresent()) {
            return ResponseEntity.ok(aptitud.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AptitudResponseDto> guardarAptitud(
            @RequestBody AptitudRequestDto aptitud) {

        AptitudResponseDto aptitudGuardada =
                aptitudService.guardarAptitud(aptitud);

        return ResponseEntity.ok(aptitudGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAptitud(@PathVariable Long id) {

        aptitudService.eliminarAptitud(id);

        return ResponseEntity.noContent().build();
    }
}