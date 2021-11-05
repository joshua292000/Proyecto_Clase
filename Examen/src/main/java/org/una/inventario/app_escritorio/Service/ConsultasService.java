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
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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


        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());
        if(response.body().equals("No se encontro información en su solicitud, revise su petición")){
            provedor=null;
        }else{
            provedor = mapper.readValue(response.body(), new TypeReference<ProveedoresDTO>() {});
        }

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return provedor;

    }
    public static ProveedoresDTO ProveeCBX(String Nombre, String Notas,String Correo,String Estado,String Telefono,LocalDate fecha,LocalDate fechamodi) throws IOException, InterruptedException {

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
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8089/proveedores/"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());

        proveedores = mapper.readValue(response.body(), new TypeReference<ProveedoresDTO>() {});

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
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


        System.out.println("status "+response.statusCode());

        System.out.println("cuerpo "+response.body());

        if(response.body().equals("No se encontro información en su solicitud, revise su petición")){
            marca=null;
        }else{
            marca = mapper.readValue(response.body(), new TypeReference<MarcaDTO>() {});
        }


        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return marca;

    }
    public static MarcaDTO MarcaCBX(String Estado, LocalDate fecha, String Nombre) throws IOException, InterruptedException {

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
                /*.append("\"id\":\"" )
                .append(id)
                .append("\",")*/
                .append("\"nombre\":\"" )
                .append(Nombre)
                .append("\"")
                .append("}").toString();
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


    public static ActivoDTO ObtenerActivo1(Long Continente,String Estado, LocalDate fecha, LocalDate fechaModificacion, String Nombre,MarcaDTO Marcaid, ProveedoresDTO Provedorid) throws IOException, InterruptedException {
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
                .append("\"proveedor\":{" )
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

       /* String json = new StringBuilder()
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
                .append("\"nombre\":\"" )
                .append(Nombre)
                .append("\",")
                .append("{\"marca\":\"" )
                .append(Marcaid)
                .append("\"},")
                .append("{\"proveedor\":\"" )
                .append(Provedorid)
                .append("\"}")
                .append("}").toString();*/
        System.out.println("json "+json);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8089/activo/"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());

        activos = mapper.readValue(response.body(), new TypeReference<ActivoDTO>() {});

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return activos;

    }

    public static List<ActivoDTO> ObtenerActivo2(int id, LocalDate startDate, LocalDate endDate) throws IOException, InterruptedException {

        List<ActivoDTO> activos = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/activo/findByActivosxMarcaDescBetweenFechas/"+id+"/"+startDate+"/"+endDate))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());

        activos = mapper.readValue(response.body(), new TypeReference<List<ActivoDTO>>() {});

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return activos;

    }

    public static List<ActivoDTO> ObtenerActivo3(int id, LocalDate startDate, LocalDate endDate) throws IOException, InterruptedException {

        List<ActivoDTO> activos = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/activo/findByActivosxProveDescBetweenFechas/"+id+"/"+startDate+"/"+endDate))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());

        activos = mapper.readValue(response.body(), new TypeReference<List<ActivoDTO>>() {});

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return activos;

    }

    public static List<ActivoDTO> ObtenerActivo4(int id, LocalDate startDate, LocalDate endDate) throws IOException, InterruptedException {

        List<ActivoDTO> activos = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/activo/findByActivosxProveAscBetweenFechas/"+id+"/"+startDate+"/"+endDate))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());

        activos = mapper.readValue(response.body(), new TypeReference<List<ActivoDTO>>() {});

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return activos;

    }

}
