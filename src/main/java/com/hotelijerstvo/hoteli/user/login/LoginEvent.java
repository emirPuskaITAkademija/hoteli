package com.hotelijerstvo.hoteli.user.login;

import com.hotelijerstvo.hoteli.Controller;
import com.hotelijerstvo.hoteli.gui.components.ProgressForm;
import com.hotelijerstvo.hoteli.user.User;
import com.hotelijerstvo.hoteli.user.admin.AdminBorderPane;
import com.hotelijerstvo.hoteli.user.privilege.Privilege;
import com.hotelijerstvo.hoteli.user.regular.UserBorderPane;
import com.hotelijerstvo.hoteli.user.service.UserServiceFactory;
import com.hotelijerstvo.hoteli.user.service.UserServiceLocal;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        ProgressForm progressForm = new ProgressForm();
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                for (int i = 0; i < 10; i++) {
                    updateProgress(i, 10);
                    Thread.sleep(200);
                }
                updateProgress(10, 10);
                return null ;
            }
        };
        progressForm.activateProgressBar(task);
        task.setOnSucceeded(event -> {
            progressForm.getDialogStage().close();
        });
        progressForm.getDialogStage().show();
        Thread thread = new Thread(task);
        thread.start();

        Controller controller = Controller.instance();
        LoginGridPane loginGridPane = controller.getLoginView();
        String usernameInput = loginGridPane.getUsernameTextField().getText();
        if (usernameInput == null || usernameInput.isBlank()) {
            Label messageLabel = loginGridPane.getMessageLabel();
            messageLabel.setText("Korisničko ime je prazno");
            return;
        }
        String passwordInput = loginGridPane.getPasswordField().getText();
        if (passwordInput == null || passwordInput.isBlank()) {
            Label messageLabel = loginGridPane.getMessageLabel();
            messageLabel.setText("Nije dozvoljen unos prazna lozinke");
            return;
        }
        UserServiceLocal userServiceLocal = UserServiceFactory.USER_SERVICE.get();
        User user = userServiceLocal.login(usernameInput, passwordInput);
        if (user == null) {
            Label messageLabel = loginGridPane.getMessageLabel();
            messageLabel.setText("Neispravna kombinacija korisničkog imena i lozinke");
            return;
        }
        controller.setLoggedUser(user);
        swapView();
    }

    private void swapView() {
        Controller controller = Controller.instance();
        User user = controller.getLoggedUser();
        Privilege privilege = user.getPrivilege();
        if("admin".equalsIgnoreCase(privilege.getName())){
            showAdminView();
        }else{
            showUserView();
        }
    }

    private void showAdminView(){
        Controller controller = Controller.instance();
        AdminBorderPane adminView = new AdminBorderPane();
        controller.setAdminView(adminView);
        controller.getMainStage().setTitle("Admin Panel");
        setupOnStage(adminView);
    }

    private void showUserView(){
        Controller controller = Controller.instance();
        UserBorderPane userView = new UserBorderPane();
        controller.setUserView(userView);
        controller.getMainStage().setTitle("User Panel");
        setupOnStage(userView);
    }

    private void setupOnStage(BorderPane borderPane){
        Controller controller = Controller.instance();
        Stage mainStage = controller.getMainStage();
        Scene scene = new Scene(borderPane, 700, 300);
        mainStage.setScene(scene);
    }
}
