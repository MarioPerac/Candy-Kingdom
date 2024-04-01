package factory.controller;

import factory.model.User;
import factory.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsersController implements Initializable {
    @FXML
    private TableView<User> usersTableView;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> companyColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, Button> acceptColumn;
    @FXML
    private TableColumn<User, Button> rejectColumn;

    UserService userService = new UserService();
    ObservableList<User> users;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<User> userList = userService.getAll();
        users = FXCollections.observableArrayList(userList);

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));



        usersTableView.setItems(users);
    }
}
