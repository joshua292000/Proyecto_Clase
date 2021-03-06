package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "activos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Activo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="categorias_id")
    private Categorias categorias;

    @ManyToOne
    @JoinColumn(name="proveedor_id")
    private Proveedores proveedores;

    @ManyToOne
    @JoinColumn(name="marca_id")
    private Marca marca;

    @Column(name = "continente")
    private Long continente;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "estado", length = 10)
    private String estado;

    @Column(name = "fecha_creacion", updatable = false)
   //@Temporal(TemporalType.DATE)
    @Setter(AccessLevel.PUBLIC)
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion", updatable = false)
    //@Temporal(TemporalType.TIME)
    @Setter(AccessLevel.PUBLIC)
    private LocalDate fechaModificacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    @Builder.Default
    private List<ContratosGarantias> contratosGarantias = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    @Builder.Default
    private List<Valuaciones> valuaciones = new ArrayList<>();

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
   @Builder.Default
    private List<ActivoAsignado> activoAsignados = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        //fechaCreacion = new Date();
        //fechaModificacion=new Date();
    }

}
