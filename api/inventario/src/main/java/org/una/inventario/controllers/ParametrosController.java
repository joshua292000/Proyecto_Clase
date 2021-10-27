package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ParametrosDTO;
import org.una.inventario.services.ParametrosService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/parametros")
@Api(tags = {"Parametros"})
public class ParametrosController {
    @Autowired
    private ParametrosService parametrosService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un activo a partir de su id", response = ParametrosDTO.class, tags = "Parametros")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ParametrosDTO> parametrosFound = parametrosService.findById(id);
        return new ResponseEntity<>(parametrosFound, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los parametros", response = ParametrosDTO.class, responseContainer = "List", tags = "Parametros")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ParametrosDTO>> result = parametrosService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/findByNombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista de parametros a partir de su nombre", response = ParametrosDTO.class, responseContainer = "List", tags = "Parametros")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre") String nombre) {
        Optional<List<ParametrosDTO>>parametrosFound= parametrosService.findByNombre(nombre);
        return new ResponseEntity<>(parametrosFound, HttpStatus.OK);
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de parametros a partir de su estado", response = ParametrosDTO.class, responseContainer = "List", tags = "Parametros")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estado) {
        Optional<List<ParametrosDTO>>parametrosFound= parametrosService.findByEstado(estado);
        return new ResponseEntity<>(parametrosFound, HttpStatus.OK);
    }

    @GetMapping("/findByFechaCreacionBetween/{fechaCreacion}/{fechaModificacion}")
    @ApiOperation(value = "Obtiene una lista de parametros por fecha de creacion", response = ParametrosDTO.class, responseContainer = "ParametrosDTO" , tags = "Parametros")
    public ResponseEntity<?> findByFechaCreacionBetween(@PathVariable(value = "fechaCreacion") @DateTimeFormat(pattern = "yyyy-MM-yy") Date fechaCreacion, @PathVariable(value = "fechaModificacion") @DateTimeFormat(pattern = "yyyy-MM-yy")Date fechaModificacion){
        try{
            Optional<List<ParametrosDTO>> result = parametrosService.findByFechaCreacionBetween(fechaCreacion, fechaModificacion);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ParametrosDTO parametrosDTO) {
        try {
            Optional<ParametrosDTO> parametrosCreated =parametrosService.create(parametrosDTO);
            return new ResponseEntity<>(parametrosCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id del parametro", response = ParametrosDTO.class, tags = "Parametros")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametrosDTO parametrosModified) {
        Optional<ParametrosDTO> parametrosUpdated = parametrosService.update(parametrosModified, id);
        return new ResponseEntity<>(parametrosUpdated, HttpStatus.OK);
    }
}
