package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.dto.ProveedoresDTO;
import org.una.inventario.entities.Marca;
import org.una.inventario.entities.Proveedores;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IMarcaRepository;
import org.una.inventario.utils.MapperUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImplementation implements MarcaService {
    @Autowired
    private IMarcaRepository marcaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<MarcaDTO> findById(Long id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isEmpty()) throw new NotFoundInformationException();
        MarcaDTO marcaDTO = MapperUtils.DtoFromEntity(marca.get(), MarcaDTO.class);
        return Optional.ofNullable(marcaDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<MarcaDTO>> findAll() {
        List<MarcaDTO> marcaDTOList = MapperUtils.DtoListFromEntityList(marcaRepository.findAll(), MarcaDTO.class);
        if (marcaDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(marcaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MarcaDTO> findByNombre(String nombre) {
        Optional<Marca> marca = marcaRepository.findByNombre(nombre);
        if (marca.isEmpty()) throw new NotFoundInformationException();
        MarcaDTO marcaDTO = MapperUtils.DtoFromEntity(marca.get(), MarcaDTO.class);
        return Optional.ofNullable(marcaDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<MarcaDTO>> findByEstado(String estado) {
        List<Marca> estadoList = marcaRepository.findByEstado(estado);
        List<MarcaDTO> estadoDTOList = MapperUtils.DtoListFromEntityList(estadoList, MarcaDTO.class);
        if (estadoDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(estadoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<MarcaDTO>> findByFechaCreacion(Date fechaCreacion) {
        List<Marca> fechaCreacionList = marcaRepository.findByFechaCreacion(fechaCreacion);
        List<MarcaDTO> fechaCreacionDTOList = MapperUtils.DtoListFromEntityList(fechaCreacionList, MarcaDTO.class);
        if (fechaCreacionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(fechaCreacionDTOList);
    }

    private MarcaDTO getSavedMarcaDTO(MarcaDTO marcaDTO) {
        Marca marca = MapperUtils.EntityFromDto(marcaDTO, Marca.class);
        Marca marcaCreated = marcaRepository.save(marca);
        return MapperUtils.DtoFromEntity(marcaCreated, MarcaDTO.class);
    }
    @Override
    @Transactional
    public Optional<MarcaDTO> create(MarcaDTO marcaDTO) {
        return Optional.ofNullable(getSavedMarcaDTO(marcaDTO));
    }

    @Override
    @Transactional
    public Optional<MarcaDTO> update(MarcaDTO marcaDTO, Long id) {
        if (marcaRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedMarcaDTO(marcaDTO));
    }
}
