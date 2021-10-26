package org.una.inventario.services;

import org.una.inventario.dto.AlertasDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Alertas;

import java.util.List;
import java.util.Optional;

public interface AlertasService {

    public Optional<List<AlertasDTO>> findAll();

    public Optional<AlertasDTO> findById(Long id);

    public Optional<AlertasDTO> findByTipo(String tipo);

    public Optional<AlertasDTO> create(AlertasDTO alertasDTO);

    public Optional<AlertasDTO> update(AlertasDTO alertasDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
