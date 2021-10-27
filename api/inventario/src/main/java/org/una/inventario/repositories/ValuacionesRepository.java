package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.entities.Categorias;
import org.una.inventario.entities.Valuaciones;

import java.util.Date;
import java.util.List;

public interface ValuacionesRepository extends JpaRepository<Valuaciones, Long> {
    public List<Valuaciones> findByPrecioValuacion(Double precioValuacion);
    public List<Valuaciones>findByFechaCreacion(Date fechaCreacion);
}
