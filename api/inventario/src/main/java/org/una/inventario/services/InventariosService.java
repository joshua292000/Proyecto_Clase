package org.una.inventario.services;


import org.una.inventario.dto.InventariosDTO;
import org.una.inventario.entities.Inventarios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InventariosService {
    public Optional<InventariosDTO> findById(Long id);
    public Optional<List<InventariosDTO>> findAll();
    public Optional<List<InventariosDTO>> findByDescripcion(String descripcion);
    public Optional<List<InventariosDTO>> findByEstado(String estado);
    public Optional<InventariosDTO> create(InventariosDTO inventariosDTO);
    public Optional<InventariosDTO> update(InventariosDTO inventariosDTO, Long id);
}
