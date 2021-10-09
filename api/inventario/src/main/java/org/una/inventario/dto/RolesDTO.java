package org.una.inventario.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class RolesDTO {
    private Long id;
    private String nombre;
    private Date fechaCreacion;

}
