package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.services.DataInitializerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datainitializer")
@Api(tags = {"DataInitializer"})
public class DataInitializerController {
    //holas
    @Autowired
    private DataInitializerService dataInitializerService;

    @PutMapping()
    @ResponseBody
    public ResponseEntity<?> initDevelopData() throws Exception {
        //TODO: Implementar este método
        throw new Exception("Not implemented Function");
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAllData() throws Exception {
        //TODO: Implementar este método
        throw new Exception("Not implemented Function");

    }
}
