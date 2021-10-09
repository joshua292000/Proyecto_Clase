package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.services.IUsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"})
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {

        Optional<List<UsuarioDTO>> result = usuarioService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una usuario a partir de su id", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<UsuarioDTO> usuarioFound = usuarioService.findById(id);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);

    }

    @PutMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String password) {
        Optional<UsuarioDTO> usuarioFound = usuarioService.login(cedula, password);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
    }

    @GetMapping("/cedula/{term}")
    @ApiOperation(value = "Obtiene una lista de las cedulas", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
            Optional<List<UsuarioDTO>> result = usuarioService.findByCedulaAproximate(term);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/id/{term}")
    @ApiOperation(value = "Obtiene una lista de los id de un departamento", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "term") long id) {
        Optional<List<UsuarioDTO>> result = usuarioService.findByDepartamentoId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{iddepartamento}")
    @ApiOperation(value = "Obtiene una jefe de un departamento mediante su id", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findJefeByDepartamento(@PathVariable(value = "id") Long id) {
        Optional<UsuarioDTO> usuarioFound = usuarioService.findJefeByDepartamento(id);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de los nombres", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        Optional<List<UsuarioDTO>> result = usuarioService.findByNombreCompletoAproximateIgnoreCase(term);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Optional<UsuarioDTO> usuarioCreated = usuarioService.create(usuarioDTO);
            return new ResponseEntity<>(usuarioCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza por medio del id", response = UsuarioDTO.class, tags = "Seguridad")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioDTO usuarioModified) {
        Optional<UsuarioDTO> usuarioUpdated = usuarioService.update(usuarioModified, id);
        return new ResponseEntity<>(usuarioUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            usuarioService.delete(id);
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
