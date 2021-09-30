package org.una.inventario.services;

import org.una.inventario.dto.DepartamentoDTO;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {
    public Optional<List<DepartamentoDTO>> findAll();

    public Optional<List<DepartamentoDTO>>findByEstadoProximo(boolean estado);

    public Optional<DepartamentoDTO>create(DepartamentoDTO departamentoDTO);

    public Optional<DepartamentoDTO> update(DepartamentoDTO departamentoDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

}
