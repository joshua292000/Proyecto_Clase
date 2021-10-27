package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.CategoriasDTO;
import org.una.inventario.entities.Categorias;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.CategoriasRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CategoriasServiceImplementation implements CategoriasService {
    @Autowired
    private CategoriasRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriasDTO> findById(Long id) {
        Optional<Categorias> categorias = categoriaRepository.findById(id);
        if (categorias.isEmpty()) throw new NotFoundInformationException();
        CategoriasDTO categoriasDTO = MapperUtils.DtoFromEntity(categorias.get(), CategoriasDTO.class);
        return Optional.ofNullable(categoriasDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CategoriasDTO>> findAll() {
        List<CategoriasDTO> categoriasDTOList = MapperUtils.DtoListFromEntityList(categoriaRepository.findAll(), CategoriasDTO.class);
        if (categoriasDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(categoriasDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CategoriasDTO>> findByNombre(String nombre) {
        List<Categorias> categoriasList = categoriaRepository.findByNombre( nombre);
        List<CategoriasDTO> categoriasDTOList = MapperUtils.DtoListFromEntityList(categoriasList, CategoriasDTO.class);
        if (categoriasDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(categoriasDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CategoriasDTO>> findByEstado(String estado) {
        List<Categorias> categoriasList = categoriaRepository.findByEstado(estado);
        List<CategoriasDTO> categoriasDTOList = MapperUtils.DtoListFromEntityList(categoriasList, CategoriasDTO.class);
        if (categoriasDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(categoriasDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CategoriasDTO>> findByFechaCreacion(Date fechaCreacion) {
        List<Categorias> categoriasList = categoriaRepository.findByFechaCreacion(fechaCreacion);
        List<CategoriasDTO> categoriasDTOList = MapperUtils.DtoListFromEntityList(categoriasList, CategoriasDTO.class);
        if (categoriasDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(categoriasDTOList);
    }

    private CategoriasDTO getSavedCategoriasDTO(CategoriasDTO categoriasDTO) {
        Categorias valuaciones = MapperUtils.EntityFromDto(categoriasDTO, Categorias.class);
        Categorias categoriasCreated = categoriaRepository.save(valuaciones);
        return MapperUtils.DtoFromEntity(categoriasCreated, CategoriasDTO.class);
    }

    @Override
    @Transactional
    public Optional<CategoriasDTO> create(CategoriasDTO categoriasDTO) {
        return Optional.ofNullable(getSavedCategoriasDTO(categoriasDTO));
    }

    @Override
    @Transactional
    public Optional<CategoriasDTO> update(CategoriasDTO categoriasDTO, Long id) {
        if (categoriaRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedCategoriasDTO(categoriasDTO));
    }
}
