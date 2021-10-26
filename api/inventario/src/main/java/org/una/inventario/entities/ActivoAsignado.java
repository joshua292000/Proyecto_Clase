package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "activosAsignados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ActivoAsignado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuarios_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="activo_id")
    private Activo activo;

    @Column(name = "justificacion", length = 100)
    private String justificacion;

    @Column(name = "estado", length = 10)
    private String estado;

    private static final long serialVersionUID = 1L;

    @Column(name = "fecha_creacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    @PrePersist
    public void prePersist() {
        fechaCreacion = new Date();
    }
}
