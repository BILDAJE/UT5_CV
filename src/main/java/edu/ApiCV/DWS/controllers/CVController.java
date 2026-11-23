package edu.ApiCV.DWS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ApiCV.DWS.dtos.CVRequestDto;
import edu.ApiCV.DWS.dtos.CVResponseDto;
import edu.ApiCV.DWS.services.CVService;

@RestController
@RequestMapping("/api/cvs")
public class CVController {

    private final CVService cvService;

    public CVController(CVService cvService) {
        this.cvService = cvService;
    }

    @GetMapping
    public ResponseEntity<List<CVResponseDto>> listarCVs() {
        return ResponseEntity.ok(cvService.listarCVs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CVResponseDto> buscarPorId(@PathVariable Long id) {

        Optional<CVResponseDto> cv = cvService.buscarPorId(id);

        if (cv.isPresent()) {
            return ResponseEntity.ok(cv.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CVResponseDto> guardarCV(@RequestBody CVRequestDto cv) {

        CVResponseDto cvGuardado = cvService.guardarCV(cv);

        return ResponseEntity.ok(cvGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCV(@PathVariable Long id) {

        cvService.eliminarCV(id);

        return ResponseEntity.noContent().build();
    }
}