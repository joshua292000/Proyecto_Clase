package org.una.inventario.services;

import org.una.inventario.dto.MarcaDTO;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MarcaService {
    public Optional<MarcaDTO> findById(Long id);
    public Optional<List<MarcaDTO>> findAll();
    public Optional<List<MarcaDTO>> findByNombre(String nombre);
    public Optional<List<MarcaDTO>>findByEstado(String estado);
    public Optional<List<MarcaDTO>>findByFechaCreacion(Date fechaCreacion);
    public Optional<MarcaDTO> create(MarcaDTO marcaDTO);
    public Optional<MarcaDTO> update(MarcaDTO marcaDTO, Long id);
}
