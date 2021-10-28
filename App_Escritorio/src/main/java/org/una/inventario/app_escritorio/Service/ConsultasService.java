package org.una.inventario.app_escritorio.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.DatePicker;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ConsultasService {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static ObjectMapper mapper = new ObjectMapper();

    public static List<ProveedoresDTO> ProveeCBX() throws IOException, InterruptedException {

        List<ProveedoresDTO> proveedores = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/proveedores"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());

        proveedores = mapper.readValue(response.body(), new TypeReference<List<ProveedoresDTO>>() {});

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return proveedores;

    }


    public static List<MarcaDTO> MarcaCBX() throws IOException, InterruptedException {

        List<MarcaDTO> marcas = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/marca"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());

        marcas = mapper.readValue(response.body(), new TypeReference<List<MarcaDTO>>() {});

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return marcas;

    }


    public static List<ActivoDTO> ObtenerActivo1(int id, LocalDate startDate, LocalDate endDate) throws IOException, InterruptedException {

        List<ActivoDTO> activos = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/activo/findByActivosxMarcaAscBetweenFechas/"+id+"/"+startDate+"/"+endDate))
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
