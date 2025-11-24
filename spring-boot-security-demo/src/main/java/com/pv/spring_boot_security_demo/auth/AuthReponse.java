package com.pv.spring_boot_security_demo.auth;

public class AuthReponse {
    public String token;

    public AuthReponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
