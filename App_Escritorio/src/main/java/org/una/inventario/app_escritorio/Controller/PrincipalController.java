package org.una.inventario.app_escritorio.Controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import org.una.inventario.app_escritorio.DTO.ActivoDTO;
import org.una.inventario.app_escritorio.DTO.AuthenticationResponse;
import org.una.inventario.app_escritorio.Service.AutenticacionService;
import org.una.inventario.app_escritorio.Service.ReporteService;
import org.una.inventario.app_escritorio.Util.FlowController;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalController extends Controller implements Initializable {
    @FXML
    public JFXRadioButton cbDescendente;
    @FXML
    public JFXRadioButton cbAscendente;
    @FXML
    private AnchorPane APFondo;

    @FXML
    public JFXRadioButton cbMarca;
    @FXML
    public JFXRadioButton cbProveedor;
    @FXML
    private JFXButton btnGenerarReporte;
    @FXML
    private JFXButton btnImprimirReporte;
    @FXML
    private JFXButton btnVisualizarReporte;
    @FXML
    private DatePicker  dtpFInicio;
    @FXML
    private DatePicker dtpFFinal;

    private  ObservableList<String> options = FXCollections.observableArrayList();

    @FXML
    private ComboBox cbxPrueba = new ComboBox(options);


    public void OnActionbtnGenerarReporte(ActionEvent actionEvent) throws IOException, InterruptedException {

        List<ActivoDTO> activos = ReporteService.Reporte();
        for(ActivoDTO activo:activos){
            options.add(activo.getId() +"-"+ activo.getNombre());
        }
        cbxPrueba.setItems(options);

    }

    public void OnActionbtnVisualizarReporte(ActionEvent actionEvent) {
    }

    public void OnActionbtnImprimirReporte(ActionEvent actionEvent) {
    }

    public void OnActionDespliegue(ActionEvent actionEvent) {



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {

    }
}
