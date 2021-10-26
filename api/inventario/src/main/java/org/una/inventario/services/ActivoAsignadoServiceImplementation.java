package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.entities.ActivoAsignado;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoAsignadoRepository;
import org.una.inventario.utils.MapperUtils;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivoAsignadoServiceImplementation implements ActivoAsignadoService {
    @Autowired
    private IActivoAsignadoRepository activoAsignadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ActivoAsignadoDTO> findById(Long id) {
        Optional<ActivoAsignado> activoAsignado = activoAsignadoRepository.findById(id);
        if (activoAsignado.isEmpty()) throw new NotFoundInformationException();
        ActivoAsignadoDTO activoAsignadoDTO = MapperUtils.DtoFromEntity(activoAsignado.get(), ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoAsignadoDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findAll() {
        List<ActivoAsignadoDTO> activoAsignadoDTOList = MapperUtils.DtoListFromEntityList(activoAsignadoRepository.findAll(), ActivoAsignadoDTO.class);
        if (activoAsignadoDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(activoAsignadoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findByEstadoActivo(String estado) {
        List<ActivoAsignado> activoAsignadoestadoList = activoAsignadoRepository.findByEstadoActivo(estado);
        List<ActivoAsignadoDTO> activoAsignadoestadoDTOList = MapperUtils.DtoListFromEntityList(activoAsignadoestadoList, ActivoAsignadoDTO.class);
        if (activoAsignadoestadoDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(activoAsignadoestadoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findByFechaCreacion(Date fechaCreacion) {
        List<ActivoAsignado> fechaCreacionList = activoAsignadoRepository.findByFechaCreacion(fechaCreacion);
        List<ActivoAsignadoDTO> fechaCreacionDTOList = MapperUtils.DtoListFromEntityList(fechaCreacionList, ActivoAsignadoDTO.class);
        if (fechaCreacionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(fechaCreacionDTOList);
    }

    private ActivoAsignadoDTO getSavedActivoAsignadoDTO(ActivoAsignadoDTO activoAsignadoDTO) {
        ActivoAsignado activoAsignado = MapperUtils.EntityFromDto(activoAsignadoDTO, ActivoAsignado.class);
        ActivoAsignado activoAsignadoCreated = activoAsignadoRepository.save(activoAsignado);
        return MapperUtils.DtoFromEntity(activoAsignadoCreated, ActivoAsignadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<ActivoAsignadoDTO> create(ActivoAsignadoDTO activoAsignadoDTO) {
        return Optional.ofNullable(getSavedActivoAsignadoDTO(activoAsignadoDTO));
    }

    @Override
    @Transactional
    public Optional<ActivoAsignadoDTO> update(ActivoAsignadoDTO activoAsignadoDTO, Long id) {
        if (activoAsignadoRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedActivoAsignadoDTO(activoAsignadoDTO));
    }
}
