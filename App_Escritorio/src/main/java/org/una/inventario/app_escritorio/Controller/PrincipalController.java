package org.una.inventario.app_escritorio.Controller;

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
import java.util.Collection;
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
    @FXML
    private TableView tbvContenido;
    @FXML
    private TableColumn tcIdx;
    @FXML
    private TableColumn tcNombre;
    @FXML
    private TableColumn tcFecha;
    @FXML
    public TableColumn tcEstado;
    @FXML
    private TableColumn tcMarca;

    private  ObservableList<String> options = FXCollections.observableArrayList();
    private  ObservableList<String> options2 = FXCollections.observableArrayList();

    @FXML
    private ComboBox cbxPrueba = new ComboBox(options);

    private int tipo=0;
    private int AntDec =0;

    public void OnActionbtnGenerarReporte(ActionEvent actionEvent) throws IOException, InterruptedException {
        String id;
        String ids="";
        int numero=0;
        id = cbxPrueba.getValue().toString();
        char [] split = id.toCharArray();
        for(int x=0;x<split.length;x++){
            if(Character.isDigit(split[x])){
                ids+=split[x];
            }
        }
        numero=Integer.parseInt(ids);
        System.out.println("res "+numero);

        if(tipo==2 && AntDec==2){
            List<ActivoDTO> activo = ConsultasService.ObtenerActivo1(numero,dtpFInicio.getValue(),dtpFFinal.getValue());
            if(activo!=null){
                for(ActivoDTO activos:activo){
                    options2.add(activos.getId()+activos.getNombre()+activos.getMarca()+activos.getFechaCreacion()+activos.getEstado());
                }
                this.tbvContenido.setItems(options2);
            }
        }else if(tipo==2 && AntDec==1){
            List<ActivoDTO> activo = ConsultasService.ObtenerActivo2(numero,dtpFInicio.getValue(),dtpFFinal.getValue());
            if(activo!=null){
                System.out.println("Entre");
                for(ActivoDTO activos:activo){
                    options2.add(activos.getId()+activos.getNombre()+activos.getMarca()+activos.getFechaCreacion()+activos.getEstado());
                }
                this.tbvContenido.setItems(options2);
                System.out.println(options2);
            }
        }else if(tipo==1 && AntDec==1){
            List<ActivoDTO> activo = ConsultasService.ObtenerActivo3(numero,dtpFInicio.getValue(),dtpFFinal.getValue());
            if(activo!=null){

                for(ActivoDTO activos:activo){
                    options2.add(activos.getId()+activos.getNombre()+activos.getMarca()+activos.getFechaCreacion()+activos.getEstado());
                }
                this.tbvContenido.setItems(options2);

            }
        }else if(tipo==1 && AntDec==2){
            List<ActivoDTO> activo = ConsultasService.ObtenerActivo4(numero,dtpFInicio.getValue(),dtpFFinal.getValue());
            if(activo!=null){
                for(ActivoDTO activos:activo){
                    options2.add(activos.getId()+activos.getNombre()+activos.getMarca()+activos.getFechaCreacion()+activos.getEstado());
                }
                this.tbvContenido.setItems(options2);
            }
        }

    }

    public void OnActionbtnVisualizarReporte(ActionEvent actionEvent) {
        try{
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/org/una/inventario/app_escritorio/resources/Reporte.jrxml"));
        }catch(Exception ex){
            ex.getMessage();
        }
    }

    public void OnActionbtnImprimirReporte(ActionEvent actionEvent) {
    }

    public void OnActionDespliegue(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       //ad LlenarTabla();
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

    public void LlenarTabla(){
        this.tcIdx.setCellValueFactory(new PropertyValueFactory("id"));
        this.tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tcMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.tcFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.tcEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        options2.add(""+""+""+""+"");
        this.tbvContenido.setItems(options2);
    }
}
