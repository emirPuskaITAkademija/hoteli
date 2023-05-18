package com.hotelijerstvo.hoteli.user.admin;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminBorderPane extends BorderPane {
    private final ToggleButton userToggleButton = new ToggleButton("Korisnici");
    private UserAdminPanel userAdminPanel;
    private final ToggleButton roomToggleButton = new ToggleButton("Sobe");
    private RoomAdminPanel roomAdminPanel;
    private final Button logoutButton = new Button("Odjava");

    public AdminBorderPane(){
        userAdminPanel = new UserAdminPanel();
        setCenter(userAdminPanel);

        ToggleGroup toggleGroup = new ToggleGroup();
        userToggleButton.setToggleGroup(toggleGroup);
        userToggleButton.setSelected(true);
        roomToggleButton.setToggleGroup(toggleGroup);
        userToggleButton.setOnAction(this::setUserAdminPanel);
        roomToggleButton.setOnAction(this::setRoomAdminPanel);

        HBox mainMenuHBox = new HBox();
        mainMenuHBox.setSpacing(5);
        mainMenuHBox.setPadding(new Insets(10));
        mainMenuHBox.getChildren().addAll(userToggleButton, roomToggleButton);


        HBox logoutMenuHBox = new HBox();
        logoutMenuHBox.setAlignment(Pos.CENTER_RIGHT);
        logoutMenuHBox.setPadding(new Insets(10));
        logoutMenuHBox.getChildren().add(logoutButton);

        GridPane topMenuPane = new GridPane();
        topMenuPane.setHgap(300);
        topMenuPane.add(mainMenuHBox, 0, 0);
        topMenuPane.add(logoutMenuHBox, 1, 0);
        setTop(topMenuPane);
    }

    private void setRoomAdminPanel(ActionEvent actionEvent){
        roomAdminPanel = new RoomAdminPanel();
        setCenter(roomAdminPanel);
    }

    private void setUserAdminPanel(ActionEvent actionEvent){
        userAdminPanel = new UserAdminPanel();
        setCenter(userAdminPanel);
    }
}
