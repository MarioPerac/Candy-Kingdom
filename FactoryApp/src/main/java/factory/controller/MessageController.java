package factory.controller;

import factory.service.MulticastService;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MessageController {

    public TextArea messageTextArea;
    private MulticastService multicastService = new MulticastService();

    public void onSendButtonClick(MouseEvent mouseEvent) {

        String message = messageTextArea.getText();

        multicastService.sendMessage(message);
    }
}
