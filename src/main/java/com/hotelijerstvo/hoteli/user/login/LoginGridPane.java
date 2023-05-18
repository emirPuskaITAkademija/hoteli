package com.hotelijerstvo.hoteli.user.login;

import com.hotelijerstvo.hoteli.Controller;
import com.hotelijerstvo.hoteli.event.EventBus;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class LoginGridPane extends GridPane {
    private final Label usernameLabel = new Label("Korisničko ime:");
    private final Label passwordLabel = new Label("Lozinka:");
    private final TextField usernameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button loginButton = new Button("Prijava");
    private final Button cancelButton = new Button("Odustani");
    /**
     * Ovdje inicijalno neće biti ništa zapisano.
     * Nju ili njen sadržaj ćemo puniti dinamički.
     */
    private final Label messageLabel = new Label();

    public LoginGridPane(){
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        setAlignment(Pos.CENTER);

        add(usernameLabel, 0, 0);
        add(usernameTextField, 1, 0);
        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);
        FlowPane buttonFlowPane = new FlowPane();
        buttonFlowPane.setAlignment(Pos.CENTER_RIGHT);
        buttonFlowPane.getChildren().addAll(loginButton, cancelButton);
        add(buttonFlowPane, 1, 2);
        add(messageLabel, 1, 3);

        EventBus eventBus = Controller.instance().getEventBus();
        loginButton.setOnAction(eventBus.getLoginEvent());
        cancelButton.setOnAction(eventBus.getCancelEvent());
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }
}
