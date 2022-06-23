/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staxtapidemo;

/**
 *
 * @author loanbui
 */
public class User {
    private String uid;
    private String username;
    private String password;
    private String role;
    private String email;

    public User() {
    }

    public User(String uid, String username, String password, String role, String email) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "uid=" + uid + ", username=" + username + ", password=" + password + ", role=" + role + ", email=" + email + '}';
    }
    
    
}
