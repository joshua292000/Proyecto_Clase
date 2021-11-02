package org.una.inventario.app_escritorio.DTO;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
@Builder
public class ActivosDTO implements Comparable<ActivosDTO>{
    private final String Marca;
    private final String Proveedor;
    private final String Numero;
    private final String Nota;
    private final String Telefono;
    private final String CorreoElectronico;
    private final String FechadeCreaciondelProveedor;
    private  final String Continente;
    private final String Nombre;
    private final String Estado;
    private  final String Fechadecreacion;



    @Override
    public int compareTo(ActivosDTO o) {
        return this.getMarca().compareTo(o.getMarca());
    }
}
