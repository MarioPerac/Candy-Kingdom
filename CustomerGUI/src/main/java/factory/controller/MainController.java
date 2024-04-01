package factory.controller;

import javafx.scene.input.MouseEvent;

public class MainController {
    public void onLogoutButtonClicked(MouseEvent mouseEvent) {
        Controller.changeScene("login-view.fxml", mouseEvent);
    }
}
