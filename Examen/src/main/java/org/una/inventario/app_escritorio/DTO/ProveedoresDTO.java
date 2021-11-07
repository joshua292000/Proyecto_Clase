package org.una.inventario.app_escritorio.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProveedoresDTO {
    private Long id;
    private String nombre;
    private String notas;
    private String telefono;
    private String correoElectronico;
    private String estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
