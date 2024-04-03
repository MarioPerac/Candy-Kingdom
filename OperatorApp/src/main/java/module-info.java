module factory.operatorapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens factory to javafx.fxml;
    exports factory;
    exports factory.controller;
}