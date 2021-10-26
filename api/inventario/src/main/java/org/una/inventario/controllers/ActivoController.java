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
    @ApiOperation(value = "Obtiene una lista de todos los activos", response = ActivoDTO.class, responseContainer = "List", tags = "Activos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ActivoDTO>> result = activoService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/findByNombre/{nombreMarca}")
    @ApiOperation(value = "Obtiene una lista de activos a partir de su nombre", response = ActivoDTO.class, responseContainer = "List", tags = "Activos")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombreActivo") String nombreActivo) {
        Optional<List<ActivoDTO>>activoFound= activoService.findByNombre(nombreActivo);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @GetMapping("/findByEstadoActivo/{estadoActivo}")
    @ApiOperation(value = "Obtiene una lista de activos a partir de su estado", response = ActivoDTO.class, responseContainer = "List", tags = "Activos")
    public ResponseEntity<?> findByEstadoActivo(@PathVariable(value = "estadoActivo") String estadoActivo) {
        Optional<List<ActivoDTO>>activoFound= activoService.findByEstadoActivo(estadoActivo);
        return new ResponseEntity<>(activoFound, HttpStatus.OK);
    }

    @GetMapping("/findByFechaCreacionBetween/{fechaCreacion}/{endDate}")
    @ApiOperation(value = "Obtiene una lista de Activos por fecha de creacion", response = ActivoDTO.class, responseContainer = "ActivoDTO" , tags = "Activos")
    public ResponseEntity<?> findByFechaCreacionBetween(@PathVariable(value = "fechaCreacion") @DateTimeFormat(pattern = "yyyy-MM-yy") Date fechaCreacion, @PathVariable(value = "fechaModificacion") @DateTimeFormat(pattern = "yyyy-MM-yy")Date fechaModificacion){
        try{
            Optional<List<ActivoDTO>> result = activoService.findByFechaCreacionBetween(fechaCreacion, fechaModificacion);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
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
