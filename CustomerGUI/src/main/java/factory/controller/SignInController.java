package factory.controller;

import factory.model.User;
import factory.service.UserService;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignInController {
    public TextField companyField;
    public TextField emailField;
    public TextField phoneField;
    public TextField usernameField;
    public PasswordField passwordField;

    public PasswordField confirmPasswordField;
    public Label signInLabel;
    public Label signInInfoLabel;
    public Button signInButton;

    UserService userService = new UserService();

    public void onSignInButtonClick(MouseEvent mouseEvent) {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String company = companyField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                phone.isEmpty() || email.isEmpty() || company.isEmpty()) {

            signInInfoLabel.setText("Please enter all fields.");


        } else {

            if (password.equals(confirmPassword)) {

                boolean result = userService.register(new User(username, password, confirmPassword, email, phone, company));


                if (result) {
                    signInInfoLabel.setText("Request for sign-in is sent");
                    signInButton.setText("Go Back");
                    signInButton.setOnMouseClicked(e -> {
                        Controller.changeScene("login-view.fxml", e);
                    });
                } else {
                    signInInfoLabel.setText("Try again. Failed to send sign-in request.");
                }
            } else {

                signInInfoLabel.setText("Password is not confirmed.");
            }

        }


    }
}
