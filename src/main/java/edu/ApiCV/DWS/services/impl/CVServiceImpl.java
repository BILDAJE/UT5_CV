package edu.ApiCV.DWS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ApiCV.DWS.dtos.CVRequestDto;
import edu.ApiCV.DWS.dtos.CVResponseDto;
import edu.ApiCV.DWS.entities.Alumno;
import edu.ApiCV.DWS.entities.CV;
import edu.ApiCV.DWS.entities.Profesor;
import edu.ApiCV.DWS.exceptions.RecursoNoEncontradoException;
import edu.ApiCV.DWS.repositories.AlumnoRepository;
import edu.ApiCV.DWS.repositories.CVRepository;
import edu.ApiCV.DWS.repositories.ProfesorRepository;
import edu.ApiCV.DWS.services.CVService;

/**
 * Implementación del servicio para gestionar los CV.
 */
@Service
public class CVServiceImpl implements CVService {

    private final CVRepository cvRepository;
    private final AlumnoRepository alumnoRepository;
    private final ProfesorRepository profesorRepository;

    public CVServiceImpl(CVRepository cvRepository,
                         AlumnoRepository alumnoRepository,
                         ProfesorRepository profesorRepository) {

        this.cvRepository = cvRepository;
        this.alumnoRepository = alumnoRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public List<CVResponseDto> listarCVs() {

        List<CV> cvs = cvRepository.findAll();
        List<CVResponseDto> cvsDto = new ArrayList<>();

        for (CV cv : cvs) {
            cvsDto.add(convertirAResponseDto(cv));
        }

        return cvsDto;
    }

    @Override
    public Optional<CVResponseDto> buscarPorId(Long id) {

        Optional<CV> cv = cvRepository.findById(id);

        if (cv.isPresent()) {
            return Optional.of(convertirAResponseDto(cv.get()));
        }

        return Optional.empty();
    }

    @Override
    public CVResponseDto guardarCV(CVRequestDto cvDto) {

        CV cv = new CV();

        if (cvDto.alumnoId() != null) {

            Alumno alumno = alumnoRepository.findById(cvDto.alumnoId())
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "No se ha encontrado el alumno indicado."));

            cv.setAlumno(alumno);
        }

        if (cvDto.profesorId() != null) {

            Profesor profesor = profesorRepository.findById(cvDto.profesorId())
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "No se ha encontrado el profesor indicado."));

            cv.setProfesor(profesor);
        }

        CV cvGuardado = cvRepository.save(cv);

        return convertirAResponseDto(cvGuardado);
    }

    @Override
    public void eliminarCV(Long id) {

        if (!cvRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No se ha encontrado el CV indicado.");
        }

        cvRepository.deleteById(id);
    }

    /**
     * Convierte una entidad CV en un DTO de respuesta.
     *
     * @param cv CV que se va a convertir
     * @return DTO con los datos del CV
     */
    private CVResponseDto convertirAResponseDto(CV cv) {

        Long alumnoId = null;
        Long profesorId = null;

        if (cv.getAlumno() != null) {
            alumnoId = cv.getAlumno().getId();
        }

        if (cv.getProfesor() != null) {
            profesorId = cv.getProfesor().getId();
        }

        return new CVResponseDto(
                cv.getId(),
                alumnoId,
                profesorId
        );
    }
}