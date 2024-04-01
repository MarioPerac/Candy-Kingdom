package factory.controller;

import factory.model.Status;
import factory.model.User;
import factory.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsersController implements Initializable {
    @FXML
    private TableColumn<User, String> statusColumn;
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
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        setAcceptColumn();
        setRejectColumn();

        usersTableView.setItems(users);
    }

    private void setAcceptColumn() {
        acceptColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<User, Button> call(TableColumn<User, Button> param) {
                return new TableCell<>() {
                    private final Button acceptButton = new Button();

                    {
                        acceptButton.setOnAction(event -> {
                            User user = getTableView().getItems().get(getTableRow().getIndex());

                            if (user.getStatus().equals(Status.PENDING.toString()) ) {
                                user.setStatus(Status.ACCEPTED.toString());
                                System.out.println("Korisnik prihvacen");
                                usersTableView.refresh();
                            }
                            else if(user.getStatus().equals(Status.ACCEPTED.toString())){
                                System.out.println("Korisnik obiran");

                                usersTableView.refresh();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            User user = getTableView().getItems().get(getTableRow().getIndex());

                            if (user.getStatus().equals(Status.PENDING.toString())) {
                                acceptButton.setText("Accept");
                            } else if(user.getStatus().equals(Status.ACCEPTED.toString())) {
                                acceptButton.setText("Delete");
                            }
                            else {
                                acceptButton.setText("Delete");
                                acceptButton.setDisable(true);
                            }
                            setGraphic(acceptButton);
                        }
                    }
                };
            }
        });
    }


    private void setRejectColumn() {
        rejectColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<User, Button> call(TableColumn<User, Button> param) {
                return new TableCell<>() {
                    private final Button rejectButton = new Button();

                    {
                        rejectButton.setOnAction(event -> {
                            User user = getTableView().getItems().get(getTableRow().getIndex());
                            // Handle reject action based on user status
                            if (user.getStatus().equals(Status.PENDING.toString())) {
                                // Perform reject action
                                System.out.println(user.getUsername());
                                user.setStatus(Status.REJECTED.toString());

                                usersTableView.refresh();
                            }
                            else if(user.getStatus().equals(Status.ACCEPTED.toString()))
                            {
                                user.setStatus(Status.BLOCKED.toString());

                                usersTableView.refresh();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            User user = getTableView().getItems().get(getTableRow().getIndex());

                            if (user.getStatus().equals(Status.PENDING.toString())) {
                                rejectButton.setText("Reject");
                            } else if(user.getStatus().equals(Status.ACCEPTED.toString())) {
                                rejectButton.setText("Block");
                            }
                            else{
                                rejectButton.setText("Block");
                                rejectButton.setDisable(true);
                            }
                            setGraphic(rejectButton);
                        }
                    }
                };
            }
        });
    }
}
