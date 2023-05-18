package com.hotelijerstvo.hoteli;

import com.hotelijerstvo.hoteli.event.EventBus;
import com.hotelijerstvo.hoteli.user.User;
import com.hotelijerstvo.hoteli.user.admin.AdminBorderPane;
import com.hotelijerstvo.hoteli.user.login.LoginGridPane;
import com.hotelijerstvo.hoteli.user.regular.UserBorderPane;
import javafx.stage.Stage;

/**
 * On će biti spona između:
 * <li>1. VIEW</li>
 * <li>2. MODEL </li>
 */
public class Controller {

    private static Controller INSTANCE = null;

    private LoginGridPane loginView;
    private UserBorderPane userView;
    private AdminBorderPane adminView;

    private Stage mainStage;

    private User loggedUser;

    private final EventBus eventBus = new EventBus();

    private Controller(){
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setLoginView(LoginGridPane loginView) {
        this.loginView = loginView;
    }

    public LoginGridPane getLoginView() {
        return loginView;
    }

    public void setAdminView(AdminBorderPane adminView) {
        this.adminView = adminView;
    }

    public AdminBorderPane getAdminView() {
        return adminView;
    }

    public void setUserView(UserBorderPane userView) {
        this.userView = userView;
    }

    public UserBorderPane getUserView() {
        return userView;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public static Controller instance(){
        if(INSTANCE == null){
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

}
