module factory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.rabbitmq.client;
    requires com.fasterxml.jackson.dataformat.xml;

    requires jakarta.mail;
    opens factory to javafx.fxml;
    exports factory;
    exports factory.controller;
    exports factory.model;

}