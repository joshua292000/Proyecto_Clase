package org.una.inventario.dto;

import lombok.*;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Usuario;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ActivoAsignadoDTO {
    private Long id;
    private Usuario usuario;
    private Activo activo;
    private String justificacion;
    private String estado;
    private Date fechaCreacion;
}
