package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Activo;

import java.util.Date;
import java.util.List;

@Repository
public interface IActivoRepository extends JpaRepository<Activo, Long> {
    public List<Activo> findByNombre(String nombre);
    public List<Activo>findByEstado(String estado);
    public List<Activo>findByFechaCreacionBetween(Date fechaCreacion, Date fechaModificacion);

    @Query(value = "SELECT u FROM Activo u LEFT JOIN u.marca e WHERE " +
            "  e.id=:idMarcaProve AND u.fechaCreacion >= :startDate AND u.fechaCreacion <= :endDate ORDER BY u.fechaCreacion DESC")
    public List<Activo> findByActivosxMarcaDescBetweenFechas(@Param("idMarcaProve")Long idMarcaProve,
                                                                       @Param("startDate")Date startDate,
                                                                       @Param("endDate")Date endDate);
    @Query(value = "SELECT u FROM Activo u LEFT JOIN u.marca e WHERE " +
            "  e.id=:idMarcaProve AND u.fechaCreacion >= :startDate AND u.fechaCreacion <= :endDate  ORDER BY u.fechaCreacion ASC")
    public List<Activo> findByActivosxMarcaAscBetweenFechas(@Param("idMarcaProve")Long idMarcaProve,
                                                                       @Param("startDate")Date startDate,
                                                                       @Param("endDate")Date endDate);
    @Query(value = "SELECT u FROM Activo u LEFT JOIN u.proveedores e WHERE " +
            "  e.id=:idMarcaProve AND u.fechaCreacion >= :startDate AND u.fechaCreacion <= :endDate  ORDER BY u.fechaCreacion DESC")
    public List<Activo> findByActivosxProveDescBetweenFechas(@Param("idMarcaProve")Long idMarcaProve,
                                                                       @Param("startDate")Date startDate,
                                                                       @Param("endDate")Date endDate);
    @Query(value = "SELECT u FROM Activo u LEFT JOIN u.proveedores e WHERE " +
            "  e.id=:idMarcaProve AND u.fechaCreacion >= :startDate AND u.fechaCreacion <= :endDate  ORDER BY u.fechaCreacion ASC")
    public List<Activo> findByActivosxProveAscBetweenFechas(@Param("idMarcaProve")Long idMarcaProve,
                                                             @Param("startDate")Date startDate,
                                                             @Param("endDate")Date endDate);

}
