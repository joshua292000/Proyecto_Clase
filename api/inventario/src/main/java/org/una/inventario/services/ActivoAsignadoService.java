package org.una.inventario.services;

import org.una.inventario.dto.ActivoAsignadoDTO;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ActivoAsignadoService {
    public Optional<ActivoAsignadoDTO> findById(Long id);
    public Optional<List<ActivoAsignadoDTO>> findAll();
    public Optional<List<ActivoAsignadoDTO>>findByEstadoActivo(String estado);
    public Optional<List<ActivoAsignadoDTO>>findByFechaCreacion(Date fechaCreacion);
    public Optional<ActivoAsignadoDTO> create(ActivoAsignadoDTO activoAsignadoDTO);
    public Optional<ActivoAsignadoDTO> update(ActivoAsignadoDTO activoAsignadoDTO, Long id);
}
