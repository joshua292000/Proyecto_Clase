package org.una.inventario.services;


import org.una.inventario.dto.CategoriasDTO;
import org.una.inventario.entities.Categorias;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CategoriasService {
    public Optional<CategoriasDTO> findById(Long id);
    public Optional<List<CategoriasDTO>> findAll();
    public Optional<List<CategoriasDTO>> findByNombre(String nombre);
    public Optional<List<CategoriasDTO>> findByEstado(String estado);
    public Optional<List<CategoriasDTO>> findByFechaCreacion(Date fechaCreacion);
    public Optional<CategoriasDTO> create(CategoriasDTO categoriasDTO);
    public Optional<CategoriasDTO> update(CategoriasDTO categoriasDTO, Long id);
}
