module Factory {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    requires com.rabbitmq.client;
    requires com.fasterxml.jackson.dataformat.xml;
    requires java.desktop;
    exports factory.controller;
    exports factory;
    exports factory.model;
    exports factory.service;
    opens factory.model to com.google.gson;

}