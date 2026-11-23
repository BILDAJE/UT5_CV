package edu.ApiCV.DWS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ApiCV.DWS.dtos.ProfesorRequestDto;
import edu.ApiCV.DWS.dtos.ProfesorResponseDto;
import edu.ApiCV.DWS.entities.Profesor;
import edu.ApiCV.DWS.exceptions.RecursoNoEncontradoException;
import edu.ApiCV.DWS.repositories.ProfesorRepository;
import edu.ApiCV.DWS.services.ProfesorService;

/**
 * Implementación del servicio para gestionar los profesores.
 */
@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    public List<ProfesorResponseDto> listarProfesores() {

        List<Profesor> profesores = profesorRepository.findAll();
        List<ProfesorResponseDto> profesoresDto = new ArrayList<>();

        for (Profesor profesor : profesores) {
            profesoresDto.add(convertirAResponseDto(profesor));
        }

        return profesoresDto;
    }

    @Override
    public Optional<ProfesorResponseDto> buscarPorId(Long id) {

        Optional<Profesor> profesor = profesorRepository.findById(id);

        if (profesor.isPresent()) {
            return Optional.of(convertirAResponseDto(profesor.get()));
        }

        return Optional.empty();
    }

    @Override
    public ProfesorResponseDto guardarProfesor(ProfesorRequestDto profesorDto) {

        Profesor profesor = new Profesor();

        profesor.setNombre(profesorDto.nombre());
        profesor.setApellidos(profesorDto.apellidos());
        profesor.setTelefonoFijo(profesorDto.telefonoFijo());
        profesor.setTelefonoMovil(profesorDto.telefonoMovil());
        profesor.setEmail(profesorDto.email());
        profesor.setEmailSecundario(profesorDto.emailSecundario());
        profesor.setLocalidad(profesorDto.localidad());

        Profesor profesorGuardado = profesorRepository.save(profesor);

        return convertirAResponseDto(profesorGuardado);
    }

    @Override
    public void eliminarProfesor(Long id) {

        if (!profesorRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No se ha encontrado el profesor indicado.");
        }

        profesorRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Profesor en un DTO de respuesta.
     *
     * @param profesor profesor que se va a convertir
     * @return DTO con los datos del profesor
     */
    private ProfesorResponseDto convertirAResponseDto(Profesor profesor) {

        return new ProfesorResponseDto(
                profesor.getId(),
                profesor.getNombre(),
                profesor.getApellidos(),
                profesor.getTelefonoFijo(),
                profesor.getTelefonoMovil(),
                profesor.getEmail(),
                profesor.getEmailSecundario(),
                profesor.getLocalidad()
        );
    }
}