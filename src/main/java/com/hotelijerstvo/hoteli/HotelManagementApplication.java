package com.hotelijerstvo.hoteli;

import com.hotelijerstvo.hoteli.user.login.LoginGridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HotelManagementApplication extends Application {
    @Override
    public void start(Stage stage) {
        LoginGridPane loginGridPane = new LoginGridPane();
       // loginGridPane.setGridLinesVisible(true);
        Controller.instance().setLoginView(loginGridPane);
        Controller.instance().setMainStage(stage);

        Scene scene = new Scene(loginGridPane, 650, 180);
        stage.setTitle("Login Screen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}