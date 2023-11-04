module library.library {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens library.library to javafx.fxml;
    exports library.library;
    exports library.library.controller;
    opens library.library.controller to javafx.fxml;
}