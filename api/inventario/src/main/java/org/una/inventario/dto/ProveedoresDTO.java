package org.una.inventario.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProveedoresDTO {
    private Long id;
    private String nombre;
    private String notas;
    private String telefono;
    private String correoElectronico;
    private String estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

}
