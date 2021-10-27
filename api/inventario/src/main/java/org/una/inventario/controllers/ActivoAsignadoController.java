package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.services.ActivoAsignadoService;
import org.una.inventario.services.ActivoService;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activoAsignado")
@Api(tags = {"Activo Asignado"})
public class ActivoAsignadoController {
    @Autowired
    private ActivoAsignadoService activoAsignadoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un activo asignado a partir de su id", response = ActivoAsignadoDTO.class, tags = "Activo Asignado")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ActivoAsignadoDTO> activoAsignadoFound = activoAsignadoService.findById(id);
        return new ResponseEntity<>(activoAsignadoFound, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Activos Asignado", response = ActivoAsignadoDTO.class, responseContainer = "List", tags = "Activo Asignado")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ActivoAsignadoDTO>> result = activoAsignadoService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findByEstadoActivo/{estado}")
    @ApiOperation(value = "Obtiene una lista de activos asignados a partir de su estado", response = ActivoAsignadoDTO.class, responseContainer = "List", tags = "Activo Asignado")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estadoActivoAsignado) {
        Optional<List<ActivoAsignadoDTO>>activoAsignadoFound= activoAsignadoService.findByEstado(estadoActivoAsignado);
        return new ResponseEntity<>(activoAsignadoFound, HttpStatus.OK);
    }

    @GetMapping("/findByFechaCreacion/{fechaCreacion}")
    @ApiOperation(value = "Obtiene una lista de marcas a partir de su FechaCreacion", response = ActivoAsignadoDTO.class, responseContainer = "List", tags = "Activo Asignado")
    public ResponseEntity<?> findByFechaCreacion(@PathVariable(value = "fechaCreacion") Date fechaCreacionActivoAsignado) {
        Optional<List<ActivoAsignadoDTO>>activoAsignadoFound= activoAsignadoService.findByFechaCreacion(fechaCreacionActivoAsignado);
        return new ResponseEntity<>(activoAsignadoFound, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ActivoAsignadoDTO activoAsignadoDTO) {
        try {
            Optional<ActivoAsignadoDTO> activoAsignadoCreated =activoAsignadoService.create(activoAsignadoDTO);
            return new ResponseEntity<>(activoAsignadoCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id de los activos asignados", response = ActivoAsignadoDTO.class, tags = "Activo Asignado")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ActivoAsignadoDTO activoAsignadoModified) {
        Optional<ActivoAsignadoDTO> activoAsignadoUpdated = activoAsignadoService.update(activoAsignadoModified, id);
        return new ResponseEntity<>(activoAsignadoUpdated, HttpStatus.OK);
    }
}
