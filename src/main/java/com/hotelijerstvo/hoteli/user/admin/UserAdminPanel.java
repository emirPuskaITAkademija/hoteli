package com.hotelijerstvo.hoteli.user.admin;

import com.hotelijerstvo.hoteli.user.User;
import com.hotelijerstvo.hoteli.user.privilege.Privilege;
import com.hotelijerstvo.hoteli.user.privilege.service.PrivilegeServiceLocal;
import com.hotelijerstvo.hoteli.user.service.UserServiceFactory;
import com.hotelijerstvo.hoteli.user.service.UserServiceLocal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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

    private final Button editButton = new Button("Ažuriraj");
    private final Button deleteButton = new Button("Obriši");

    private ObservableList<User> observableUserList;

    private User selectedUser = null;

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
        privilegeChoiceBox.getSelectionModel().select(0);
        usernameFormHBox
                .getChildren()
                .addAll(usernameTextField,
                        passwordField,
                        nameTextField,
                        surnameTextField,
                        privilegeChoiceBox,
                        addButton,
                        editButton,
                        deleteButton);
        addButton.setOnAction(this::onAddButtonHandle);
        deleteButton.setOnAction(this::onDeleteUserHandle);
        editButton.setOnAction(this::onEditUserHandle);
        return usernameFormHBox;
    }

    private void onEditUserHandle(ActionEvent actionEvent) {
        final User user = userTableView.getSelectionModel().getSelectedItem();
        if(user == null){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Morate odabrati korisnika kojeg želite ažurirati");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
            dialog.show();
        }else{
            user.setName(nameTextField.getText());
            user.setSurname(surnameTextField.getText());
            final User updatedUser = UserServiceLocal.USER_SERVICE.update(user);
            observableUserList.replaceAll((User u1) -> {
                if(u1.equals(updatedUser)){
                    return updatedUser;
                }
                return u1;
            });
        }
    }

    private void onDeleteUserHandle(ActionEvent actionEvent) {
        if (selectedUser != null) {
            UserServiceLocal.USER_SERVICE.delete(selectedUser);
            observableUserList.remove(selectedUser);
        }
    }

    private void onAddButtonHandle(ActionEvent actionEvent) {
        String usernameInput = usernameTextField.getText();
        String passwordInput = passwordField.getText();
        String nameInput = nameTextField.getText();
        String surnameInput = surnameTextField.getText();
        Privilege privilege = privilegeChoiceBox.getSelectionModel().getSelectedItem();
        User user = new User(usernameInput, passwordInput, nameInput, surnameInput, privilege);
        //insert into DB table hotels.users
        UserServiceLocal.USER_SERVICE.save(user);
        //clear form input
        clearInput();
        //refreshment of the user table view
        observableUserList.add(user);
    }

    private void clearInput() {
        usernameTextField.clear();
        passwordField.clear();
        nameTextField.clear();
        surnameTextField.clear();
        privilegeChoiceBox.getSelectionModel().select(0);
    }


    private void bindTableWithData() {
        UserServiceLocal userService = UserServiceFactory.USER_SERVICE.get();
        List<User> userList = userService.findAll();
        observableUserList = FXCollections.observableList(userList);
        userTableView.setItems(observableUserList);
    }

    private void configureTableView() {

        userTableView.setEditable(true);
        TableColumn<User, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setName(event.getNewValue());
                UserServiceLocal.USER_SERVICE.update(user);
            }
        });
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
        Privilege[] privilegeArray = PrivilegeServiceLocal.SERVICE.findAll().toArray(new Privilege[0]);
        privilegeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(privilegeArray));
        privilegeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, Privilege>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, Privilege> event) {
                User editedUser = event.getRowValue();
                Privilege newPrivilege = event.getNewValue();
                editedUser.setPrivilege(newPrivilege);
                UserServiceLocal.USER_SERVICE.update(editedUser);
            }
        });


//        privilegeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(PrivilegeServiceLocal.SERVICE.findAll().toArray(new Privilege[0])));

        userTableView.getColumns().addAll(nameColumn, surnameColumn, usernameColumn, privilegeColumn);
        TableView.TableViewSelectionModel<User> selectionModel = userTableView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(this::onUserSelection);
    }


    private void onUserSelection(ObservableValue<? extends User> observableValue, User oldValue, User newValue){
        System.out.println("STARI USER: " + oldValue);
        System.out.println("NOVI USER: " + newValue);
        selectedUser = newValue;
        setupForm(newValue);
    }

    private void setupForm(User newUser) {
        usernameTextField.setText(newUser.getUsername());
        passwordField.setText(newUser.getPassword());
        nameTextField.setText(newUser.getName());
        surnameTextField.setText(newUser.getSurname());
        privilegeChoiceBox.getSelectionModel().select(newUser.getPrivilege());
    }
}
