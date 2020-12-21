/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.dto;

import com.penzasoft.uldbs.model.User;

/**
 *
 * @author ktepin
 */
public class LoginResult {
    
    private Boolean result;
    private String token;
    private User user;

    public LoginResult(Boolean result, String token, User user) {
        this.result = result;
        this.token = token;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
