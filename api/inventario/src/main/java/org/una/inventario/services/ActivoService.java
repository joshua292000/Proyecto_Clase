package org.una.inventario.services;

import org.una.inventario.dto.ActivoDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ActivoService {
    public Optional<ActivoDTO> findById(Long id);
    public Optional<List<ActivoDTO>> findAll();
    public Optional<List<ActivoDTO>> findByNombre(String nombre);
    public Optional<List<ActivoDTO>>findByEstadoActivo(String estado);
    public Optional<List<ActivoDTO>>findByFechaCreacionBetween(Date fechaCreacion, Date fechaModificacion);
    public Optional<ActivoDTO> create(ActivoDTO activoDTO);
    public Optional<ActivoDTO> update(ActivoDTO activoDTO, Long id);
}
