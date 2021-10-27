package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.dto.ValuacionesDTO;
import org.una.inventario.entities.ActivoAsignado;
import org.una.inventario.entities.Valuaciones;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoAsignadoRepository;
import org.una.inventario.repositories.ValuacionesRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ValuacionesServiceImplementation implements ValuacionesService{
    @Autowired
    private ValuacionesRepository valuacionesRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ValuacionesDTO> findById(Long id) {
        Optional<Valuaciones> valuaciones = valuacionesRepository.findById(id);
        if (valuaciones.isEmpty()) throw new NotFoundInformationException();
        ValuacionesDTO valuacionesDTO = MapperUtils.DtoFromEntity(valuaciones.get(), ValuacionesDTO.class);
        return Optional.ofNullable(valuacionesDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionesDTO>> findAll() {
        List<ValuacionesDTO> valuacionesDTOList = MapperUtils.DtoListFromEntityList(valuacionesRepository.findAll(), ValuacionesDTO.class);
        if (valuacionesDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(valuacionesDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionesDTO>> findByPrecioValuacion(Double precioValuacion) {
        List<Valuaciones> valuacionesList = valuacionesRepository.findByPrecioValuacion( precioValuacion);
        List<ValuacionesDTO> valuacionesDTOList = MapperUtils.DtoListFromEntityList(valuacionesList, ValuacionesDTO.class);
        if (valuacionesDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(valuacionesDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionesDTO>> findByFechaCreacion(Date fechaCreacion) {
        List<Valuaciones> fechaCreacionList = valuacionesRepository.findByFechaCreacion(fechaCreacion);
        List<ValuacionesDTO> fechaCreacionDTOList = MapperUtils.DtoListFromEntityList(fechaCreacionList, ValuacionesDTO.class);
        if (fechaCreacionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(fechaCreacionDTOList);
    }

    private ValuacionesDTO getSavedValuacionesDTO(ValuacionesDTO valuacionesDTO) {
        Valuaciones valuaciones = MapperUtils.EntityFromDto(valuacionesDTO, Valuaciones.class);
        Valuaciones valuacionesCreated = valuacionesRepository.save(valuaciones);
        return MapperUtils.DtoFromEntity(valuacionesCreated, ValuacionesDTO.class);
    }

    @Override
    @Transactional
    public Optional<ValuacionesDTO> create(ValuacionesDTO valuacionesDTO) {
        return Optional.ofNullable(getSavedValuacionesDTO(valuacionesDTO));
    }

    @Override
    @Transactional
    public Optional<ValuacionesDTO> update(ValuacionesDTO valuacionesDTO, Long id) {
        if (valuacionesRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedValuacionesDTO(valuacionesDTO));
    }

}
