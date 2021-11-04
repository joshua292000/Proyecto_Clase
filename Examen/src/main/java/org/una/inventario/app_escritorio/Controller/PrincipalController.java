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
import org.una.inventario.app_escritorio.Service.ConsultasService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    String[] opcionemss = {"Corregir la información", "Descartar la información"};
    final static String DATE_FORMAT = "yyyy-MM-dd";
    public String SEPARADOR = ";";
    private  ObservableList<ActivosDTO>  options = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //LlenarTabla();
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
        int num=0;
        if (filecsv != null) {
            while ((fila = csvReader.readNext()) != null) {
                if (num < 1) {
                    num = 1;
                } else {
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
                    //options.add(new ActivosDTO(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], fila[6], fila[7], fila[8], fila[9],fila[10]));
                    boolean Validarfila2 = fila[2].matches("[+-]?\\d*(\\.\\d+)?");
                    boolean Validarfila4 = fila[4].matches("[+-]?\\d*(\\.\\d+)?");

                    if (Validarfila2 == false) {
                        int seleccion = JOptionPane.showOptionDialog(null, "El campo con la siguiente información <<" + fila[2] + ">> posee letras, favor solo ingresar numeros, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss, opcionemss[0]);
                        System.out.println("Favor revisar la fila del numero, ingrese solo números");
                        switch (seleccion) {
                            case 0:
                                System.out.println("Hacer cambios");
                                String respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<" + fila[2] + ">>", "Error!", JOptionPane.ERROR_MESSAGE);
                                System.out.println("Cambios " + respuesta);
                                fila[2] = respuesta;
                                break;
                            case 1:
                                fila[2] = null;
                                System.out.println("Descartar");
                                break;
                        }

                    }
                    if (Validarfila4 == false) {
                        int seleccion = JOptionPane.showOptionDialog(null, "El campo <<" + fila[4] + ">> posee letras, favor solo ingresar numeros sin guiones ni letras, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss, opcionemss[0]);
                        System.out.println("Favor revisar la fila de teléfono, ingrese solo números sin guiones de por medio");
                        switch (seleccion) {
                            case 0:
                                String respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<" + fila[4] + ">>", "Error!", JOptionPane.ERROR_MESSAGE);
                                fila[4] = respuesta;
                                System.out.println("Nuevo Campo Fila[4] " + fila[4]);
                                break;
                            case 1:
                                fila[4] = null;
                                System.out.println("Descartar");
                                break;
                        }
                    }
                    if (isDateValid(fila[6]) == false) {
                        int seleccion = JOptionPane.showOptionDialog(null, "El campo <<" + fila[6] + ">> posee un formato distinto de fecha, favor solo ingresar en el siguiente formato <<dd/MM/yyyy>>, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss, opcionemss[0]);
                        switch (seleccion) {
                            case 0:
                                System.out.println("Hacer cambios");
                                String respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<" + fila[6] + ">>", "Error!", JOptionPane.ERROR_MESSAGE);
                                System.out.println("Cambios " + respuesta);
                                fila[6] = respuesta;
                                break;
                            case 1:
                                fila[6] = null;
                                System.out.println("Descartar");
                                break;
                        }
                    }
                    if(fila[7]!=null||fila[7]!="1"||fila[7]!="2"||fila[7]!="3"||fila[7]!="4"||fila[7]!="5"||fila[7]!="6"){
                        int seleccion = JOptionPane.showOptionDialog(null,"El campo <<"+fila[7]+">> favor poner un valor del 1 al 6,el cual corresponde respectivamente: 1.América 2.Europa 3.Asia 4.Oceanía 5.África 6.Antártida ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss,opcionemss[0]);
                        switch (seleccion){
                            case 0:
                                System.out.println("Hacer cambios");
                                String respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<"+fila[7]+">>", "Error!", JOptionPane.ERROR_MESSAGE);
                                System.out.println("Cambios "+respuesta);
                                fila[7]=respuesta;
                                break;
                            case 1:
                                fila[7]=null;
                                System.out.println("Descartar");
                                break;
                        }
                    }
                    if (isDateValid(fila[10]) == false) {
                        int seleccion = JOptionPane.showOptionDialog(null, "El campo <<" + fila[10] + ">> posee un formato distinto de fecha, favor solo ingresar en el siguiente formato <<dd/MM/yyyy>>, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss, opcionemss[0]);
                        switch (seleccion) {
                            case 0:
                                System.out.println("Hacer cambios");
                                String respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<" + fila[10] + ">>", "Error!", JOptionPane.ERROR_MESSAGE);
                                System.out.println("Cambios " + respuesta);
                                fila[10] = respuesta;
                                break;
                            case 1:
                                fila[10] = null;
                                System.out.println("Descartar");
                                break;
                        }
                    }
                    options.add(new ActivosDTO(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], fila[6], fila[7], fila[8], fila[9], fila[10]));
                }
            }
        }
    }
    public void OnActionbtnGuardar(ActionEvent actionEvent) throws ParseException, IOException, InterruptedException {
        long idd=8;

        //for(int x=2;x<options.size();x++){
            DateTimeFormatter formato = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate fecha = LocalDate.parse(options.get(0).getFechadecreacion(), formato);
            MarcaDTO marca = ConsultasService.MarcaCBX(options.get(0).getEstado(),fecha,idd,options.get(0).getMarca());

       // }
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
        //options.add(new ActivosDTO("","","","","","","","","","",""));
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
