package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.AlertasDTO;
import org.una.inventario.dto.ContratosGarantiasDTO;
import org.una.inventario.entities.Alertas;
import org.una.inventario.entities.ContratosGarantias;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.AlertasRespository;
import org.una.inventario.repositories.ContratosGarantiasRespository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ContratosGarantiasServiceImplementation implements ContratosGarantiasService{

    @Autowired
    private ContratosGarantiasRespository contratosGarantiasRespository;

    @Autowired
    private ContratosGarantiasService contratosGarantiasService;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ContratosGarantiasDTO>> findAll() {
        List<ContratosGarantiasDTO> contratosGarantiasDTOList = MapperUtils.DtoListFromEntityList(contratosGarantiasRespository.findAll(), ContratosGarantiasDTO.class);
        if (contratosGarantiasDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(contratosGarantiasDTOList );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContratosGarantiasDTO> findById(Long id) {
        Optional<ContratosGarantias> contratosGarantias = contratosGarantiasRespository.findById(id);
        if (contratosGarantias.isEmpty()) throw new NotFoundInformationException();
        ContratosGarantiasDTO contratosGarantiasDTO = MapperUtils.DtoFromEntity(contratosGarantias.get(), ContratosGarantiasDTO.class);
        return Optional.ofNullable(contratosGarantiasDTO);
    }

    private ContratosGarantiasDTO getSavedContratoGarantiaDTO(ContratosGarantiasDTO contratosGarantiasDTO) {
        ContratosGarantias contratosGarantias = MapperUtils.EntityFromDto(contratosGarantiasDTO, ContratosGarantias.class);
        ContratosGarantias contratosGarantiasCreated = contratosGarantiasRespository.save(contratosGarantias);
        return MapperUtils.DtoFromEntity(contratosGarantiasCreated, ContratosGarantiasDTO.class);
    }

    @Override
    @Transactional()
    public Optional<ContratosGarantiasDTO> create(ContratosGarantiasDTO contratosGarantiasDTO) {
        return Optional.ofNullable(getSavedContratoGarantiaDTO(contratosGarantiasDTO));
    }

    @Override
    @Transactional()
    public Optional<ContratosGarantiasDTO> update(ContratosGarantiasDTO contratosGarantiasDTO, Long id) {
        if (contratosGarantiasRespository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedContratoGarantiaDTO(contratosGarantiasDTO));
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        contratosGarantiasRespository.deleteById(id);
    }

    @Override
    @Transactional()
    public void deleteAll() {
        contratosGarantiasRespository.deleteAll();
    }
}
