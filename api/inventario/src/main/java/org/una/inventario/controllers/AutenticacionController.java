package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.AuthenticationRequest;
import org.una.inventario.dto.AuthenticationResponse;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.exceptions.InvalidCredentialsException;
import org.una.inventario.exceptions.MissingInputsException;
import org.una.inventario.services.AutenticacionService;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.validation.Valid;

@RestController
@RequestMapping("/autenticacion")
@Api(tags = {"Autenticaciones"})
public class AutenticacionController {
    @Autowired
    private AutenticacionService autenticacionService;

    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Autenticaciones")
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MissingInputsException();
        }
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        AuthenticationResponse token = autenticacionService.login(authenticationRequest);
        if (token.getJwt() != null) {
            return new ResponseEntity(autenticacionService.login(authenticationRequest), HttpStatus.OK);
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
