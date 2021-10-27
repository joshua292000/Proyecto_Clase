package org.una.inventario.app_escritorio.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.una.inventario.app_escritorio.DTO.AuthenticationResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.URI;

public class AutenticacionService {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static ObjectMapper mapper = new ObjectMapper();

    public static AuthenticationResponse Autenticacion(String cedula, String password) throws IOException, InterruptedException {

        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"cedula\":\"" )
                .append(cedula)
                .append("\",")
                .append("\"password\":\"" )
                .append(password)
                .append("\"")
                .append("}").toString();

        // add json header
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8089/autenticacion"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("cedula: " + cedula);
        System.out.println("contraseña: " + password);

        if(response.statusCode() == 500){
            System.out.println("Contraseña incorrecta");
        }else{
            System.out.println(response.statusCode());
            System.out.println("Contraseña correcta");
            System.out.println(response.body());
            AuthenticationResponse authenticationResponse = mapper.readValue(response.body(), AuthenticationResponse.class);
            return authenticationResponse;

        }
    return null;
    }
}
