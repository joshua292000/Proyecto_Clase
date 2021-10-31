package org.una.inventario.app_escritorio.DTO;

import javafx.scene.control.TableColumn;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
@Builder
public class ReporteDTO implements Comparable<ReporteDTO>{

    private final String id;

    private final String nombre;

    private final String fecha;

    public final String estado;

    private final String marca;

    @Override
    public int compareTo(ReporteDTO o) {
        return this.getMarca().compareTo(o.getMarca());
    }
}
