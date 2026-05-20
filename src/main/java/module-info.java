/**
 * Main module descriptor for the Farmer Fred's Trivia application.
 * Defines the required dependencies and packages for the project.
 */
module com.example.chermn {
    // -- Core UI (JavaFx) --
    requires javafx.controls;
    requires javafx.fxml;

    // -- Other UI Libraries --
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    // -- Database --
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires org.json;

    // -- Networking --
    requires java.net.http;
    requires java.desktop;
    requires jbcrypt;

    // -- Reflection access (JavaFX FXML injection) --
    opens com.example.chermn to javafx.fxml;
    opens com.example.chermn.controller to javafx.fxml;

    // -- Project 'Public' APIs --
    exports com.example.chermn;
    exports com.example.chermn.controller;
    exports com.example.chermn.model;

}