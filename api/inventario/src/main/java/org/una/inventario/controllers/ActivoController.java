package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.services.ActivoService;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activo")
@Api(tags = {"Activo"})
public class ActivoController {
    @Autowired
    private ActivoService activoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un activo a partir de su id", response = ActivoDTO.class, tags = "Activo")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ActivoDTO> activoFound = activoService.findById(id);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los activos", response = ActivoDTO.class, responseContainer = "List", tags = "Activo")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ActivoDTO>> result = activoService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/findByNombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista de activos a partir de su nombre", response = ActivoDTO.class, responseContainer = "List", tags = "Activo")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre") String nombreActivo) {
        Optional<List<ActivoDTO>>activoFound= activoService.findByNombre(nombreActivo);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de activos a partir de su estado", response = ActivoDTO.class, responseContainer = "List", tags = "Activo")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estadoActivo) {
        Optional<List<ActivoDTO>>activoFound= activoService.findByEstado(estadoActivo);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @GetMapping("/findByFechaCreacionBetween/{fechaCreacion}/{fechaModificacion}")
    @ApiOperation(value = "Obtiene una lista de Activos por fecha de creacion", response = ActivoDTO.class, responseContainer = "ActivoDTO" , tags = "Activo")
    public ResponseEntity<?> findByFechaCreacionBetween(@PathVariable(value = "fechaCreacion") @DateTimeFormat(pattern = "yyyy-MM-yy") Date fechaCreacion, @PathVariable(value = "fechaModificacion") @DateTimeFormat(pattern = "yyyy-MM-yy")Date fechaModificacion){
        try{
            Optional<List<ActivoDTO>> result = activoService.findByFechaCreacionBetween(fechaCreacion, fechaModificacion);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByActivosxMarcaDescBetweenFechas/{idMarcaProve}/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de activos descendente de acuerdo a la marca y dos fechas dadas", response = ActivoDTO.class, responseContainer = "ActivoDTO", tags = "Activo")
    public ResponseEntity<?> findByActivosxMarcaDescBetweenFechas(@PathVariable(value = "idMarcaProve") Long idMarcaProve, @PathVariable(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            Optional<List<ActivoDTO>> result = activoService.findByActivosxMarcaDescBetweenFechas(idMarcaProve,startDate,endDate);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByActivosxMarcaAscBetweenFechas/{idMarcaProve}/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de activos ascendente de acuerdo a la marca y dos fechas dadas", response = ActivoDTO.class, responseContainer = "ActivoDTO", tags = "Activo")
    public ResponseEntity<?> findByActivosxMarcaAscBetweenFechas(@PathVariable(value = "idMarcaProve") Long idMarcaProve, @PathVariable(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            Optional<List<ActivoDTO>> result = activoService.findByActivosxMarcaAscBetweenFechas(idMarcaProve,startDate,endDate);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByActivosxProveDescBetweenFechas/{idMarcaProve}/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de activos descendente de acuerdo a los proveedores y dos fechas dadas", response = ActivoDTO.class, responseContainer = "ActivoDTO", tags = "Activo")
    public ResponseEntity<?> findByActivosxProveDescBetweenFechas(@PathVariable(value = "idMarcaProve") Long idMarcaProve, @PathVariable(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            Optional<List<ActivoDTO>> result = activoService.findByActivosxProveDescBetweenFechas(idMarcaProve,startDate,endDate);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByActivosxProveAscBetweenFechas/{idMarcaProve}/{startDate}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de activos ascendente de acuerdo a los proveedores y dos fechas dadas", response = ActivoDTO.class, responseContainer = "ActivoDTO", tags = "Activo")
    public ResponseEntity<?> findByActivosxProveAscBetweenFechas(@PathVariable(value = "idMarcaProve") Long idMarcaProve, @PathVariable(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            Optional<List<ActivoDTO>> result = activoService.findByActivosxProveAscBetweenFechas(idMarcaProve,startDate,endDate);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }  catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ActivoDTO activoDTO) {
        try {
            Optional<ActivoDTO> activoCreated =activoService.create(activoDTO);
            return new ResponseEntity<>(activoCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
        @ApiOperation(value = "Actualiza por medio del id del activo", response = ActivoDTO.class, tags = "Activo")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ActivoDTO activoModified) {
        Optional<ActivoDTO> activoUpdated = activoService.update(activoModified, id);
        return new ResponseEntity<>(activoUpdated, HttpStatus.OK);
    }
}
