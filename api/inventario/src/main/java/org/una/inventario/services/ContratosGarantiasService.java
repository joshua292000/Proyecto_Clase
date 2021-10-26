package org.una.inventario.services;

import org.una.inventario.dto.AlertasDTO;
import org.una.inventario.dto.ContratosGarantiasDTO;

import java.util.List;
import java.util.Optional;

public interface ContratosGarantiasService {
    public Optional<List<ContratosGarantiasDTO>> findAll();

    public Optional<ContratosGarantiasDTO> findById(Long id);

    public Optional<ContratosGarantiasDTO> create(ContratosGarantiasDTO contratosGarantiasDTO);

    public Optional<ContratosGarantiasDTO> update(ContratosGarantiasDTO contratosGarantiasDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
