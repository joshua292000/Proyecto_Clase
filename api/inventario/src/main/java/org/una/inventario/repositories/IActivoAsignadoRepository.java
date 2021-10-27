package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.ActivoAsignado;

import java.util.Date;
import java.util.List;

@Repository
public interface IActivoAsignadoRepository extends JpaRepository<ActivoAsignado, Long> {
    public List<ActivoAsignado> findByEstado(String estado);
    public List<ActivoAsignado>findByFechaCreacion(Date fechaCreacion);
}
