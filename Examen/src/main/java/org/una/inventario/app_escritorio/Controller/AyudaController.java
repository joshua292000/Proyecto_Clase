package org.una.inventario.app_escritorio.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.una.inventario.app_escritorio.Util.FlowController;

import java.net.URL;
import java.util.ResourceBundle;

public class AyudaController extends Controller implements Initializable {

    @FXML
    private Label lblTexto;

    @FXML
    private Button btnAtras;

    @FXML
    private Label lblTitulo;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {

    }

    @FXML
    void b9eb88(ActionEvent event) {

    }

    @FXML
    void OnActionAtras(ActionEvent event) {

        FlowController.getInstance().goViewInWindow("Principalview");
    }
}
