module factory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.rabbitmq.client;
    requires com.fasterxml.jackson.dataformat.xml;

    requires jakarta.mail;
    requires com.google.gson;
    opens factory to javafx.fxml;
    opens factory.model to com.google.gson;
    exports factory;
    exports factory.controller;
    exports factory.model;

}