package org.una.inventario.app_escritorio.Controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
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

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrincipalController extends Controller implements Initializable {

    public JFXButton btnGuardar;
    public JFXButton btnVisualizarInformacion;
    public JFXButton btnAgregarA;
    public TableView<ActivosDTO> tbvContenido;
    public TableColumn tcMarca;
    public TableColumn tcProveedor;
    public TableColumn tcNumero;
    public TableColumn tcNota;
    public TableColumn tcTelefono;
    public TableColumn tcCorreoElectronico;
    public TableColumn tcFechadeCreaciondelProveedor;
    public TableColumn tcContinente;
    public TableColumn tcNombre;
    public TableColumn tcEstado;
    public TableColumn tcFechadecreacion;
    public ScrollPane SPane;
    String[] opcionemss = {"Corregir la informacion", "Descartar la información", "Cancelar"};
    final static String DATE_FORMAT = "dd-MM-yyyy";
    public String SEPARADOR = ";";
    private  ObservableList<ActivosDTO>  options = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {
       LlenarTabla();

    }

    public void OnActionbtnAgregarA(ActionEvent actionEvent) throws IOException, CsvValidationException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivos");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("csv", "*.CSV")
        );
        File filecsv = fileChooser.showOpenDialog(null);
        FileReader archCSV = null;
        CSVReader csvReader = null;
        archCSV = new FileReader(filecsv);
        CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
        csvReader = new CSVReaderBuilder(archCSV).withCSVParser(conPuntoYComa).build();
        String[] fila = null;
        if (filecsv != null) {
            while ((fila = csvReader.readNext()) != null) {

             //System.out.println("num"+Validarfila2);
             //System.out.println("telef"+Validarfila4);
                System.out.println(fila[0]
                        + " | " + fila[1]
                        + " |  " + fila[2]
                        + " |   " + fila[3]
                        + " |    " + fila[4]
                        + " |      " + fila[5]
                        + " |       " + fila[6]
                        + " |        " + fila[7]
                        + " |         " + fila[8]
                        + " |           " + fila[9]
                        + " |             " + fila[10]
                );
                options.add(new ActivosDTO(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], fila[6], fila[7], fila[8], fila[9],fila[10]));
                boolean Validarfila2 = fila[2].matches("[+-]?\\d*(\\.\\d+)?");
                boolean Validarfila4 = fila[4].matches("[+-]?\\d*(\\.\\d+)?");

                if(Validarfila2==false){
                    JOptionPane.showOptionDialog(null,"El campo con la siguiente información <<"+fila[2]+">> posee letras, favor solo ingresar numeros, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss,opcionemss[0]);
                    System.out.println("Favor revisar la fila del numero, ingrese solo números");
                }
                if(Validarfila4==false){
                    JOptionPane.showOptionDialog(null,"El campo <<"+fila[4]+">> posee letras, favor solo ingresar numeros sin guiones ni letras, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss,opcionemss[0]);
                    System.out.println("Favor revisar la fila de teléfono, ingrese solo números sin guiones de por medio");
                }
                ;
                if(isDateValid(fila[6])==false){
                    JOptionPane.showOptionDialog(null,"El campo <<"+fila[6]+">> posee un formato distinto de fecha, favor solo ingresar en el siguiente formato <<dd/MM/yyyy>>, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss,opcionemss[0]);
                }
                if(isDateValid(fila[10])==false){
                    JOptionPane.showOptionDialog(null,"El campo <<"+fila[10]+">> posee un formato distinto de fecha, favor solo ingresar en el siguiente formato <<dd/MM/yyyy>>, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss,opcionemss[0]);
                }
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
        this.tcCorreoElectronico.setCellValueFactory(new PropertyValueFactory("correoElectronico"));
        this.tcFechadeCreaciondelProveedor.setCellValueFactory(new PropertyValueFactory("fechadeCreaciondelProveedor"));
        this.tcContinente.setCellValueFactory(new PropertyValueFactory("continente"));
        this.tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tcEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        this.tcFechadecreacion.setCellValueFactory(new PropertyValueFactory("fechadecreacion"));
        options.add(new ActivosDTO("","","","","","","","","","",""));
        this.tbvContenido.setItems(options);

    }

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            System.out.println("Yes");
            return true;
        } catch (ParseException e) {
            System.out.println("No");
            return false;
        }
    }


}
