package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.entities.Categorias;

import java.util.Date;
import java.util.List;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    public List<Categorias> findByNombre(String nombre);
    public List<Categorias> findByEstado(String estado);
    public List<Categorias>findByFechaCreacion(Date fechaCreacion);
}
