package org.una.inventario.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContratosGarantiasDTO {
    private Long id;
    private Double montoAsegurado;
    private Double costo;
    private String estado;
    private Date fechaVecimiento;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
