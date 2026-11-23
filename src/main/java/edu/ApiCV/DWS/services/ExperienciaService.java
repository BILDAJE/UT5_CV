package edu.ApiCV.DWS.services;

import java.util.List;
import java.util.Optional;

import edu.ApiCV.DWS.dtos.ExperienciaRequestDto;
import edu.ApiCV.DWS.dtos.ExperienciaResponseDto;

public interface ExperienciaService {

    List<ExperienciaResponseDto> listarExperiencias();

    Optional<ExperienciaResponseDto> buscarPorId(Long id);

    ExperienciaResponseDto guardarExperiencia(ExperienciaRequestDto experiencia);

    void eliminarExperiencia(Long id);
}