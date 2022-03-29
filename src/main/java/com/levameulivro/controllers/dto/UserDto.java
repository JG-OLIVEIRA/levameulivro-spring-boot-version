package com.levameulivro.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.levameulivro.models.User;

public class UserDto {
    
    private Long id;
    private String name;
    private String email;

    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<UserDto> convert(List<User> user) {
        return user.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
