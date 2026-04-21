module com.example.chermn {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.json;
    requires java.sql;

    opens com.example.chermn to javafx.fxml;
    exports com.example.chermn;
    exports com.example.chermn.controller;
    opens com.example.chermn.controller to javafx.fxml;
}