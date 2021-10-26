package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Alertas;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertasRespository extends JpaRepository<Alertas, Long> {
    public Optional<Alertas> findByTipo(String tipo);
}
