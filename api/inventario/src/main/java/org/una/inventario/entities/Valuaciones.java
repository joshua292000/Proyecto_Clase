package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "valuaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Valuaciones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="inventarios_id")
    private Inventarios inventarios;

    @ManyToOne
    @JoinColumn(name="activo_id")
    private Activo activo;

    @Column(name = "precio_valuacion")
    private Double precioValuacion;

    @Column(name = "fecha_creacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaCreacion = new Date();
    }
}
