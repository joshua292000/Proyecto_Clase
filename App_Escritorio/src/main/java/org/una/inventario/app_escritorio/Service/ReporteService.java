package org.una.inventario.app_escritorio.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.una.inventario.app_escritorio.DTO.ActivoDTO;
import org.una.inventario.app_escritorio.DTO.AuthenticationResponse;
import org.una.inventario.app_escritorio.Util.AppContext;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.URI;
import java.util.List;

public class ReporteService {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static ObjectMapper mapper = new ObjectMapper();

    public static List<ActivoDTO> Reporte() throws IOException, InterruptedException {

        List<ActivoDTO> activos = null;

        AuthenticationResponse token = (AuthenticationResponse) AppContext.getInstance().get("Rol");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/activo"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .setHeader("AUTHORIZATION", "Bearer " + token.getJwt())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print response headers
       // HttpHeaders headers = response.headers();
        //adminheaders.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println("status "+response.statusCode());

        // print response body
        System.out.println("cuerpo "+response.body());

        activos = mapper.readValue(response.body(), new TypeReference<List<ActivoDTO>>() {});

        //AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
        return activos;

    }
}
