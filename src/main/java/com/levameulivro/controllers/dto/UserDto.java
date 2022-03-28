package com.levameulivro.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.levameulivro.models.User;

public class UserDto {

    private String username;
    private String email;

    public UserDto(User user){
        this.username = user.getUsername();
        this.email = user.getUsername();
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

    public static List<UserDto> converter(List<User> user) {
        return user.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
