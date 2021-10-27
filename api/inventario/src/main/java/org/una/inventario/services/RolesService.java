package org.una.inventario.services;

import org.una.inventario.dto.RolesDTO;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    public Optional<List<RolesDTO>> findAll();

    public Optional<RolesDTO> findById(Long id);

    public Optional<List<RolesDTO>> findByNombreAproximateIgnoreCase(String nombre);

    public Optional<RolesDTO> create(RolesDTO rolesDTO);

    public Optional<RolesDTO> update(RolesDTO rolesDTO, Long id);

    public void delete(Long id);

    public void deleteAll();


}
