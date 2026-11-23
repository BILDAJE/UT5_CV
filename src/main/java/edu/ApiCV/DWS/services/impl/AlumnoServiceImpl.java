package edu.ApiCV.DWS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ApiCV.DWS.dtos.AlumnoRequestDto;
import edu.ApiCV.DWS.dtos.AlumnoResponseDto;
import edu.ApiCV.DWS.entities.Alumno;
import edu.ApiCV.DWS.exceptions.RecursoNoEncontradoException;
import edu.ApiCV.DWS.repositories.AlumnoRepository;
import edu.ApiCV.DWS.services.AlumnoService;

/**
 * Implementación del servicio para gestionar los alumnos.
 */
@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<AlumnoResponseDto> listarAlumnos() {

        List<Alumno> alumnos = alumnoRepository.findAll();
        List<AlumnoResponseDto> alumnosDto = new ArrayList<>();

        for (Alumno alumno : alumnos) {
            alumnosDto.add(convertirAResponseDto(alumno));
        }

        return alumnosDto;
    }

    @Override
    public Optional<AlumnoResponseDto> buscarPorId(Long id) {

        Optional<Alumno> alumno = alumnoRepository.findById(id);

        if (alumno.isPresent()) {
            return Optional.of(convertirAResponseDto(alumno.get()));
        }

        return Optional.empty();
    }

    @Override
    public AlumnoResponseDto guardarAlumno(AlumnoRequestDto alumnoDto) {

        Alumno alumno = new Alumno();

        alumno.setNombre(alumnoDto.nombre());
        alumno.setApellidos(alumnoDto.apellidos());
        alumno.setTelefonoFijo(alumnoDto.telefonoFijo());
        alumno.setTelefonoMovil(alumnoDto.telefonoMovil());
        alumno.setEmail(alumnoDto.email());
        alumno.setEmailSecundario(alumnoDto.emailSecundario());
        alumno.setLocalidad(alumnoDto.localidad());

        Alumno alumnoGuardado = alumnoRepository.save(alumno);

        return convertirAResponseDto(alumnoGuardado);
    }

    @Override
    public void eliminarAlumno(Long id) {

        if (!alumnoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No se ha encontrado el alumno indicado.");
        }

        alumnoRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Alumno en un DTO de respuesta.
     *
     * @param alumno alumno que se va a convertir
     * @return DTO con los datos del alumno
     */
    private AlumnoResponseDto convertirAResponseDto(Alumno alumno) {

        return new AlumnoResponseDto(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getTelefonoFijo(),
                alumno.getTelefonoMovil(),
                alumno.getEmail(),
                alumno.getEmailSecundario(),
                alumno.getLocalidad()
        );
    }
}