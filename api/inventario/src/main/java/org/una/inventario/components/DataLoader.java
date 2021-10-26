package org.una.inventario.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.RolesDTO;
import org.una.inventario.entities.Departamento;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.services.DepartamentoService;
import org.una.inventario.services.RolesService;
import org.una.inventario.services.IUsuarioService;

import java.util.Optional;

@Component
public class DataLoader implements ApplicationRunner {
    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private RolesService rolService;

    @Autowired
    private DepartamentoService departamentoService;

    @Override
    public void run(ApplicationArguments args) {
        if (usuarioService.findByCedulaAproximate(cedula).get().size()==0) {
            Optional<DepartamentoDTO> contabilidadDepartamento = departamentoService.create(DepartamentoDTO.builder().nombre("Contabilidad").build());
            Optional<DepartamentoDTO> cajasDepartamento = departamentoService.create(DepartamentoDTO.builder().nombre("Cajas").build());
            Optional<DepartamentoDTO> informaticaDepartamento = departamentoService.create(DepartamentoDTO.builder().nombre("Informatica").build());

            RolesDTO colaboradorRol = rolService.create(RolesDTO.builder().nombre("Colaborador").build());
            RolesDTO auditorRol = rolService.create(RolesDTO.builder().nombre("Auditor").build());
            RolesDTO contadorRol = rolService.create(RolesDTO.builder().nombre("Contador").build());
            RolesDTO usuarioRol = rolService.create(RolesDTO.builder().nombre("Usuario").build());
            RolesDTO administradorRol = rolService.create(RolesDTO.builder().nombre("Administrador").build());

            UsuarioDTO cajeroUsuario = UsuarioDTO.builder()

                    .cedula("0123456789")
                    .nombreCompleto("Joselyne Moras")
                    .passwordEncriptado("Una2021")
                    .departamento(cajasDepartamento.orElseThrow())
                    .rol(usuarioRol).build();
            usuarioService.create(cajeroUsuario);

            UsuarioDTO contadorUsuario = UsuarioDTO.builder()
                    .cedula("9876543210")
                    .nombreCompleto("El Kevin Moras")
                    .esJefe(true)
                    .passwordEncriptado("Una2021")
                    .departamento(contabilidadDepartamento.orElseThrow())
                    .rol(contadorRol).build();
            usuarioService.create(contadorUsuario);

            UsuarioDTO administradorUsuario = UsuarioDTO.builder()
                    .cedula(cedula)
                    .nombreCompleto("Usuario Administrador")
                    .passwordEncriptado(password)
                    .departamento(informaticaDepartamento.orElseThrow())
                    .rol(administradorRol).build();
            usuarioService.create(administradorUsuario);

            System.out.println("Se agrega el usuario inicial a la aplicación");
        }else {
            System.out.println("Se encontro el usuario administrador, continuando...");
        }
    }

}