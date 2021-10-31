package org.una.inventario.app_escritorio.DTO;

import lombok.*;
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
