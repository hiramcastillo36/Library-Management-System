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

    opens library.library.controller to javafx.fxml;
    exports library.library.controller;

    opens library.library.homePage to javafx.fxml;
    exports library.library.homePage;

    opens library.library.models to javafx.fxml;
    exports library.library.models;

    opens library.library.AdminInterface to javafx.fxml;
    exports library.library.AdminInterface;

    opens library.library.AccountInterface to javafx.fxml;
    exports library.library.AccountInterface;

    opens library.library.adminDashboard to javafx.fxml;
    exports library.library.adminDashboard;

    opens library.library.adminProfile to javafx.fxml;
    exports library.library.adminProfile;

    opens library.library.booking to javafx.fxml;
    exports library.library.booking;

    opens library.library.login to javafx.fxml;
    exports library.library.login;

}