package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.InventariosDTO;
import org.una.inventario.entities.Inventarios;
import org.una.inventario.services.InventariosService;

import java.util.Date;
import java.util.Optional;
import java.util.List;
@RestController
@RequestMapping("/inventarios")
@Api(tags = {"Inventarios"})
public class InventariosController {
    @Autowired

    private InventariosService inventariosService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una inventarios a partir de su id", response = InventariosDTO.class, tags = "Inventarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<InventariosDTO> inventariosFound = inventariosService.findById(id);
        return new ResponseEntity<>(inventariosFound, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las inventarios", response = InventariosDTO.class, responseContainer = "List", tags = "Inventarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<InventariosDTO>> result = inventariosService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findByDescripcion/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de inventarios a partir de su descripcion", response = InventariosDTO.class, responseContainer = "List", tags = "Inventarios")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion") String descripcion) {
        Optional<List<InventariosDTO>>inventariosFound= inventariosService.findByDescripcion(descripcion);
        return new ResponseEntity<>(inventariosFound, HttpStatus.OK);
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de inventarios a partir de su estado", response = InventariosDTO.class, responseContainer = "List", tags = "Inventarios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estado) {
        Optional<List<InventariosDTO>>inventariosFound= inventariosService.findByEstado(estado);
        return new ResponseEntity<>(inventariosFound, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody InventariosDTO inventariosDTO) {
        try {
            Optional<InventariosDTO> inventariosCreated =inventariosService.create(inventariosDTO);
            return new ResponseEntity<>(inventariosCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id de los inventarios", response = InventariosDTO.class, tags = "Inventarios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody InventariosDTO inventariosModified) {
        Optional<InventariosDTO> inventariosUpdated = inventariosService.update(inventariosModified, id);
        return new ResponseEntity<>(inventariosUpdated, HttpStatus.OK);
    }
}
