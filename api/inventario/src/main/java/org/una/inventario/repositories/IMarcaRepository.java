package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Marca;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Long> {
    public Optional<Marca> findByNombre(String nombre);
    public List<Marca>findByEstado(String estado);
    public List<Marca>findByFechaCreacion(Date fechaCreacion);
}
