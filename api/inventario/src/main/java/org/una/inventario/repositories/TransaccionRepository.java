package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Transaccion;

import java.util.Date;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{

    //public Transaccion findById(Long id);

    public List<Transaccion>findByUsuarioIdAndFechaCreacionBetween(Long usuarioId, Date fechaCreacion, Date fechaFinalizacion);

    public List<Transaccion> findByIdAndFechaCreacionBetween(Long id, Date fechaCreacion, Date fechaFinalizacion);

    public List<Transaccion> findByObjetoAndFechaCreacionBetween(String objetoId, Date fechaCreacion, Date fechaFinalizacion);

    public List<Transaccion> findByFechaCreacionBetween(Date fechaCreacion, Date fechaFinalizacion);

   // public Transaccion create(Transaccion transacciones);


}
