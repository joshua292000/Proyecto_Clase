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
import javafx.stage.Stage;
import org.una.inventario.app_escritorio.DTO.*;
import org.una.inventario.app_escritorio.Service.ConsultasService;
import org.una.inventario.app_escritorio.Util.AppContext;
import org.una.inventario.app_escritorio.Util.FlowController;

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
    String[] opcionemss1 = {"OK"};
    final static String DATE_FORMAT = "dd/MM/yyyy";
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
        tbvContenido.getItems().clear();
        btnGuardar.setDisable(false);
        String nombre="";
        try {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar archivos");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv", "*.CSV")
            );
            List<File> filecsv = fileChooser.showOpenMultipleDialog(null);
            //File filecsv = fileChooser.showOpenDialog(null);
            FileReader archCSV = null;
            CSVReader csvReader = null;

        for(int i=0; i<filecsv.size();i++) {
            archCSV = new FileReader(filecsv.get(i));
            nombre=filecsv.get(i).getName();
            int cantidad=0;

            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(archCSV).withCSVParser(conPuntoYComa).build();
            String[] fila = null;
            int num = 0;
                if (filecsv != null) {
                    while ((fila = csvReader.readNext()) != null) {
                        System.out.println("Fila tamaño = "+filecsv.get(i).getName());
                        if(fila.length!=11){
                            JOptionPane.showMessageDialog(null,"El archivo <<"+nombre +">> no presenta el formato adecuado para poder abrirlo.Si no sabe cuál es el formato, diríjase al botón de <<Ayuda>>, luego en formato.");
                            if(filecsv.size()>1){
                                i++;
                            }
                            break;
                        }

                        if (num < 1) {
                            num = 1;
                        } else {
                            boolean Validarfila2 = fila[2].matches("[+-]?\\d*(\\.\\d+)?");
                            boolean Validarfila4 = fila[4].matches("[+-]?\\d*(\\.\\d+)?");

                            if (Validarfila2 == false) {
                                int seleccion = JOptionPane.showOptionDialog(null, "El campo con la siguiente información <<" + fila[2] + ">> posee letras, favor solo ingresar numeros, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss, opcionemss[0]);
                                switch (seleccion) {
                                    case 0:
                                        String respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<" + fila[2] + ">>", "Error!", JOptionPane.ERROR_MESSAGE);
                                        fila[2] = respuesta;
                                        break;
                                    case 1:
                                        fila[2] = null;
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
                                        break;
                                    case 1:
                                        fila[4] = null;
                                        break;
                                }
                            }
                            if (isDateValid(fila[6]) == false) {
                                int seleccion = JOptionPane.showOptionDialog(null, "El campo <<" + fila[6] + ">> posee un formato distinto de fecha, favor solo ingresar en el siguiente formato <<dd/MM/yyyy>>, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss, opcionemss[0]);
                                switch (seleccion) {
                                    case 0:
                                        String respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<" + fila[6] + ">>", "Error!", JOptionPane.ERROR_MESSAGE);
                                        fila[6] = respuesta;
                                        break;
                                    case 1:
                                        fila[6] = null;
                                        break;
                                }
                            }
                            if (Integer.parseInt(fila[7])>6 || Integer.parseInt(fila[7])==0) {
                                int seleccion = JOptionPane.showOptionDialog(null, "El campo <<" + fila[7] + ">> favor poner un valor del 1 al 6,el cual corresponde respectivamente: 1.América 2.Europa 3.Asia 4.Oceanía 5.África 6.Antártida ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss, opcionemss[0]);
                                switch (seleccion) {
                                    case 0: {
                                        String respuesta;
                                        do {
                                            respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<" + fila[7] + ">>(1=América 2=Europa 3=Asia 4=Oceanía 5=África 6=Antártida)", "Error!", JOptionPane.ERROR_MESSAGE);
                                            if(respuesta.length()!=0){
                                                if (Integer.parseInt(respuesta) < 7 && Integer.parseInt(respuesta) > 0) {
                                                    fila[7] = respuesta;
                                                } else {
                                                    JOptionPane.showOptionDialog(null, "El campo que acaba de corregir es erróneo, favor poner un valor del 1 al 6,el cual corresponde respectivamente: 1.América 2.Europa 3.Asia 4.Oceanía 5.África 6.Antártida", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss1, opcionemss1[0]);
                                                    respuesta="8";
                                                }
                                            }else {
                                                JOptionPane.showOptionDialog(null, "El campo que acaba de corregir es erróneo, favor poner un valor del 1 al 6,el cual corresponde respectivamente: 1.América 2.Europa 3.Asia 4.Oceanía 5.África 6.Antártida", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss1, opcionemss1[0]);
                                                respuesta="8";
                                            }

                                        } while (respuesta.length()==0 || Integer.parseInt(respuesta) > 7 || Integer.parseInt(respuesta) == 0);

                                        break;
                                    }
                                    case 1:
                                        fila[7] = null;
                                        break;
                                }
                            }
                            if (isDateValid(fila[10]) == false) {
                                int seleccion = JOptionPane.showOptionDialog(null, "El campo <<" + fila[10] + ">> posee un formato distinto de fecha, favor solo ingresar en el siguiente formato <<dd/MM/yyyy>>, ¿Qué desea hacer?", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionemss, opcionemss[0]);
                                switch (seleccion) {
                                    case 0:
                                        String respuesta = JOptionPane.showInputDialog(null, "Escriba correctamente el campo <<" + fila[10] + ">>", "Error!", JOptionPane.ERROR_MESSAGE);
                                        fila[10] = respuesta;
                                        break;
                                    case 1:
                                        fila[10] = null;
                                        break;
                                }
                            }
                            options.add(new ActivosDTO(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], fila[6], fila[7], fila[8], fila[9], fila[10]));
                        }
                    }
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"El archivo <<"+nombre +">> no presenta el formato adecuado para poder abrirlo.Si no sabe cuál es el formato, diríjase al botón de <<Ayuda>>, luego en formato.");
        }
    }
    public void OnActionbtnGuardar(ActionEvent actionEvent) throws ParseException, IOException, InterruptedException {
        long idd=8;
        String nombre= "Licencia Windows";
        for(int x=0;x<options.size();x++){
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(options.get(x).getFechadecreacion(), formato);

            if(ConsultasService.ObtenermarcaxNombre(sacarNombre(options.get(x).getMarca()))==null){
                MarcaDTO marca = ConsultasService.MarcaCBX(options.get(x).getEstado(),fecha,options.get(x).getMarca());
            }
            LocalDate fecha1 = LocalDate.parse(options.get(x).getFechadeCreaciondelProveedor(), formato);
            if(ConsultasService.ObtenerProvedoresxNombre(sacarNombre(options.get(x).getProveedor()))==null){
                ProveedoresDTO proveedor = ConsultasService.ProveeCBX(options.get(x).getProveedor(),options.get(x).getNota(),options.get(x).getCorreoElectronico(),options.get(x).getEstado(),options.get(x).getTelefono(),fecha1,fecha1);
            }
            ActivoDTO activo=ConsultasService.ObtenerActivo1(Long.parseLong(options.get(x).getContinente()),Long.parseLong(options.get(x).getNumero()),options.get(x).getEstado(),fecha,fecha,options.get(x).getNombre(),ConsultasService.ObtenermarcaxNombre(sacarNombre(options.get(x).getMarca())),ConsultasService.ObtenerProvedoresxNombre(sacarNombre(options.get(x).getProveedor())));
        }
        JOptionPane.showMessageDialog(null,"Archivo guardado correctamente");
    }

    public String sacarNombre(String nombre){
        String []n=nombre.split(" ");
        String j=" ";
        if(n.length>1){
            for(int x=0;x<n.length;x++){
                if(x==0){
                    j=n[x];
                }else{
                    j=j+"%20";
                    j=j+n[x];
                }
            }
        }else{
            j=nombre;
        }
        return j;
    }
    public void OnActionbtnVisualizarInformacion(ActionEvent actionEvent) {
        //((Stage) btnVisualizarInformacion.getScene().getWindow()).close();
        FlowController.getInstance().goViewInWindow("Ayuda");
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
        this.tbvContenido.setItems(options);

    }

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


}
