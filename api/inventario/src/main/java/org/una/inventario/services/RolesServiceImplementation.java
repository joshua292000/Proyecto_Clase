package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.RolesDTO;
import org.una.inventario.entities.Roles;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.RolesRepository;
import org.una.inventario.utils.MapperUtils;
import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImplementation implements RolesService{
    @Autowired
    private RolesRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolesDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        List<Roles> rolList = rolRepository.findByNombreContainingIgnoreCase(nombre);
        List<RolesDTO> rolDTOList = MapperUtils.DtoListFromEntityList(rolList, RolesDTO.class);
        if (rolDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(rolDTOList);
    }

    private RolesDTO getSavedRolDTO(RolesDTO rolDTO) {
        Roles rol = MapperUtils.EntityFromDto(rolDTO, Roles.class);
        Roles rolCreated = rolRepository.save(rol);
        return MapperUtils.DtoFromEntity(rolCreated, RolesDTO.class);
    }

    @Override
    @Transactional
    public Optional<RolesDTO> create(RolesDTO rolDTO) {
        return Optional.ofNullable(getSavedRolDTO(rolDTO));
    }

    @Override
    @Transactional
    public Optional<RolesDTO> update(RolesDTO rolDTO, Long id) {
        if (rolRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedRolDTO(rolDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        rolRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findById(Long id) {
        Optional<Roles> rol = rolRepository.findById(id);
        if (rol.isEmpty()) throw new NotFoundInformationException();
        RolesDTO rolDTO = MapperUtils.DtoFromEntity(rol.get(), RolesDTO.class);
        return Optional.ofNullable(rolDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolesDTO>> findAll() {
        List<RolesDTO> rolDTOList = MapperUtils.DtoListFromEntityList(rolRepository.findAll(), RolesDTO.class);
        if (rolDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(rolDTOList);
    }


}
