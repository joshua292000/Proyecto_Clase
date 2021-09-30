package org.una.inventario.services;

import org.una.inventario.dto.TransaccionDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransaccionService {

    public Optional<TransaccionDTO> findById(Long id);

    public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaCreacionBetween(Long usuarioId, Date fechaCreacion, Date fechaFinalizacion);

    public Optional<List<TransaccionDTO>> findByIdAndFechaCreacionBetween(Long id, Date fechaCreacion, Date fechaFinalizacion);

    public Optional<List<TransaccionDTO>> findByObjetoAndFechaCreacionBetween(String objetoId, Date fechaCreacion, Date fechaFinalizacion);

    public Optional<List<TransaccionDTO>> findByFechaCreacionBetween(Date fechaCreacion, Date fechaFinalizacion);

    public TransaccionDTO create(TransaccionDTO transacciones);

}
