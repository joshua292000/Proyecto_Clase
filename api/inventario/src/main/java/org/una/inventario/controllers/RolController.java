package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.RolesDTO;
import org.una.inventario.services.RolesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@Api(tags = {"Roles"})
public class RolController {
    @Autowired
    private RolesService rolService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Roles", response = RolesDTO.class, responseContainer = "List", tags = "Roles")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        Optional<List<RolesDTO>> result = rolService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un rol a partir de su id", response = RolesDTO.class, tags = "Roles")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<RolesDTO> rolFound = rolService.findById(id);
        return new ResponseEntity<>(rolFound, HttpStatus.OK);
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de los nombres de los roles", response = RolesDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        Optional<List<RolesDTO>> result = rolService.findByNombreAproximateIgnoreCase(term);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody RolesDTO rolDTO) {
        try {
            Optional<RolesDTO> rolCreated = rolService.create(rolDTO);
            return new ResponseEntity<>(rolCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id del rol", response = RolesDTO.class, tags = "Seguridad")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RolesDTO rolModified) {
        Optional<RolesDTO> rolUpdated = rolService.update(rolModified, id);
        return new ResponseEntity<>(rolUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            rolService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        //TODO: Implementar este método
        throw new Exception("Not implemented Function");

    }
}
