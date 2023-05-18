package com.hotelijerstvo.hoteli.event;

import com.hotelijerstvo.hoteli.user.login.CancelEvent;
import com.hotelijerstvo.hoteli.user.login.LoginEvent;
import com.hotelijerstvo.hoteli.user.logout.LogoutEvent;

public class EventBus {

    private final LoginEvent loginEvent = new LoginEvent();
    private final CancelEvent cancelEvent = new CancelEvent();
    private final LogoutEvent logoutEvent = new LogoutEvent();

    public EventBus(){

    }

    public LoginEvent getLoginEvent() {
        return loginEvent;
    }

    public LogoutEvent getLogoutEvent() {
        return logoutEvent;
    }

    public CancelEvent getCancelEvent() {
        return cancelEvent;
    }
}
