package edu.ApiCV.DWS.services;

import java.util.List;
import java.util.Optional;

import edu.ApiCV.DWS.dtos.ProfesorRequestDto;
import edu.ApiCV.DWS.dtos.ProfesorResponseDto;

public interface ProfesorService {

    List<ProfesorResponseDto> listarProfesores();

    Optional<ProfesorResponseDto> buscarPorId(Long id);

    ProfesorResponseDto guardarProfesor(ProfesorRequestDto profesor);

    void eliminarProfesor(Long id);
}