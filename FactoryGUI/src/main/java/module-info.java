module factory.factorygui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens factory.controller to javafx.fxml;
    exports factory.controller;
    exports factory;
    opens factory to javafx.fxml;
}