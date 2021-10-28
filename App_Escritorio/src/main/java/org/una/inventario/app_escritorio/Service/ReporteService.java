package org.una.inventario.app_escritorio.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.una.inventario.app_escritorio.DTO.ActivoDTO;
import org.una.inventario.app_escritorio.DTO.AuthenticationResponse;
import org.una.inventario.app_escritorio.DTO.ReporteDTO;
import org.una.inventario.app_escritorio.Util.AppContext;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.URI;
import java.util.List;

public class ReporteService implements JRDataSource {


    private ObservableList<ReporteDTO> reportes = FXCollections.observableArrayList();
    private int index;
    public ReporteService(){
        reportes = (ObservableList<ReporteDTO>) AppContext.getInstance().get("reporte");
    }

    @Override
    public boolean next() throws JRException {
       index++;
       return (index<reportes.size());
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;
        String fieldname = jrField.getName();
        switch (fieldname){
            case "id":
                valor = reportes.get(index).getId();
                break;

            case "nombre":
                valor = reportes.get(index).getNombre();
                break;

            case "fecha":
                valor = reportes.get(index).getFecha();
                break;

            case "estado":
                valor = reportes.get(index).getEstado();
                break;

            case "marca":
                valor = reportes.get(index).getMarca();
                break;
            case "total":
                valor = String.valueOf(reportes.size());
                break;
            case "auditor":
                AuthenticationResponse auditor = (AuthenticationResponse) AppContext.getInstance().get("Rol");
                valor = auditor.getUsuarioDTO().getNombreCompleto();
                break;
        }

        return valor;
        }
    public static JRDataSource getDataSource(){
        return new ReporteService();
    }
}
