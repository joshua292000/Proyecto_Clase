package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.AlertasDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Alertas;
import org.una.inventario.entities.Usuario;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.AlertasRespository;
import org.una.inventario.repositories.IUsuarioRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AlertasServiceImplementation implements AlertasService{

    @Autowired
    private AlertasRespository alertasRespository;

    @Autowired
    private AlertasService alertasService;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertasDTO>> findAll() {
        List<AlertasDTO> alertaDTOList = MapperUtils.DtoListFromEntityList(alertasRespository.findAll(), AlertasDTO.class);
        if (alertaDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(alertaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlertasDTO> findById(Long id) {
        Optional<Alertas> alertas = alertasRespository.findById(id);
        if (alertas.isEmpty()) throw new NotFoundInformationException();
        AlertasDTO alertasDTO = MapperUtils.DtoFromEntity(alertas.get(), AlertasDTO.class);
        return Optional.ofNullable(alertasDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlertasDTO> findByTipo(String tipo) {
        Optional<Alertas> alertas = alertasRespository.findByTipo(tipo);
        if (alertas.isEmpty()) throw new NotFoundInformationException();
        AlertasDTO alertasDTO = MapperUtils.DtoFromEntity(alertas.get(), AlertasDTO.class);
        return Optional.ofNullable(alertasDTO);
    }

    private AlertasDTO getSavedAlertaDTO(AlertasDTO alertasDTO) {
        Alertas alertas = MapperUtils.EntityFromDto(alertasDTO, Alertas.class);
        Alertas alertasCreated = alertasRespository.save(alertas);
        return MapperUtils.DtoFromEntity(alertasCreated, AlertasDTO.class);
    }

    @Override
    @Transactional
    public Optional<AlertasDTO> create(AlertasDTO alertasDTO) {
        return Optional.ofNullable(getSavedAlertaDTO(alertasDTO));
    }

    @Override
    @Transactional
    public Optional<AlertasDTO> update(AlertasDTO alertasDTO, Long id) {
        if (alertasRespository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedAlertaDTO(alertasDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alertasRespository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        alertasRespository.deleteAll();
    }
}
