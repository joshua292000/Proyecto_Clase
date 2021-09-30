package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.TransaccionDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Transaccion;
import org.una.inventario.entities.Usuario;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.TransaccionRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImplementation implements TransaccionService{

    @Autowired
    private TransaccionRepository transaccionesRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        Optional<Transaccion> transacciones = transaccionesRepository.findById(id);
        if (transacciones.isEmpty()) throw new NotFoundInformationException();
        TransaccionDTO transaccionesDTO = MapperUtils.DtoFromEntity(transacciones.get(), TransaccionDTO.class);
        return Optional.ofNullable(transaccionesDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaCreacionBetween(Long usuarioId, Date fechaCreacion, Date fechaFinalizacion) {
        List<Transaccion> transaccionList = transaccionesRepository.findByUsuarioIdAndFechaCreacionBetween(usuarioId, fechaCreacion, fechaFinalizacion);
        List<TransaccionDTO> transaccionDTOList = MapperUtils.DtoListFromEntityList(transaccionList, TransaccionDTO.class);
        if (transaccionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(transaccionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByIdAndFechaCreacionBetween(Long id, Date fechaCreacion, Date fechaFinalizacion) {
        List<Transaccion> transaccionList = transaccionesRepository.findByIdAndFechaCreacionBetween(id, fechaCreacion, fechaFinalizacion);
        List<TransaccionDTO> transaccionDTOList = MapperUtils.DtoListFromEntityList(transaccionList, TransaccionDTO.class);
        if (transaccionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(transaccionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByObjetoAndFechaCreacionBetween(String objetoId, Date fechaCreacion, Date fechaFinalizacion) {
        List<Transaccion> transaccionList = transaccionesRepository.findByObjetoAndFechaCreacionBetween(objetoId, fechaCreacion, fechaFinalizacion);
        List<TransaccionDTO> transaccionDTOList = MapperUtils.DtoListFromEntityList(transaccionList, TransaccionDTO.class);
        if (transaccionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(transaccionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByFechaCreacionBetween(Date fechaCreacion, Date fechaFinalizacion) {
        List<Transaccion> transaccionList = transaccionesRepository.findByFechaCreacionBetween(fechaCreacion, fechaFinalizacion);
        List<TransaccionDTO> transaccionDTOList = MapperUtils.DtoListFromEntityList(transaccionList, TransaccionDTO.class);
        if (transaccionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(transaccionDTOList);
    }

   /* private TransaccionDTO getSavedTransaccionDTO(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = MapperUtils.EntityFromDto(transaccionDTO, Transaccion.class);
        Transaccion transaccionCreated = transaccionRepository.save(transaccion);
        return MapperUtils.DtoFromEntity(transaccionCreated, TransaccionDTO.class);
    }*/

    public TransaccionDTO create(TransaccionDTO transaccionDTO) {
       // return Optional.ofNullable(getSavedTransaccionDTO(transaccionDTO));
        Transaccion transacciones = MapperUtils.EntityFromDto(transaccionDTO, Transaccion.class);
        Transaccion transaccionesCreated = transaccionesRepository.save(transacciones);
        return MapperUtils.DtoFromEntity(transaccionesCreated, TransaccionDTO.class);
    }
}
