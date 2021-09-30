package org.una.inventario.services;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Roles;
import org.una.inventario.entities.Usuario;
import org.una.inventario.repositories.*;


@Service
public class DataInitializerServiceImplementation implements DataInitializerService{
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private RolesRepository rolRepository;


    @Override
    public void initDevelopData(){
        Departamento patentesDepartamento =  departamentoRepository.save(Departamento.builder().nombre("Licencias y patentes").build());
        Departamento cajasDepartamento =  departamentoRepository.save(Departamento.builder().nombre("Cajas").build());

        Roles colaboradorRol =  rolRepository.save(Roles.builder().nombre("Colaborador").build());
        Roles usuarioRol =  rolRepository.save(Roles.builder().nombre("Usuario").build());
        Roles auditorRol =  rolRepository.save(Roles.builder().nombre("Auditor").build());
        Roles contadorRol =  rolRepository.save(Roles.builder().nombre("Contador").build());
        Roles administradorRol =  rolRepository.save(Roles.builder().nombre("Administrador").build());


        Usuario cajeroUsuario = new Usuario();
        cajeroUsuario.setCedula("0123456789");
        cajeroUsuario.setNombreCompleto("Usuario Prueba cajero");
        cajeroUsuario.setPasswordEncriptado("Una2021");
        cajeroUsuario.setDepartamento(cajasDepartamento);
        cajeroUsuario.setRol(usuarioRol);
        usuarioRepository.save(cajeroUsuario);

        Usuario coordinadorDepartamentoUsuario = new Usuario();
        coordinadorDepartamentoUsuario.setCedula("9876543210");
        coordinadorDepartamentoUsuario.setNombreCompleto("Usuario Prueba Coordinador");
        coordinadorDepartamentoUsuario.setEsJefe(true);
        coordinadorDepartamentoUsuario.setPasswordEncriptado("Una2021");
        coordinadorDepartamentoUsuario.setDepartamento(patentesDepartamento);
        coordinadorDepartamentoUsuario.setRol(administradorRol);
        usuarioRepository.save(coordinadorDepartamentoUsuario);

    }

    @Override
    public void deleteAllData() {
        rolRepository.deleteAll();
        usuarioRepository.deleteAll();
        departamentoRepository.deleteAll();
    }
}
