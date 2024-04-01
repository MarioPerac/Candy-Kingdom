package factory.controller;

import factory.LoginApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    public TextField usernameField;
    public PasswordField passwordField;
    public Label infoLogInLabel;


    public void onSignInButtonClick(MouseEvent actionEvent) {
        Controller.changeScene("signIn-view.fxml", actionEvent);

    }

    //to do
    public void onLoginButtonClicked(MouseEvent mouseEvent) {
    }
}