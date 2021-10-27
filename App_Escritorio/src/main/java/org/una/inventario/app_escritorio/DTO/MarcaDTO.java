package org.una.inventario.app_escritorio.DTO;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MarcaDTO {
    private Long id;
    private String nombre;
    private String estado;
    private Date fechaCreacion;
}
