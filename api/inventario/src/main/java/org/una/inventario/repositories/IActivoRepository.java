package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Activo;

import java.util.Date;
import java.util.List;

@Repository
public interface IActivoRepository extends JpaRepository<Activo, Long> {
    public List<Activo> findByNombre(String nombre);
    public List<Activo>findByEstadoActivo(String estado);
    public List<Activo>findByFechaCreacionBetween(Date fechaCreacion, Date fechaModificacion);
}
