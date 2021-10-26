package org.una.inventario.entities;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contratosGarantias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ContratosGarantias implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="activos_id")
    private Activo activo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratosGarantias")
    @Builder.Default
    private List<Alertas> alertas = new ArrayList<>();

    @Column(name = "monto_asegurado")
    private Double montoAsegurado;

    @Column(name = "costo")
    private Double costo;

    @Column(length = 100, name = "estado")
    private String estado;

    @Column(name = "fecha_vencimiento", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaVecimiento;

    @Column(name = "fecha_creacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaModificacion;




private static final long serialVersionUID = 1L;
}
