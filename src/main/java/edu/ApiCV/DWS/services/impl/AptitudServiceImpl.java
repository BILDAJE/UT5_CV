package edu.ApiCV.DWS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ApiCV.DWS.dtos.AptitudRequestDto;
import edu.ApiCV.DWS.dtos.AptitudResponseDto;
import edu.ApiCV.DWS.entities.Aptitud;
import edu.ApiCV.DWS.entities.CV;
import edu.ApiCV.DWS.exceptions.RecursoNoEncontradoException;
import edu.ApiCV.DWS.repositories.AptitudRepository;
import edu.ApiCV.DWS.repositories.CVRepository;
import edu.ApiCV.DWS.services.AptitudService;

/**
 * Implementación del servicio para gestionar las aptitudes.
 */
@Service
public class AptitudServiceImpl implements AptitudService {

    private final AptitudRepository aptitudRepository;
    private final CVRepository cvRepository;

    public AptitudServiceImpl(AptitudRepository aptitudRepository,
                              CVRepository cvRepository) {

        this.aptitudRepository = aptitudRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    public List<AptitudResponseDto> listarAptitudes() {

        List<Aptitud> aptitudes = aptitudRepository.findAll();
        List<AptitudResponseDto> aptitudesDto = new ArrayList<>();

        for (Aptitud aptitud : aptitudes) {
            aptitudesDto.add(convertirAResponseDto(aptitud));
        }

        return aptitudesDto;
    }

    @Override
    public Optional<AptitudResponseDto> buscarPorId(Long id) {

        Optional<Aptitud> aptitud = aptitudRepository.findById(id);

        if (aptitud.isPresent()) {
            return Optional.of(convertirAResponseDto(aptitud.get()));
        }

        return Optional.empty();
    }

    @Override
    public AptitudResponseDto guardarAptitud(AptitudRequestDto aptitudDto) {

        Aptitud aptitud = new Aptitud();

        aptitud.setNombre(aptitudDto.nombre());
        aptitud.setNivel(aptitudDto.nivel());
        aptitud.setDescripcion(aptitudDto.descripcion());

        CV cv = cvRepository.findById(aptitudDto.cvId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se ha encontrado el CV indicado."));

        aptitud.setCv(cv);

        Aptitud aptitudGuardada = aptitudRepository.save(aptitud);

        return convertirAResponseDto(aptitudGuardada);
    }

    @Override
    public void eliminarAptitud(Long id) {

        if (!aptitudRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No se ha encontrado la aptitud indicada.");
        }

        aptitudRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Aptitud en un DTO de respuesta.
     *
     * @param aptitud aptitud que se va a convertir
     * @return DTO con los datos de la aptitud
     */
    private AptitudResponseDto convertirAResponseDto(Aptitud aptitud) {

        Long cvId = null;

        if (aptitud.getCv() != null) {
            cvId = aptitud.getCv().getId();
        }

        return new AptitudResponseDto(
                aptitud.getId(),
                aptitud.getNombre(),
                aptitud.getNivel(),
                aptitud.getDescripcion(),
                cvId
        );
    }
}