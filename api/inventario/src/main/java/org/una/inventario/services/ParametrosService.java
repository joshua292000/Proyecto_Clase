package org.una.inventario.services;


import org.una.inventario.dto.ParametrosDTO;
import org.una.inventario.entities.Parametros;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ParametrosService {
    public Optional<ParametrosDTO> findById(Long id);
    public Optional<List<ParametrosDTO>> findAll();
    public Optional<List<ParametrosDTO>> findByNombre(String nombre);
    public Optional<List<ParametrosDTO>>findByEstado(String estado);
    public Optional<List<ParametrosDTO>>findByFechaCreacionBetween(Date fechaCreacion, Date fechaModificacion);
    public Optional<ParametrosDTO> create(ParametrosDTO parametrosDTO);
    public Optional<ParametrosDTO> update(ParametrosDTO parametrosDTO, Long id);
}
