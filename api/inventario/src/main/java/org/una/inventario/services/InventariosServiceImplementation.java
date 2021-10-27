package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.InventariosDTO;
import org.una.inventario.entities.Inventarios;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.InventariosRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;
@Service
public class InventariosServiceImplementation implements InventariosService {

    @Autowired
    private InventariosRepository inventarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<InventariosDTO> findById(Long id) {
        Optional<Inventarios> inventarios = inventarioRepository.findById(id);
        if (inventarios.isEmpty()) throw new NotFoundInformationException();
        InventariosDTO inventariosDTO = MapperUtils.DtoFromEntity(inventarios.get(), InventariosDTO.class);
        return Optional.ofNullable(inventariosDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<InventariosDTO>> findAll() {
        List<InventariosDTO> inventariosDTOList = MapperUtils.DtoListFromEntityList(inventarioRepository.findAll(), InventariosDTO.class);
        if (inventariosDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(inventariosDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<InventariosDTO>> findByDescripcion(String descripcion) {
        List<Inventarios> inventariosList = inventarioRepository.findByDescripcion(descripcion);
        List<InventariosDTO> inventariosDTOList = MapperUtils.DtoListFromEntityList(inventariosList, InventariosDTO.class);
        if (inventariosDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(inventariosDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<InventariosDTO>> findByEstado(String estado) {
        List<Inventarios> inventariosList = inventarioRepository.findByEstado(estado);
        List<InventariosDTO> inventariosDTOList = MapperUtils.DtoListFromEntityList(inventariosList, InventariosDTO.class);
        if (inventariosDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(inventariosDTOList);
    }

    private InventariosDTO getSavedInventariosDTO(InventariosDTO inventariosDTO) {
        Inventarios inventarios = MapperUtils.EntityFromDto(inventariosDTO, Inventarios.class);
        Inventarios inventariosCreated = inventarioRepository.save(inventarios);
        return MapperUtils.DtoFromEntity(inventariosCreated, InventariosDTO.class);
    }

    @Override
    @Transactional
    public Optional<InventariosDTO> create(InventariosDTO inventariosDTO) {
        return Optional.ofNullable(getSavedInventariosDTO(inventariosDTO));
    }

    @Override
    @Transactional
    public Optional<InventariosDTO> update(InventariosDTO inventariosDTO, Long id) {
        if (inventarioRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedInventariosDTO(inventariosDTO));
    }
}
