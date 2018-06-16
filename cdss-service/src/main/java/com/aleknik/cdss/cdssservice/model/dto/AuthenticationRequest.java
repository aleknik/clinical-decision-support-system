package com.aleknik.cdss.cdssservice.model.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Represents user's authentication request.
 * Used when user tries to log in to the system.
 */
public class AuthenticationRequest {

    /**
     * User's username.
     */
    @NotEmpty
    private String username;

    /**
     * User's password.
     */
    @NotEmpty
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
