package factory.controller;

import factory.model.Login;
import factory.model.Status;
import factory.model.UserInfo;
import factory.model.UserRequest;
import factory.service.UserService;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {


    public TextField usernameField;
    public PasswordField passwordField;
    public Label infoLogInLabel;

    UserService userService = new UserService();

    public void onSignInButtonClick(MouseEvent actionEvent) {
        Controller.changeScene("signIn-view.fxml", actionEvent);

    }


    public void onLoginButtonClicked(MouseEvent mouseEvent) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            infoLogInLabel.setText("Please enter all fields.");
        } else {
            Login login = userService.login(new UserRequest(username, password));

            if (login.isSuccessful()) {
                Controller.changeScene("products.fxml", mouseEvent);
                String userEmail = userService.getUserEmail(username);
                UserInfo.getInstance().setUsername(username);
                UserInfo.getInstance().setEmail(userEmail);
            } else if (Status.PENDING.toString().equals(login.getUserStatus())) {
                infoLogInLabel.setText("Your request status is PENDING.\nPlease wait until it is processed.");
            } else if (Status.REJECTED.toString().equals(login.getUserStatus())) {
                infoLogInLabel.setText("Your request status is REJECTED.");
            } else if (Status.BLOCKED.toString().equals(login.getUserStatus())) {
                infoLogInLabel.setText("Your request status is BLOCKED.");
            } else if (Status.ACCEPTED.toString().equals(login.getUserStatus())) {
                infoLogInLabel.setText("Incorrect credentials. Try again.");

            } else {
                infoLogInLabel.setText("User with this credentials does not exist.");
            }
        }
    }
}