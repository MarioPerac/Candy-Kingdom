module factory.distributorapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;

    opens factory.distributorapp to javafx.fxml;
    exports factory.distributorapp;
    exports factory.distributorapp.controller;
    exports factory.distributorapp.listener;
    exports factory.distributorapp.model;
    exports factory.distributorapp.rmi;
}