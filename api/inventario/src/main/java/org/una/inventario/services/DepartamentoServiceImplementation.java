package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.entities.Departamento;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.DepartamentoRepository;
import org.una.inventario.utils.MapperUtils;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImplementation implements DepartamentoService{

    @Autowired
    private DepartamentoRepository departamentoRepository;

    private DepartamentoDTO getSavedDepartamentoDTO(DepartamentoDTO departamentoDTO) {
        Departamento departamento = MapperUtils.EntityFromDto(departamentoDTO, Departamento.class);
        Departamento departamentoCreated = departamentoRepository.save(departamento);
        return MapperUtils.DtoFromEntity(departamentoCreated, DepartamentoDTO.class);
    }

    @Override
    @Transactional
    public Optional<DepartamentoDTO> create(DepartamentoDTO departamentoDTO) {
        return Optional.ofNullable(getSavedDepartamentoDTO(departamentoDTO));
    }

    @Override
    @Transactional
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamentoDTO, Long id) {
        if (departamentoRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedDepartamentoDTO(departamentoDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        departamentoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        departamentoRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
        List<DepartamentoDTO> departamentoDTOList = MapperUtils.DtoListFromEntityList(departamentoRepository.findAll(), DepartamentoDTO.class);
        if (departamentoDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(departamentoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>>findByEstadoProximo(boolean estado) {
        List<Departamento> departamentoList = departamentoRepository.findByEstado(estado);
        if (departamentoList.isEmpty()) throw new NotFoundInformationException();
        List<DepartamentoDTO> departamentoDTOList = MapperUtils.DtoListFromEntityList(departamentoList, DepartamentoDTO.class);
        return Optional.ofNullable(departamentoDTOList);
    }
}
