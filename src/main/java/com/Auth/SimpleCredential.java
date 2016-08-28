package com.Auth;

/**
 * Created by anilkumar.r on 06/08/16.
 */
public class SimpleCredential {
    private String password;

    public SimpleCredential(String password) {
        super();

        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
