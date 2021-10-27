package org.una.inventario.services;

import org.springframework.data.repository.query.Param;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.entities.Activo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ActivoService {
    public Optional<ActivoDTO> findById(Long id);
    public Optional<List<ActivoDTO>> findAll();
    public Optional<List<ActivoDTO>> findByNombre(String nombre);
    public Optional<List<ActivoDTO>>findByEstado(String estado);
    public Optional<List<ActivoDTO>>findByFechaCreacionBetween(Date fechaCreacion, Date fechaModificacion);
    public Optional<List<ActivoDTO>> findByActivosxMarcaDescBetweenFechas(@Param("idMarcaProve")Long idMarcaProve,
                                                             @Param("startDate")Date startDate,
                                                             @Param("endDate")Date endDate);

    public Optional<List<ActivoDTO>> findByActivosxMarcaAscBetweenFechas(@Param("idMarcaProve")Long idMarcaProve,
                                                            @Param("startDate")Date startDate,
                                                            @Param("endDate")Date endDate);

    public Optional<List<ActivoDTO>> findByActivosxProveDescBetweenFechas(@Param("idMarcaProve")Long idMarcaProve,
                                                             @Param("startDate")Date startDate,
                                                             @Param("endDate")Date endDate);

    public Optional<List<ActivoDTO>> findByActivosxProveAscBetweenFechas(@Param("idMarcaProve")Long idMarcaProve,
                                                            @Param("startDate")Date startDate,
                                                            @Param("endDate")Date endDate);


    public Optional<ActivoDTO> create(ActivoDTO activoDTO);
    public Optional<ActivoDTO> update(ActivoDTO activoDTO, Long id);
}
