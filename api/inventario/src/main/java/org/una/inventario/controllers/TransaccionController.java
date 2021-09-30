package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.TransaccionDTO;
import org.una.inventario.services.TransaccionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionesService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una transaccion a partir de su id", response = TransaccionDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<TransaccionDTO> transaccionesFound = transaccionesService.findById(id);
        return new ResponseEntity<>(transaccionesFound, HttpStatus.OK);
    }

    @GetMapping("/usuarioId/{term}")
    @ApiOperation(value = "Obtiene una lista de los id de usuario con su fecha de creaacion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?>findByUsuarioIdAndFechaCreacionBetween (@PathVariable(value = "term") Long term, Date fechaCreacion, Date fechaFinalizacion) {
        Optional<List<TransaccionDTO>> result = transaccionesService.findByUsuarioIdAndFechaCreacionBetween(term, fechaCreacion, fechaFinalizacion);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/rolId/{term}")
    @ApiOperation(value = "Obtiene una lista de los id de transacciones con su fecha de creaacion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?>findByIdAndFechaCreacionBetween (@PathVariable(value = "term") Long term, Date fechaCreacion, Date fechaFinalizacion) {
        Optional<List<TransaccionDTO>> result = transaccionesService.findByIdAndFechaCreacionBetween(term, fechaCreacion, fechaFinalizacion);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/objetoId/{term}")
    @ApiOperation(value = "Obtiene una lista de los id de objetos con su fecha de creaacion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?>findByObjetoAndFechaCreacionBetween (@PathVariable(value = "term") String term, Date fechaCreacion, Date fechaFinalizacion) {
        Optional<List<TransaccionDTO>> result = transaccionesService.findByObjetoAndFechaCreacionBetween(term, fechaCreacion, fechaFinalizacion);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/transaccion/{term}")
    @ApiOperation(value = "Obtiene una lista de las transacciones con su fecha de creaacion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?>findByFechaCreacionBetween (@PathVariable(value = "term")  Date fechaCreacion, Date fechaFinalizacion) {
        Optional<List<TransaccionDTO>> result = transaccionesService.findByFechaCreacionBetween(fechaCreacion, fechaFinalizacion);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TransaccionDTO transaccionDTO) {
        try {
            TransaccionDTO transaccionesCreated = transaccionesService.create(transaccionDTO);
            return new ResponseEntity<>(transaccionesCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
