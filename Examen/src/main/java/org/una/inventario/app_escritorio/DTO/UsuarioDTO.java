package org.una.inventario.app_escritorio.DTO;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombreCompleto;
    private String passwordEncriptado;
    private String cedula;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private Long departamentoId;
    private boolean esJefe;
    private DepartamentoDTO departamento;
    private RolesDTO rol;
}
