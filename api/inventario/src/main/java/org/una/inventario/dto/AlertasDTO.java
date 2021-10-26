package org.una.inventario.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlertasDTO {
    private Long id;
    private String tipo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long responsable;
}
