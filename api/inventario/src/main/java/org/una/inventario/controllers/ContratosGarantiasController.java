package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.AlertasDTO;
import org.una.inventario.dto.ContratosGarantiasDTO;
import org.una.inventario.services.ContratosGarantiasService;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contratosGarantias")
@Api(tags = {"ContratosGarantias"})
public class ContratosGarantiasController {

    @Autowired
    ContratosGarantiasService contratosGarantiasService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los contratos con garantias", response = ContratosGarantiasDTO.class, responseContainer = "List", tags = "ContratosGarantias")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ContratosGarantiasDTO>> result = contratosGarantiasService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una contrato a partir de su id", response = ContratosGarantiasDTO.class, tags = "ContratosGarantias")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ContratosGarantiasDTO> contratogarantiaFound = contratosGarantiasService.findById(id);
        return new ResponseEntity<>(contratogarantiaFound, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ContratosGarantiasDTO contratosGarantiasDTO) {
        try {
            Optional<ContratosGarantiasDTO> contratogarantiaCreated = contratosGarantiasService.create(contratosGarantiasDTO);
            return new ResponseEntity<>(contratogarantiaCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id", response = ContratosGarantiasDTO.class, tags = "ContratosGarantias")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ContratosGarantiasDTO contratoModified) {
        Optional<ContratosGarantiasDTO> usuarioUpdated = contratosGarantiasService.update(contratoModified, id);
        return new ResponseEntity<>(usuarioUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina un contrato por medio del id", response = ContratosGarantiasDTO.class, tags = "ContratosGarantias")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        contratosGarantiasService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina todos los contratos", response = AlertasDTO.class, tags = "ContratosGarantias")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        contratosGarantiasService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
