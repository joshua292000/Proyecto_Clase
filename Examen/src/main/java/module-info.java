module org.una.inventario.app_escritorio {

    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;
    requires lombok;
    requires com.jfoenix;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires jasperreports;
    requires java.desktop;

    opens org.una.inventario.app_escritorio to javafx.fxml;
    opens org.una.inventario.app_escritorio.Controller to javafx.fxml;
    exports org.una.inventario.app_escritorio;
    exports org.una.inventario.app_escritorio.Controller;
    exports org.una.inventario.app_escritorio.DTO to com.fasterxml.jackson.databind;
    requires com.opencsv;
    opens org.una.inventario.app_escritorio.DTO to javafx.base;
    //opens org.una.inventario.app_escritorio.Util to javafx.base;
}