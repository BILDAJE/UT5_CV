package edu.ApiCV.DWS.services;

import java.util.List;
import java.util.Optional;

import edu.ApiCV.DWS.dtos.CVRequestDto;
import edu.ApiCV.DWS.dtos.CVResponseDto;

public interface CVService {

    List<CVResponseDto> listarCVs();

    Optional<CVResponseDto> buscarPorId(Long id);

    CVResponseDto guardarCV(CVRequestDto cv);

    void eliminarCV(Long id);
}