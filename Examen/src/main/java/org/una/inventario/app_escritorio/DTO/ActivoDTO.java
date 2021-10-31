package org.una.inventario.app_escritorio.DTO;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ActivoDTO {
    private Long id;
    private CategoriasDTO categoria;
    private ProveedoresDTO proveedor;
    private MarcaDTO marca;
    private Long continente;
    private String nombre;
    private String estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
