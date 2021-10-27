module org.una.inventario.app_escritorio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.logging;
    requires com.jfoenix;
    requires lombok;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens org.una.inventario.app_escritorio to javafx.fxml, javafx.base;
    exports org.una.inventario.app_escritorio;
    exports org.una.inventario.app_escritorio.Views;
    opens org.una.inventario.app_escritorio.Views to javafx.fxml;
}