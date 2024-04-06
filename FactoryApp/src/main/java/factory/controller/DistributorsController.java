package factory.controller;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DistributorsController {
    public VBox mainVBox;


    public void addDistributors(String[] distributors) {
        for (String distributor : distributors) {
            Button button = new Button(distributor);
            button.setOnMouseClicked(event -> {
                //to do
            });
            mainVBox.getChildren().add(button);
        }
    }

}
