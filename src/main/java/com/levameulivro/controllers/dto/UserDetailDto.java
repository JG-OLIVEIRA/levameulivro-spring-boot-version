package com.levameulivro.controllers.dto;

import com.levameulivro.models.User;

public class UserDetailDto {
    
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserDetailDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public Long getId(){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
