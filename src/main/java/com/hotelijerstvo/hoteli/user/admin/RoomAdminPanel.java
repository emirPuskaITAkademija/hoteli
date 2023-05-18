package com.hotelijerstvo.hoteli.user.admin;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RoomAdminPanel extends VBox {
    private final Label titleLabel = new Label("Administracija soba");

    public RoomAdminPanel(){
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10));

        getChildren().addAll(titleLabel);
    }
}
