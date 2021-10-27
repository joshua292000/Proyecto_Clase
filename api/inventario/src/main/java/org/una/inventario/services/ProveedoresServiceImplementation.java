package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ContratosGarantiasDTO;
import org.una.inventario.dto.ProveedoresDTO;
import org.una.inventario.entities.ContratosGarantias;
import org.una.inventario.entities.Proveedores;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.ProveedoresRespository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedoresServiceImplementation implements ProveedoresService{

    @Autowired
    ProveedoresRespository proveedoresRespository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ProveedoresDTO>> findAll() {
        List<ProveedoresDTO> proveedoresDTOList = MapperUtils.DtoListFromEntityList(proveedoresRespository.findAll(), ProveedoresDTO.class);
        if (proveedoresDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(proveedoresDTOList );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProveedoresDTO> findByNombre(String nombre) {
        Optional<Proveedores> proveedores = proveedoresRespository.findByNombre(nombre);
        if (proveedores.isEmpty()) throw new NotFoundInformationException();
        ProveedoresDTO proveedoresDTO = MapperUtils.DtoFromEntity(proveedores.get(), ProveedoresDTO.class);
        return Optional.ofNullable(proveedoresDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProveedoresDTO> findById(Long id) {
        Optional<Proveedores> proveedores = proveedoresRespository.findById(id);
        if (proveedores.isEmpty()) throw new NotFoundInformationException();
        ProveedoresDTO proveedoresDTO = MapperUtils.DtoFromEntity(proveedores.get(), ProveedoresDTO.class);
        return Optional.ofNullable(proveedoresDTO);
    }

    private ProveedoresDTO getSavedProveedoresDTO(ProveedoresDTO proveedoresDTO) {
        Proveedores proveedores = MapperUtils.EntityFromDto(proveedoresDTO, Proveedores.class);
        Proveedores proveedoresCreated = proveedoresRespository.save(proveedores);
        return MapperUtils.DtoFromEntity(proveedoresCreated, ProveedoresDTO.class);
    }


    @Override
    @Transactional
    public Optional<ProveedoresDTO> create(ProveedoresDTO proveedoresDTO) {
        return Optional.ofNullable(getSavedProveedoresDTO(proveedoresDTO));
    }

    @Override
    @Transactional
    public Optional<ProveedoresDTO> update(ProveedoresDTO proveedoresDTO, Long id) {
        if (proveedoresRespository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedProveedoresDTO(proveedoresDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        proveedoresRespository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        proveedoresRespository.deleteAll();
    }
}
