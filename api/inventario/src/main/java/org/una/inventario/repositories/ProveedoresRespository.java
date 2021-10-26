package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.ContratosGarantias;
import org.una.inventario.entities.Proveedores;

import java.util.Optional;

@Repository
public interface ProveedoresRespository extends JpaRepository<Proveedores, Long> {
    public Optional<Proveedores> findByNombre(String nombre);
}
