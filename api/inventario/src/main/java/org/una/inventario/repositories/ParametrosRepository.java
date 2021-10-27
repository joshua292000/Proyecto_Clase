package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.entities.Parametros;

import java.util.Date;
import java.util.List;

public interface ParametrosRepository extends JpaRepository<Parametros, Long> {
    public List<Parametros> findByNombre(String nombre);
    public List<Parametros>findByEstado(String estado);
    public List<Parametros>findByFechaCreacionBetween(Date fechaCreacion, Date fechaModificacion);
}
