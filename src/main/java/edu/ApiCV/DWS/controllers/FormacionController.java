package edu.ApiCV.DWS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ApiCV.DWS.dtos.FormacionRequestDto;
import edu.ApiCV.DWS.dtos.FormacionResponseDto;
import edu.ApiCV.DWS.services.FormacionService;

@RestController
@RequestMapping("/api/formaciones")
public class FormacionController {

    private final FormacionService formacionService;

    public FormacionController(FormacionService formacionService) {
        this.formacionService = formacionService;
    }

    @GetMapping
    public ResponseEntity<List<FormacionResponseDto>> listarFormaciones() {
        return ResponseEntity.ok(formacionService.listarFormaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormacionResponseDto> buscarPorId(@PathVariable Long id) {

        Optional<FormacionResponseDto> formacion = formacionService.buscarPorId(id);

        if (formacion.isPresent()) {
            return ResponseEntity.ok(formacion.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FormacionResponseDto> guardarFormacion(
            @RequestBody FormacionRequestDto formacion) {

        FormacionResponseDto formacionGuardada =
                formacionService.guardarFormacion(formacion);

        return ResponseEntity.ok(formacionGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFormacion(@PathVariable Long id) {

        formacionService.eliminarFormacion(id);

        return ResponseEntity.noContent().build();
    }
}