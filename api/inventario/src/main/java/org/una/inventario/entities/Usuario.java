package org.una.inventario.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", length = 100)
    private String nombreCompleto;

    @Column(length = 100, name = "password_encriptado")
    private String passwordEncriptado;

    @Column(length = 25, unique = true)
    private String cedula;

    @Column
    private boolean estado;

    @ManyToOne
    @JoinColumn(name="departamentos_id")
    private Long departamento;


    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @Column(name = "es_jefe")
    private boolean esJefe;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=true;
        esJefe=false;
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }

}

