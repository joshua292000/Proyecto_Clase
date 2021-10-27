package org.una.inventario.services;


import org.una.inventario.dto.ValuacionesDTO;
import org.una.inventario.entities.Valuaciones;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ValuacionesService {
    public Optional<ValuacionesDTO> findById(Long id);
    public Optional<List<ValuacionesDTO>> findAll();
    public Optional<List<ValuacionesDTO>> findByPrecioValuacion(Double precioValuacion);
    public Optional<List<ValuacionesDTO>>findByFechaCreacion(Date fechaCreacion);
    public Optional<ValuacionesDTO> create(ValuacionesDTO valuacionesDTO);
    public Optional<ValuacionesDTO> update(ValuacionesDTO valuacionesDTO, Long id);
}
