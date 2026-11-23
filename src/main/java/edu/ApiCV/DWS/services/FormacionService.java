package edu.ApiCV.DWS.services;

import java.util.List;
import java.util.Optional;

import edu.ApiCV.DWS.dtos.FormacionRequestDto;
import edu.ApiCV.DWS.dtos.FormacionResponseDto;

public interface FormacionService {

    List<FormacionResponseDto> listarFormaciones();

    Optional<FormacionResponseDto> buscarPorId(Long id);

    FormacionResponseDto guardarFormacion(FormacionRequestDto formacion);

    void eliminarFormacion(Long id);
}