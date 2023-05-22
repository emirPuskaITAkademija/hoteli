package com.hotelijerstvo.hoteli.user.logout;

import com.hotelijerstvo.hoteli.Controller;
import com.hotelijerstvo.hoteli.user.login.LoginGridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class LogoutEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        Controller controller = Controller.instance();
        controller.setLoggedUser(null);
        LoginGridPane loginGridPane = new LoginGridPane();
        controller.setLoginView(loginGridPane);
        controller.getMainStage().setScene(new Scene(loginGridPane, 850, 180));
    }
}
