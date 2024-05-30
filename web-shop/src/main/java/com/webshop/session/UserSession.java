package com.webshop.session;

import com.webshop.model.User;

/**
 * UserSession
 */
public class UserSession {

    private Long id;

    private String role;

    public UserSession(User user) {
        this.id = user.getId();
        this.role = user.getUserRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
