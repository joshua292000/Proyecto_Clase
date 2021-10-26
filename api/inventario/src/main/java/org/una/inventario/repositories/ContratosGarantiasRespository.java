package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Alertas;
import org.una.inventario.entities.ContratosGarantias;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContratosGarantiasRespository extends JpaRepository<ContratosGarantias, Long>{

}
