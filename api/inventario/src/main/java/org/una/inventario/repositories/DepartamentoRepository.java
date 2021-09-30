package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Departamento;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    public List<Departamento>findByEstado(boolean estado);

    //public List<Departamento>findByNombre(String nombre);

    //@Query("select u from Departamento u where UPPER(u.nombre) like CONCAT('%',UPPER(:nombre),'%')")
   // public Departamento findByNombreWithLikeSQL(@Param("nombre")String nombre);
}
