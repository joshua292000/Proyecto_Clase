package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.dto.RolesDTO;
import org.una.inventario.services.MarcaService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marca")
@Api(tags = {"Marca"})
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una marca a partir de su id", response = MarcaDTO.class, tags = "Marca")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<MarcaDTO> marcaFound = marcaService.findById(id);
        return new ResponseEntity<>(marcaFound, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las marcas", response = MarcaDTO.class, responseContainer = "List", tags = "Marcas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<MarcaDTO>> result = marcaService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findByNombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista de marcas a partir de su nombre", response = MarcaDTO.class, responseContainer = "List", tags = "Marcas")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre") String nombreMarca) {
        Optional<List<MarcaDTO>>marcaFound= marcaService.findByNombre(nombreMarca);
        return new ResponseEntity<>(marcaFound, HttpStatus.OK);
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de marcas a partir de su estado", response = MarcaDTO.class, responseContainer = "List", tags = "Marcas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estadoMarca) {
        Optional<List<MarcaDTO>>marcaFound= marcaService.findByEstado(estadoMarca);
        return new ResponseEntity<>(marcaFound, HttpStatus.OK);
    }

    @GetMapping("/findByFechaCreacion/{fechaCreacion}")
    @ApiOperation(value = "Obtiene una lista de marcas a partir de su FechaCreacion", response = MarcaDTO.class, responseContainer = "List", tags = "Marcas")
    public ResponseEntity<?> findByFechaCreacion(@PathVariable(value = "fechaCreacion") Date fechaCreacionMarca) {
        Optional<List<MarcaDTO>>marcaFound= marcaService.findByFechaCreacion(fechaCreacionMarca);
        return new ResponseEntity<>(marcaFound, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody MarcaDTO marcaDTO) {
        try {
            Optional<MarcaDTO> marcaCreated =marcaService.create(marcaDTO);
            return new ResponseEntity<>(marcaCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id de la marca", response = MarcaDTO.class, tags = "Marca")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody MarcaDTO marcaModified) {
        Optional<MarcaDTO> marcaUpdated = marcaService.update(marcaModified, id);
        return new ResponseEntity<>(marcaUpdated, HttpStatus.OK);
    }
}
