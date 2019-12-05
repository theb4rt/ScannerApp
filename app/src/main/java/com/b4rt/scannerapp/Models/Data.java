package com.b4rt.scannerapp.Models;

public class Data {
    private String token;
    private User user;
    private String path_output_wordpress;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPath_output_wordpress() {
        return path_output_wordpress;
    }

    public void setPath_output_wordpress(String path_output_wordpress) {
        this.path_output_wordpress = path_output_wordpress;
    }
}
