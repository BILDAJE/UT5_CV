package edu.ApiCV.DWS.services;

import java.util.List;
import java.util.Optional;

import edu.ApiCV.DWS.dtos.AlumnoRequestDto;
import edu.ApiCV.DWS.dtos.AlumnoResponseDto;

public interface AlumnoService {

    List<AlumnoResponseDto> listarAlumnos();

    Optional<AlumnoResponseDto> buscarPorId(Long id);

    AlumnoResponseDto guardarAlumno(AlumnoRequestDto alumno);

    void eliminarAlumno(Long id);
}