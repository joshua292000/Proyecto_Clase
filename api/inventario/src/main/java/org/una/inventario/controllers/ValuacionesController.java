package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ValuacionesDTO;
import org.una.inventario.services.ValuacionesService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/valuaciones")
@Api(tags = {"Valuaciones"})
public class ValuacionesController {

    @Autowired

    private ValuacionesService valuacionesService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una valuaciones a partir de su id", response = ValuacionesDTO.class, tags = "Valuaciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ValuacionesDTO> valuacionesFound = valuacionesService.findById(id);
        return new ResponseEntity<>(valuacionesFound, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las valuaciones", response = ValuacionesDTO.class, responseContainer = "List", tags = "Valuaciones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ValuacionesDTO>> result = valuacionesService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findByPrecioValuacion/{precioValuacion}")
    @ApiOperation(value = "Obtiene una lista de valuaciones a partir de su descripcion", response = ValuacionesDTO.class, responseContainer = "List", tags = "Valuaciones")
    public ResponseEntity<?> findByPrecioValuacion(@PathVariable(value = "descripcion") Double precioValuacion) {
        Optional<List<ValuacionesDTO>>valuacionesFound= valuacionesService.findByPrecioValuacion(precioValuacion);
        return new ResponseEntity<>(valuacionesFound, HttpStatus.OK);
    }

    @GetMapping("/findByFechaCreacion/{fechaCreacion}")
    @ApiOperation(value = "Obtiene una lista de valuaciones a partir de su FechaCreacion", response = ValuacionesDTO.class, responseContainer = "List", tags = "Valuaciones")
    public ResponseEntity<?> findByFechaCreacion(@PathVariable(value = "fechaCreacion") Date fechaCreacion) {
        Optional<List<ValuacionesDTO>>valuacionesFound= valuacionesService.findByFechaCreacion(fechaCreacion);
        return new ResponseEntity<>(valuacionesFound, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ValuacionesDTO valuacionesDTO) {
        try {
            Optional<ValuacionesDTO> valuacionesCreated = valuacionesService.create(valuacionesDTO);
            return new ResponseEntity<>(valuacionesCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id de las valuaciones", response = ValuacionesDTO.class, tags = "Inventarios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ValuacionesDTO valuacionesModified) {
        Optional<ValuacionesDTO> valuacionesUpdated = valuacionesService.update(valuacionesModified, id);
        return new ResponseEntity<>(valuacionesUpdated, HttpStatus.OK);
    }
}
