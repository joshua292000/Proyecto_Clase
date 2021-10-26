package org.una.inventario.services;

import org.una.inventario.dto.ProveedoresDTO;

import java.util.List;
import java.util.Optional;

public interface ProveedoresService {
    public Optional<List<ProveedoresDTO>> findAll();

    public Optional<ProveedoresDTO> findByNombre(String nombre);

    public Optional<ProveedoresDTO> findById(Long id);

    public Optional<ProveedoresDTO> create(ProveedoresDTO proveedoresDTO);

    public Optional<ProveedoresDTO> update(ProveedoresDTO proveedoresDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
