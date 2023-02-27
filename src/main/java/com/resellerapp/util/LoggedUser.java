package com.resellerapp.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@Component
@SessionScope
public class LoggedUser {

    private String username;
    private Long id;

    public boolean isLogged() {
        return this.username != null && this.id != null;
    }
}
