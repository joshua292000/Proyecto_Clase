package org.una.inventario.app_escritorio.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

public class LogginController extends Controller implements Initializable {
    @FXML
    public JFXTextField txtUsuario;
    public JFXPasswordField txtContrasenia;
    public JFXButton btnIngresar;


    @Override
    public void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void OnActionbtnIngresar(ActionEvent actionEvent) {
    }
}
