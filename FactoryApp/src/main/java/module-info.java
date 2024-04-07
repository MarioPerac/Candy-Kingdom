module factory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires com.google.gson;
    requires java.rmi;
    requires java.logging;

    opens factory.controller to javafx.fxml;
    exports factory.controller;
    exports factory;
    exports factory.rmi;
    exports factory.model;
    opens factory to javafx.fxml;
    opens factory.model to com.google.gson, javafx.base;

}