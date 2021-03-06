package org.una.inventario.dto;

import lombok.*;
import org.una.inventario.entities.Marca;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ActivoDTO {
    private Long id;
    private CategoriasDTO categoria;
    private ProveedoresDTO proveedores;
    private MarcaDTO marca;
    private Long continente;
    private Long numero;
    private String nombre;
    private String estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}
