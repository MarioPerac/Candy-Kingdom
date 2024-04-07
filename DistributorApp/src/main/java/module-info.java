module factory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;

    opens factory to javafx.fxml;
    exports factory;
    exports factory.controller;
    exports factory.model;
    exports factory.rmi;
}