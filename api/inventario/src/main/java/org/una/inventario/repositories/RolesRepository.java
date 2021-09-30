package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Roles;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{
    public List<Roles> findByNombreContainingIgnoreCase(String nombre);

}
