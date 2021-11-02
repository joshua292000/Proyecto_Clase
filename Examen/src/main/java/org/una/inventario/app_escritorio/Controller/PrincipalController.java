package org.una.inventario.app_escritorio.Controller;

import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.una.inventario.app_escritorio.DTO.*;
import org.una.inventario.app_escritorio.Util.AppContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

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
    public ScrollPane SPane;
    public String SEPARADOR = ";";
    private  ObservableList<String> options = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {
      //  LlenarTabla();
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
               String x = scn.nextLine();
               String[] campos=x.split(SEPARADOR);
               for(int i=0;i<campos.length;i++){
                   //this.tcMarca.setText(campos[i]);
                   //this.tcProveedor.setText(campos[i+1]);
                   //options.add(new ActivosDTO(campos[i],campos[i+1],campos[i+2],campos[i+3],campos[i+4],campos[i+5],campos[i+6],campos[i+7],campos[i+8],campos[i+9]));
                   options.add(campos[i]);
                   System.out.println(campos[i]);
               }
               AppContext.getInstance().delete("activo");
               AppContext.getInstance().set("activo",options);
               this.tbvContenido.setItems(options);
              // options2.add(scn);

           }
       }
    }

    public void OnActionbtnGuardar(ActionEvent actionEvent) {
    }

    public void OnActionbtnVisualizarInformacion(ActionEvent actionEvent) {
       //this.tbvContenido.setItems(options);
        System.out.println("Lista" + options);
    }

    public void LlenarTabla(){
        this.tcMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.tcProveedor.setCellValueFactory(new PropertyValueFactory("proveedor"));
        this.tcNumero.setCellValueFactory(new PropertyValueFactory("numero"));
        this.tcNota.setCellValueFactory(new PropertyValueFactory("nota"));
        this.tcTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        this.tcCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        this.tcFechaProveedor.setCellValueFactory(new PropertyValueFactory("FechaProveedor"));
        this.tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tcEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        this.tcFechadecreacion.setCellValueFactory(new PropertyValueFactory("fechadecreacion"));
        options.add(""+""+""+""+""+""+""+""+""+"");
        this.tbvContenido.setItems(options);

    }
}
