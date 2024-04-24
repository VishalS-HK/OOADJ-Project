//package com.jtspringproject.JtSpringProject.models;

public class User implements Cloneable {
    private String username;
    private String password;
    private String email;
    private String role;

    // Constructor, getters, and setters
    
    @Override
    public User clone() {
        try {
            User clone = (User) super.clone();
            // Optionally clone deeply nested objects here if necessary
            return clone;
        } catch (CloneNotSupportedException e) {
            // This shouldn't happen since we are Cloneable
            throw new RuntimeException("User cloning failed", e);
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
