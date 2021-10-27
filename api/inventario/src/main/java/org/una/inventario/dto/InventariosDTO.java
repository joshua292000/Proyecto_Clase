package org.una.inventario.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InventariosDTO {
    private Long id;
    private Long responsable;
    private String descripcion;
    private String estado;
    private Date fechaCreacion;
}
