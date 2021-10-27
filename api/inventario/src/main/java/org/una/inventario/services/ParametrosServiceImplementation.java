package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ParametrosDTO;
import org.una.inventario.dto.ValuacionesDTO;
import org.una.inventario.entities.Parametros;
import org.una.inventario.entities.Valuaciones;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.ParametrosRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ParametrosServiceImplementation implements ParametrosService{

    @Autowired
    private ParametrosRepository parametrosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametrosDTO> findById(Long id) {
        Optional<Parametros> parametros = parametrosRepository.findById(id);
        if (parametros.isEmpty()) throw new NotFoundInformationException();
        ParametrosDTO parametrosDTO = MapperUtils.DtoFromEntity(parametros.get(), ParametrosDTO.class);
        return Optional.ofNullable(parametrosDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosDTO>> findAll() {
        List<ParametrosDTO> parametrosDTOList = MapperUtils.DtoListFromEntityList(parametrosRepository.findAll(), ParametrosDTO.class);
        if (parametrosDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(parametrosDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosDTO>> findByNombre(String nombre) {
        List<Parametros> parametrosList = parametrosRepository.findByNombre(nombre);
        List<ParametrosDTO> parametrosDTOList = MapperUtils.DtoListFromEntityList(parametrosList, ParametrosDTO.class);
        if (parametrosDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(parametrosDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosDTO>> findByEstado(String estado) {
        List<Parametros> parametrosList = parametrosRepository.findByEstado(estado);
        List<ParametrosDTO> parametrosDTOList = MapperUtils.DtoListFromEntityList(parametrosList, ParametrosDTO.class);
        if (parametrosDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(parametrosDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosDTO>> findByFechaCreacionBetween(Date fechaCreacion, Date fechaModificacion) {
        List<Parametros> Fechaactivolist = parametrosRepository.findByFechaCreacionBetween(fechaCreacion, fechaModificacion);
        List<ParametrosDTO> FechaactivoDtolist = MapperUtils.DtoListFromEntityList(Fechaactivolist,ParametrosDTO.class);
        return Optional.ofNullable(FechaactivoDtolist);
    }

    private ParametrosDTO getSavedParametrosDTO(ParametrosDTO parametrosDTO) {
        Parametros parametros = MapperUtils.EntityFromDto(parametrosDTO, Parametros.class);
        Parametros parametrosCreated = parametrosRepository.save(parametros);
        return MapperUtils.DtoFromEntity(parametrosCreated, ParametrosDTO.class);
    }

    @Override
    @Transactional
    public Optional<ParametrosDTO> create(ParametrosDTO parametrosDTO) {
        return Optional.ofNullable(getSavedParametrosDTO(parametrosDTO));
    }

    @Override
    @Transactional
    public Optional<ParametrosDTO> update(ParametrosDTO parametrosDTO, Long id) {
        if (parametrosRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedParametrosDTO(parametrosDTO));
    }
}
