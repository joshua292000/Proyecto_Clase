package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.CategoriasDTO;
import org.una.inventario.services.CategoriasService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@Api(tags = {"Categorias"})
public class CategoriasController {
    @Autowired
    private CategoriasService categoriasService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una categoria a partir de su id", response = CategoriasDTO.class, tags = "Categorias")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<CategoriasDTO> categoriasFound = categoriasService.findById(id);
        return new ResponseEntity<>(categoriasFound, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las categorias", response = CategoriasDTO.class, responseContainer = "List", tags = "Categorias")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<CategoriasDTO>> result = categoriasService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findByNombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista de categorias a partir de su nombre", response = CategoriasDTO.class, responseContainer = "List", tags = "Categorias")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre") String nombre) {
        Optional<List<CategoriasDTO>>categoriasFound= categoriasService.findByNombre(nombre);
        return new ResponseEntity<>(categoriasFound, HttpStatus.OK);
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de categorias a partir de su estado", response = CategoriasDTO.class, responseContainer = "List", tags = "Categorias")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estado) {
        Optional<List<CategoriasDTO>>categoriasFound= categoriasService.findByEstado(estado);
        return new ResponseEntity<>(categoriasFound, HttpStatus.OK);
    }

    @GetMapping("/findByFechaCreacion/{fechaCreacion}")
    @ApiOperation(value = "Obtiene una lista de categorias a partir de su Fecha de Creacion", response = CategoriasDTO.class, responseContainer = "List", tags = "Categorias")
    public ResponseEntity<?> findByFechaCreacion(@PathVariable(value = "fechaCreacion") Date fechaCreacion) {
        Optional<List<CategoriasDTO>>categoriasFound= categoriasService.findByFechaCreacion(fechaCreacion);
        return new ResponseEntity<>(categoriasFound, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody CategoriasDTO categoriasDTO) {
        try {
            Optional<CategoriasDTO> categoriasCreated =categoriasService.create(categoriasDTO);
            return new ResponseEntity<>(categoriasCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id de las categorias", response = CategoriasDTO.class, tags = "Categorias")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody CategoriasDTO categoriasModified) {
        Optional<CategoriasDTO> categoriasUpdated = categoriasService.update(categoriasModified, id);
        return new ResponseEntity<>(categoriasUpdated, HttpStatus.OK);
    }
}
