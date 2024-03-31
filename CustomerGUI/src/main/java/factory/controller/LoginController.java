package factory.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    public TextField usernameField;
    public PasswordField passwordField;


    public void onSignInButtonClick(MouseEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("signIn-view.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();

    }
}