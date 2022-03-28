package com.levameulivro.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.levameulivro.models.User;
import com.levameulivro.repository.UserRepository;

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

    public User convert() {
        return new User(username, email, password);
    }

    public User update(Long id, UserRepository userRepository){
        User user = userRepository.getById(id);
        user.setUsername(this.username);
        user.setEmail(email);
        user.setPassword(this.email);

        return user;
    }
}
