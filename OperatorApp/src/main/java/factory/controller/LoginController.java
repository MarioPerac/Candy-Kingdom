package factory.controller;

import factory.service.OperatorService;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {


    public TextField usernameField;
    public Label infoLogInLabel;

    private OperatorService operatorService = new OperatorService();
    public void onLoginButtonClicked(MouseEvent mouseEvent) {

        String username = usernameField.getText();


        if (username.isEmpty() ) {
            infoLogInLabel.setText("Please enter all fields.");
        } else {

            if(operatorService.authentication(username)){
            Controller.changeScene("main-view.fxml", mouseEvent);
            }
            else{
                infoLogInLabel.setText("Operator with this credentials does not exist.");
            }
        }
    }
}