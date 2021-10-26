package org.una.inventario.dto;

import lombok.*;
import org.una.inventario.entities.Marca;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ActivoDTO {
    private Long id;
    //private Categoria categoria;
    //private Proveedor proveedor;
    private Marca marca;
    private Long continente;
    private String nombre;
    private String estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
