package org.una.inventario.dto;

import lombok.*;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Inventarios;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ValuacionesDTO {
    private Long id;
    private Inventarios inventarioos_id;
    private Activo activo;
    private Double responsable;
    private Date fechaCreacion;
}
