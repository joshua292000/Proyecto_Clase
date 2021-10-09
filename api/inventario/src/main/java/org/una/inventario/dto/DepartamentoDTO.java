package org.una.inventario.dto;

import lombok.*;
import org.una.inventario.entities.Departamento;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class DepartamentoDTO {
    private Long id;
    private String nombre;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;

}
