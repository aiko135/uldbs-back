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
public class RegisterResult {
    private Boolean result;
    private String message;
    private User createdAccount;

    public RegisterResult() {
    }

    public RegisterResult(Boolean result, String message, User createdAccount) {
        this.result = result;
        this.message = message;
        this.createdAccount = createdAccount;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getCreatedAccount() {
        return createdAccount;
    }

    public void setCreatedAccount(User createdAccount) {
        this.createdAccount = createdAccount;
    }
}
