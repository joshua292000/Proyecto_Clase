package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivoServiceImplementation implements ActivoService{
    @Autowired
    private IActivoRepository activoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ActivoDTO> findById(Long id) {
        Optional<Activo> activo = activoRepository.findById(id);
        if (activo.isEmpty()) throw new NotFoundInformationException();
        ActivoDTO activoDTO = MapperUtils.DtoFromEntity(activo.get(), ActivoDTO.class);
        return Optional.ofNullable(activoDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findAll() {
        List<ActivoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoRepository.findAll(), ActivoDTO.class);
        if (activoDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByNombre(String nombre) {
        List<Activo> activoList = activoRepository.findByNombre(nombre);
        List<ActivoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoList, ActivoDTO.class);
        if (activoDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByEstado(String estado) {
        List<Activo> activoestadoList = activoRepository.findByEstado(estado);
        List<ActivoDTO> activoestadoDTOList = MapperUtils.DtoListFromEntityList(activoestadoList, ActivoDTO.class);
        if (activoestadoDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(activoestadoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByFechaCreacionBetween(Date fechaCreacion, Date fechaModificacion) {
        List<Activo> Fechaactivolist = activoRepository.findByFechaCreacionBetween(fechaCreacion, fechaModificacion);
        List<ActivoDTO> FechaactivoDtolist = MapperUtils.DtoListFromEntityList(Fechaactivolist,ActivoDTO.class);
        return Optional.ofNullable(FechaactivoDtolist);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByActivosxMarcaDescBetweenFechas(Long idMarcaProve, Date startDate, Date endDate) {
        List<Activo> ActivoList = activoRepository.findByActivosxMarcaDescBetweenFechas(idMarcaProve,startDate,endDate);
        List<ActivoDTO> ActivoListDTOList = MapperUtils.DtoListFromEntityList(ActivoList,ActivoDTO.class);
        return Optional.ofNullable(ActivoListDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByActivosxMarcaAscBetweenFechas(Long idMarcaProve, Date startDate, Date endDate) {
        List<Activo> ActivoList = activoRepository.findByActivosxMarcaAscBetweenFechas(idMarcaProve,startDate,endDate);
        List<ActivoDTO> ActivoListDTOList = MapperUtils.DtoListFromEntityList(ActivoList,ActivoDTO.class);
        return Optional.ofNullable(ActivoListDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByActivosxProveDescBetweenFechas(Long idMarcaProve, Date startDate, Date endDate) {
        List<Activo> ActivoList = activoRepository.findByActivosxProveDescBetweenFechas(idMarcaProve,startDate,endDate);
        List<ActivoDTO> ActivoListDTOList = MapperUtils.DtoListFromEntityList(ActivoList,ActivoDTO.class);
        return Optional.ofNullable(ActivoListDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByActivosxProveAscBetweenFechas(Long idMarcaProve, Date startDate, Date endDate) {
        List<Activo> ActivoList = activoRepository.findByActivosxProveAscBetweenFechas(idMarcaProve,startDate,endDate);
        List<ActivoDTO> ActivoListDTOList = MapperUtils.DtoListFromEntityList(ActivoList,ActivoDTO.class);
        return Optional.ofNullable(ActivoListDTOList);
    }


    private ActivoDTO getSavedActivoDTO(ActivoDTO activoDTO) {
        Activo activo = MapperUtils.EntityFromDto(activoDTO, Activo.class);
        Activo activoCreated = activoRepository.save(activo);
        return MapperUtils.DtoFromEntity(activoCreated, ActivoDTO.class);
    }

    @Override
    @Transactional
    public Optional<ActivoDTO> create(ActivoDTO activoDTO) {
        return Optional.ofNullable(getSavedActivoDTO(activoDTO));
    }

    @Override
    @Transactional
    public Optional<ActivoDTO> update(ActivoDTO activoDTO, Long id) {
        if (activoRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedActivoDTO(activoDTO));
    }
}
