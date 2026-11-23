package edu.ApiCV.DWS.services;

import java.util.List;
import java.util.Optional;

import edu.ApiCV.DWS.dtos.AptitudRequestDto;
import edu.ApiCV.DWS.dtos.AptitudResponseDto;

public interface AptitudService {

    List<AptitudResponseDto> listarAptitudes();

    Optional<AptitudResponseDto> buscarPorId(Long id);

    AptitudResponseDto guardarAptitud(AptitudRequestDto aptitud);

    void eliminarAptitud(Long id);
}