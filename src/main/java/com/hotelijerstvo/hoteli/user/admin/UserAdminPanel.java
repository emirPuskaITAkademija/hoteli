package com.hotelijerstvo.hoteli.user.admin;

import com.hotelijerstvo.hoteli.user.User;
import com.hotelijerstvo.hoteli.user.privilege.Privilege;
import com.hotelijerstvo.hoteli.user.privilege.service.PrivilegeServiceLocal;
import com.hotelijerstvo.hoteli.user.service.UserServiceFactory;
import com.hotelijerstvo.hoteli.user.service.UserServiceLocal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class UserAdminPanel extends VBox {

    private final Label titleLabel = new Label("Administracija korisnika");
    private final TableView<User> userTableView = new TableView<>();

    private final TextField usernameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final TextField nameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final ChoiceBox<Privilege> privilegeChoiceBox = new ChoiceBox<>();

    private final Button addButton = new Button("Dodaj korisnika");
    private final Button deleteButton = new Button("Obriši");

    public UserAdminPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10));

        bindTableWithData();
        configureTableView();

        getChildren().addAll(titleLabel, userTableView, getForm());
    }

    private HBox getForm() {
        HBox usernameFormHBox = new HBox();
        usernameFormHBox.setSpacing(5);
        usernameTextField.setPromptText("Enter username...");
        passwordField.setPromptText("Enter password..");
        nameTextField.setPromptText("Enter name...");
        surnameTextField.setPromptText("Enter surname....");
        List<Privilege> privilegeList = PrivilegeServiceLocal.SERVICE.findAll();
        ObservableList<Privilege> observableList = FXCollections.observableList(privilegeList);
        privilegeChoiceBox.setItems(observableList);
        usernameFormHBox
                .getChildren()
                .addAll(usernameTextField,
                        passwordField,
                        nameTextField,
                        surnameTextField,
                        privilegeChoiceBox,
                        addButton,
                        deleteButton);
        addButton.setOnAction(this::onAddButtonHandle);
        return usernameFormHBox;
    }

    private void onAddButtonHandle(ActionEvent actionEvent){
        String usernameInput = usernameTextField.getText();
        String passwordInput = passwordField.getText();
        String nameInput = nameTextField.getText();
        String surnameInput = surnameTextField.getText();
        Privilege privilege = privilegeChoiceBox.getSelectionModel().getSelectedItem();
        User user = new User(usernameInput, passwordInput, nameInput, surnameInput, privilege);
        UserServiceLocal.USER_SERVICE.save(user);
    }

    private void bindTableWithData() {
        UserServiceLocal userService = UserServiceFactory.USER_SERVICE.get();
        List<User> userList = userService.findAll();
        ObservableList<User> observableUserList = FXCollections.observableList(userList);
        userTableView.setItems(observableUserList);
    }

    private void configureTableView() {
        TableColumn<User, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(200);

        TableColumn<User, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameColumn.setMinWidth(200);

        TableColumn<User, String> usernameColumn = new TableColumn<>("Korisničko ime");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setMinWidth(100);

        TableColumn<User, Privilege> privilegeColumn = new TableColumn<>("Privilegija");
        privilegeColumn.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        privilegeColumn.setMinWidth(100);

        userTableView.getColumns().addAll(nameColumn, surnameColumn, usernameColumn, privilegeColumn);
    }
}
