package org.una.inventario.app_escritorio.Controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class PrincipalController extends Controller implements Initializable {
    @FXML
    public JFXRadioButton cbMarca;
    @FXML
    public JFXRadioButton cbProveedor;
    @FXML
    private JFXButton btnAscendente;
    @FXML
    private JFXButton btnDescendente;
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

    public void OnActionbtnGenerarReporte(ActionEvent actionEvent) {
        if(!dtpFInicio.getEditor().getText().isEmpty() && !dtpFFinal.getEditor().getText().isEmpty()){
            System.out.println("No se digitaron datos");
        }
    }

    public void OnActionbtnVisualizarReporte(ActionEvent actionEvent) {
    }

    public void OnActionbtnImprimirReporte(ActionEvent actionEvent) {
    }

    public void OnActionbtnAscendente(ActionEvent actionEvent) {
    }

    public void OnActionbtnDescendente(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {

    }
}
