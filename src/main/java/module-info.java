module com.example.chermn {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires org.json;
    requires java.net.http;
    requires java.desktop;

    opens com.example.chermn to javafx.fxml;
    exports com.example.chermn;
    exports com.example.chermn.controller;
    exports com.example.chermn.model;
    opens com.example.chermn.controller to javafx.fxml;
}