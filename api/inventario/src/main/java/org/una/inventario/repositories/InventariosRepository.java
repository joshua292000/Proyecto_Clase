package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Inventarios;

import java.util.List;

public interface InventariosRepository extends JpaRepository<Inventarios, Long> {
    public List<Inventarios> findByDescripcion(String descripcion);
    public List<Inventarios>findByEstado(String estado);
}
