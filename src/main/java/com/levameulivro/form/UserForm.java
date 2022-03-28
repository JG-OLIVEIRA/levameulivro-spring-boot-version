package com.levameulivro.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.levameulivro.models.User;

import org.hibernate.validator.constraints.Length;

public class UserForm {
    
    @NotNull @NotEmpty @Length(min = 5)
    private String username;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty @Length(min = 5)
    private String password;

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

    public User converter() {
        return new User(username, email, password);
    }
}
