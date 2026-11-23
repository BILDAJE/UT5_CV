package edu.ApiCV.DWS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ApiCV.DWS.dtos.ExperienciaRequestDto;
import edu.ApiCV.DWS.dtos.ExperienciaResponseDto;
import edu.ApiCV.DWS.entities.CV;
import edu.ApiCV.DWS.entities.Experiencia;
import edu.ApiCV.DWS.exceptions.RecursoNoEncontradoException;
import edu.ApiCV.DWS.repositories.CVRepository;
import edu.ApiCV.DWS.repositories.ExperienciaRepository;
import edu.ApiCV.DWS.services.ExperienciaService;

/**
 * Implementación del servicio para gestionar las experiencias laborales.
 */
@Service
public class ExperienciaServiceImpl implements ExperienciaService {

    private final ExperienciaRepository experienciaRepository;
    private final CVRepository cvRepository;

    public ExperienciaServiceImpl(ExperienciaRepository experienciaRepository,
                                  CVRepository cvRepository) {

        this.experienciaRepository = experienciaRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    public List<ExperienciaResponseDto> listarExperiencias() {

        List<Experiencia> experiencias = experienciaRepository.findAll();
        List<ExperienciaResponseDto> experienciasDto = new ArrayList<>();

        for (Experiencia experiencia : experiencias) {
            experienciasDto.add(convertirAResponseDto(experiencia));
        }

        return experienciasDto;
    }

    @Override
    public Optional<ExperienciaResponseDto> buscarPorId(Long id) {

        Optional<Experiencia> experiencia =
                experienciaRepository.findById(id);

        if (experiencia.isPresent()) {
            return Optional.of(convertirAResponseDto(experiencia.get()));
        }

        return Optional.empty();
    }

    @Override
    public ExperienciaResponseDto guardarExperiencia(
            ExperienciaRequestDto experienciaDto) {

        Experiencia experiencia = new Experiencia();

        experiencia.setPuesto(experienciaDto.puesto());
        experiencia.setEmpresa(experienciaDto.empresa());
        experiencia.setFechaInicio(experienciaDto.fechaInicio());
        experiencia.setFechaFin(experienciaDto.fechaFin());
        experiencia.setDescripcion(experienciaDto.descripcion());

        CV cv = cvRepository.findById(experienciaDto.cvId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se ha encontrado el CV indicado."));

        experiencia.setCv(cv);

        Experiencia experienciaGuardada =
                experienciaRepository.save(experiencia);

        return convertirAResponseDto(experienciaGuardada);
    }

    @Override
    public void eliminarExperiencia(Long id) {

        if (!experienciaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No se ha encontrado la experiencia indicada.");
        }

        experienciaRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Experiencia en un DTO de respuesta.
     *
     * @param experiencia experiencia que se va a convertir
     * @return DTO con los datos de la experiencia
     */
    private ExperienciaResponseDto convertirAResponseDto(
            Experiencia experiencia) {

        Long cvId = null;

        if (experiencia.getCv() != null) {
            cvId = experiencia.getCv().getId();
        }

        return new ExperienciaResponseDto(
                experiencia.getId(),
                experiencia.getPuesto(),
                experiencia.getEmpresa(),
                experiencia.getFechaInicio(),
                experiencia.getFechaFin(),
                experiencia.getDescripcion(),
                cvId
        );
    }
}