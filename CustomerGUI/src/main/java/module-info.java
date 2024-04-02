module Factory {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;

    exports factory.controller;
    exports factory;
    exports factory.model;
    opens factory.model to com.google.gson;

}