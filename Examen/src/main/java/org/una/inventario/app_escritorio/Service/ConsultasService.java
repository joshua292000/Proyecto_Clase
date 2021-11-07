package org.una.inventario.app_escritorio.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.una.inventario.app_escritorio.DTO.ActivoDTO;
import org.una.inventario.app_escritorio.DTO.AuthenticationResponse;
import org.una.inventario.app_escritorio.DTO.MarcaDTO;
import org.una.inventario.app_escritorio.DTO.ProveedoresDTO;
import org.una.inventario.app_escritorio.Util.AppContext;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.*;
import java.util.Date;
import java.util.List;

public class ConsultasService {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static ObjectMapper mapper = new ObjectMapper();

    public static ProveedoresDTO ObtenerProvedoresxNombre(String nombre) throws IOException, InterruptedException {

        ProveedoresDTO provedor = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/proveedores/findByNombre/"+nombre))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.body().equals("No se encontro información en su solicitud, revise su petición")){
            provedor=null;
        }else{
            provedor = mapper.readValue(response.body(), new TypeReference<ProveedoresDTO>() {});
        }
        return provedor;

    }
    public static ProveedoresDTO ProveeCBX(String Nombre, String Notas,String Correo,String Estado,String Telefono,LocalDateTime fecha,LocalDateTime fechamodi) throws IOException, InterruptedException {

        ProveedoresDTO proveedores = null;
        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");
        String json = new StringBuilder()
                .append("{")
                .append("\"correoElectronico\":\"" )
                .append(Correo)
                .append("\",")
                .append("\"estado\":\"" )
                .append(Estado)
                .append("\",")
                .append("\"fechaCreacion\":\"" )
                .append(fecha)
                .append("\",")
                .append("\"fechaModificacion\":\"" )
                .append(fechamodi)
                .append("\",")
                .append("\"nombre\":\"" )
                .append(Nombre)
                .append("\",")
                .append("\"notas\":\"" )
                .append(Notas)
                .append("\",")
                .append("\"telefono\":\"" )
                .append(Telefono)
                .append("\"")
                .append("}").toString();
        System.out.println("jsonprove "+json+"\n");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8089/proveedores/"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        proveedores = mapper.readValue(response.body(), new TypeReference<ProveedoresDTO>() {});
        return proveedores;

    }

    public static MarcaDTO ObtenermarcaxNombre(String nombre) throws IOException, InterruptedException {

        MarcaDTO marca = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/marca/findByNombre/"+nombre))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.body().equals("No se encontro información en su solicitud, revise su petición")){
            marca=null;
        }else{
            marca = mapper.readValue(response.body(), new TypeReference<MarcaDTO>() {});
        }
        return marca;

    }
    public static MarcaDTO MarcaCBX(String Estado, LocalDateTime fecha, String Nombre) throws IOException, InterruptedException {

        MarcaDTO marcas = null;
        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");
        String json = new StringBuilder()
                .append("{")
                .append("\"estado\":\"" )
                .append(Estado)
                .append("\",")
                .append("\"fechaCreacion\":\"" )
                .append(fecha)
                .append("\",")
                .append("\"nombre\":\"" )
                .append(Nombre)
                .append("\"")
                .append("}").toString();
        System.out.println("jsonmarcas "+json+"\n");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8089/marca/"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        marcas = mapper.readValue(response.body(), new TypeReference<MarcaDTO>() {});

        return marcas;
    }


    public static ActivoDTO ObtenerActivo1(Long Continente, Long numero, String Estado, LocalDateTime fecha, LocalDateTime fechaModificacion, String Nombre, MarcaDTO Marcaid, ProveedoresDTO Provedorid) throws IOException, InterruptedException {
        LocalDate localDatemarca = Instant.ofEpochMilli(Marcaid.getFechaCreacion().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateProveedor = Instant.ofEpochMilli(Provedorid.getFechaCreacion().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateproveedormo = Instant.ofEpochMilli(Provedorid.getFechaModificacion().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        ActivoDTO activos = null;
        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");
        String json = new StringBuilder()
                .append("{")
                .append("\"continente\":\"" )
                .append(Continente)
                .append("\",")
                .append("\"estado\":\"" )
                .append(Estado)
                .append("\",")
                .append("\"fechaCreacion\":\"" )
                .append(fecha)
                .append("\",")
                .append("\"fechaModificacion\":\"" )
                .append(fechaModificacion)
                .append("\",")
                .append("\"marca\":{" )
                .append("\"estado\":\"" )
                .append(Marcaid.getEstado())
                .append("\",")
                .append("\"fechaCreacion\":\"" )
                .append(localDatemarca)
                .append("\",")
                .append("\"id\":\"" )
                .append(Marcaid.getId())
                .append("\",")
                .append("\"nombre\":\"" )
                .append(Marcaid.getNombre())
                .append("\"},")
                .append("\"nombre\":\"" )
                .append(Nombre)
                .append("\",")
                .append("\"numero\":\"" )
                .append(numero)
                .append("\",")
                .append("\"proveedores\":{" )
                .append("\"correoElectronico\":\"" )
                .append(Provedorid.getCorreoElectronico())
                .append("\",")
                .append("\"estado\":\"" )
                .append(Provedorid.getEstado())
                .append("\",")
                .append("\"fechaCreacion\":\"" )
                .append(localDateProveedor)
                .append("\",")
                .append("\"fechaModificacion\":\"" )
                .append(localDateproveedormo)
                .append("\",")
                .append("\"id\":\"" )
                .append(Provedorid.getId())
                .append("\",")
                .append("\"nombre\":\"" )
                .append(Provedorid.getNombre())
                .append("\",")
                .append("\"notas\":\"" )
                .append(Provedorid.getNotas())
                .append("\",")
                .append("\"telefono\":\"" )
                .append(Provedorid.getTelefono())
                .append("\"}")
                .append("}").toString();
        System.out.println("jsonActivo "+json+"\n");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8089/activo/"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        activos = mapper.readValue(response.body(), new TypeReference<ActivoDTO>() {});
        return activos;

    }
}
