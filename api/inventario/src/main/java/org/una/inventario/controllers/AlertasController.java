package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.AlertasDTO;
import org.una.inventario.services.AlertasService;
import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/alertas")
@Api(tags = {"Alertas"})
public class AlertasController {
    @Autowired
    AlertasService alertasService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las alertas", response = AlertasDTO.class, responseContainer = "List", tags = "Alertas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<AlertasDTO>> result = alertasService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una alerta a partir de su id", response = AlertasDTO.class, tags = "Alertas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<AlertasDTO> alertaFound = alertasService.findById(id);
        return new ResponseEntity<>(alertaFound, HttpStatus.OK);

    }

    @GetMapping("/{tipo}")
    @ApiOperation(value = "Obtiene una alerta a partir de su tipo", response = AlertasDTO.class, tags = "Alertas")
    public ResponseEntity<?> findByTipo(@PathVariable(value = "tipo") String tipo) {
        Optional<AlertasDTO> alertaFound = alertasService.findByTipo(tipo);
        return new ResponseEntity<>(alertaFound, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody AlertasDTO alertasDTO) {
        try {
            Optional<AlertasDTO> alertaCreated = alertasService.create(alertasDTO);
            return new ResponseEntity<>(alertaCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id", response = AlertasDTO.class, tags = "Alertas")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AlertasDTO alertaModified) {
        Optional<AlertasDTO> usuarioUpdated = alertasService.update(alertaModified, id);
        return new ResponseEntity<>(usuarioUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina una alerta por medio del id", response = AlertasDTO.class, tags = "Alertas")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        alertasService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina todas las alertas", response = AlertasDTO.class, tags = "Alertas")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        alertasService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
