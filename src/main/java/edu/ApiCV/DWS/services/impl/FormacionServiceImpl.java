package edu.ApiCV.DWS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ApiCV.DWS.dtos.FormacionRequestDto;
import edu.ApiCV.DWS.dtos.FormacionResponseDto;
import edu.ApiCV.DWS.entities.CV;
import edu.ApiCV.DWS.entities.Formacion;
import edu.ApiCV.DWS.exceptions.RecursoNoEncontradoException;
import edu.ApiCV.DWS.repositories.CVRepository;
import edu.ApiCV.DWS.repositories.FormacionRepository;
import edu.ApiCV.DWS.services.FormacionService;

/**
 * Implementación del servicio para gestionar las formaciones.
 */
@Service
public class FormacionServiceImpl implements FormacionService {

    private final FormacionRepository formacionRepository;
    private final CVRepository cvRepository;

    public FormacionServiceImpl(FormacionRepository formacionRepository,
                                CVRepository cvRepository) {

        this.formacionRepository = formacionRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    public List<FormacionResponseDto> listarFormaciones() {

        List<Formacion> formaciones = formacionRepository.findAll();
        List<FormacionResponseDto> formacionesDto = new ArrayList<>();

        for (Formacion formacion : formaciones) {
            formacionesDto.add(convertirAResponseDto(formacion));
        }

        return formacionesDto;
    }

    @Override
    public Optional<FormacionResponseDto> buscarPorId(Long id) {

        Optional<Formacion> formacion = formacionRepository.findById(id);

        if (formacion.isPresent()) {
            return Optional.of(convertirAResponseDto(formacion.get()));
        }

        return Optional.empty();
    }

    @Override
    public FormacionResponseDto guardarFormacion(
            FormacionRequestDto formacionDto) {

        Formacion formacion = new Formacion();

        formacion.setTitulacion(formacionDto.titulacion());
        formacion.setCentro(formacionDto.centro());
        formacion.setFechaInicio(formacionDto.fechaInicio());
        formacion.setFechaFin(formacionDto.fechaFin());
        formacion.setDescripcion(formacionDto.descripcion());

        CV cv = cvRepository.findById(formacionDto.cvId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se ha encontrado el CV indicado."));

        formacion.setCv(cv);

        Formacion formacionGuardada = formacionRepository.save(formacion);

        return convertirAResponseDto(formacionGuardada);
    }

    @Override
    public void eliminarFormacion(Long id) {

        if (!formacionRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No se ha encontrado la formación indicada.");
        }

        formacionRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Formacion en un DTO de respuesta.
     *
     * @param formacion formación que se va a convertir
     * @return DTO con los datos de la formación
     */
    private FormacionResponseDto convertirAResponseDto(Formacion formacion) {

        Long cvId = null;

        if (formacion.getCv() != null) {
            cvId = formacion.getCv().getId();
        }

        return new FormacionResponseDto(
                formacion.getId(),
                formacion.getTitulacion(),
                formacion.getCentro(),
                formacion.getFechaInicio(),
                formacion.getFechaFin(),
                formacion.getDescripcion(),
                cvId
        );
    }
}