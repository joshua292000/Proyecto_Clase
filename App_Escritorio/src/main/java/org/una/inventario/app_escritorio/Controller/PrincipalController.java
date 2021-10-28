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
import org.una.inventario.app_escritorio.DTO.MarcaDTO;
import org.una.inventario.app_escritorio.DTO.ProveedoresDTO;
import org.una.inventario.app_escritorio.Service.AutenticacionService;
import org.una.inventario.app_escritorio.Service.ConsultasService;
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
    private DatePicker dtpFInicio;
    @FXML
    private DatePicker dtpFFinal;

    private  ObservableList<String> options = FXCollections.observableArrayList();

    @FXML
    private ComboBox cbxPrueba = new ComboBox(options);

    private int tipo=0;
    private int AntDec =0;


    public void OnActionbtnGenerarReporte(ActionEvent actionEvent) throws IOException, InterruptedException {
        int id = 1;
        if(tipo==2 && AntDec==2){
            List<ActivoDTO> activo = ConsultasService.ObtenerActivo1(id,dtpFInicio.getValue(),dtpFFinal.getValue());
        }else if(tipo==2 && AntDec==1){
            List<ActivoDTO> activo = ConsultasService.ObtenerActivo2(id,dtpFInicio.getValue(),dtpFFinal.getValue());
        }else if(tipo==1 && AntDec==1){
            List<ActivoDTO> activo = ConsultasService.ObtenerActivo3(id,dtpFInicio.getValue(),dtpFFinal.getValue());
        }else if(tipo==1 && AntDec==2){
            List<ActivoDTO> activo = ConsultasService.ObtenerActivo4(id,dtpFInicio.getValue(),dtpFFinal.getValue());
        }

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

    public void OnActioncbProveedor(ActionEvent actionEvent) throws IOException, InterruptedException {
        tipo = 1;
        OpcionPM(tipo);
    }

    public void OnActioncbMarca(ActionEvent actionEvent) throws IOException, InterruptedException {
        tipo = 2;
        OpcionPM(tipo);
    }

    public void OnActioncbDescendente(ActionEvent actionEvent) {
        AntDec =1;
    }

    public void OnActioncbAscendente(ActionEvent actionEvent) {
        AntDec =2;
    }

    public void OpcionPM(int tipo) throws IOException, InterruptedException {
        if(tipo == 1){
            List<ProveedoresDTO> proveedor = ConsultasService.ProveeCBX();
            cbxPrueba.getItems().removeAll();
            for(ProveedoresDTO proveedores:proveedor){
                options.add(proveedores.getId() +"-"+ proveedores.getNombre());
            }
            cbxPrueba.setDisable(false);
            cbxPrueba.setItems(options);

        }else if(tipo == 2){
            List<MarcaDTO> marca = ConsultasService.MarcaCBX();
            cbxPrueba.getItems().removeAll();
            for(MarcaDTO marcas:marca){
                options.add(marcas.getId() +"-"+ marcas.getNombre());
            }
            cbxPrueba.setDisable(false);
            cbxPrueba.setItems(options);

        }
    }
}
