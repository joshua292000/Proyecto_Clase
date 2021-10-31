package org.una.inventario.app_escritorio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse {
    private String jwt;
    private UsuarioDTO usuarioDTO;
    private RolesDTO rolDTO;
}
