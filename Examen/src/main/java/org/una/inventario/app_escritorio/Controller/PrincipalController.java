package org.una.inventario.app_escritorio.Controller;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import net.sf.jasperreports.engine.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.una.inventario.app_escritorio.DTO.*;
import org.una.inventario.app_escritorio.Service.AutenticacionService;
import org.una.inventario.app_escritorio.Service.ConsultasService;
import org.una.inventario.app_escritorio.Service.ReporteService;
import org.una.inventario.app_escritorio.Util.AppContext;
import org.una.inventario.app_escritorio.Util.FlowController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class PrincipalController extends Controller implements Initializable {

    public JFXButton btnGuardar;
    public JFXButton btnVisualizarInformacion;
    public JFXButton btnAgregarA;
    public TableView tbvContenido;
    public TableColumn tcMarca;
    public TableColumn tcProveedor;
    public TableColumn tcNumero;
    public TableColumn tcNota;
    public TableColumn tcTelefono;
    public TableColumn tcCorreo;
    public TableColumn tcFechaProveedor;
    public TableColumn tcNombre;
    public TableColumn tcEstado;
    public TableColumn tcFechadecreacion;

    private  ObservableList<String> options = FXCollections.observableArrayList();
    private  ObservableList<ReporteDTO> options2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {

    }

    public void OnActionbtnAgregarA(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivos");
        fileChooser.getExtensionFilters().addAll(
             new FileChooser.ExtensionFilter("csv", "*.CSV")
        );
        File filecsv = fileChooser.showOpenDialog(null);
       if (filecsv != null) {
           Scanner scn = new Scanner(filecsv);
           while (scn.hasNext()) {
               System.out.println(scn.nextLine() + "\n");
           }
       }
    }

    public void OnActionbtnGuardar(ActionEvent actionEvent) {
    }

    public void OnActionbtnVisualizarInformacion(ActionEvent actionEvent) {
    }
}
