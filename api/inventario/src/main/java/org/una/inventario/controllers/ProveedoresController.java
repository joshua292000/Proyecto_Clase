package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.AlertasDTO;
import org.una.inventario.dto.ProveedoresDTO;
import org.una.inventario.services.AlertasService;
import org.una.inventario.services.ProveedoresService;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
@Api(tags = {"Proveedores"})
public class ProveedoresController {
    @Autowired
    ProveedoresService proveedoresService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los proveedores", response = ProveedoresDTO.class, responseContainer = "List", tags = "Proveedores")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<ProveedoresDTO>> result = proveedoresService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un proveedor a partir de su id", response = ProveedoresDTO.class, tags = "Proveedores")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ProveedoresDTO> proveedorFound = proveedoresService.findById(id);
        return new ResponseEntity<>(proveedorFound, HttpStatus.OK);

    }

    @GetMapping("/{tipo}")
    @ApiOperation(value = "Obtiene un proveedor por medio de nombre", response = ProveedoresDTO.class, tags = "Proveedores")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "tipo") String nombre) {
        Optional<ProveedoresDTO> proveedorFound = proveedoresService.findByNombre(nombre);
        return new ResponseEntity<>(proveedorFound, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ProveedoresDTO proveedoresDTO) {
        try {
            Optional<ProveedoresDTO> proveedorCreated = proveedoresService.create(proveedoresDTO);
            return new ResponseEntity<>(proveedorCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id", response = ProveedoresDTO.class, tags = "Proveedores")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ProveedoresDTO proveedorModified) {
        Optional<ProveedoresDTO> proveedorUpdated = proveedoresService.update(proveedorModified, id);
        return new ResponseEntity<>(proveedorUpdated, HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina un proveedor por medio del id", response = ProveedoresDTO.class, tags = "Proveedores")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        proveedoresService.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina todas los proveedores", response = ProveedoresDTO.class, tags = "Proveedores")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        proveedoresService.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
