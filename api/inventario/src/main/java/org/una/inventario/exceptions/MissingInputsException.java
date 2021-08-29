package org.una.inventario.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Component
public class MissingInputsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final HttpStatus errorCode = HttpStatus.NOT_FOUND;

    private final String errorMessage = "No se encontro información en su solicitud, revise su petición";

}
