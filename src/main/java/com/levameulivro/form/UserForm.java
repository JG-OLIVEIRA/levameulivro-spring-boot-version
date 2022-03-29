package com.levameulivro.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.levameulivro.models.User;
import com.levameulivro.repository.UserRepository;

import org.hibernate.validator.constraints.Length;

public class UserForm {
    
    @NotNull @NotEmpty @Length(min = 5)
    private String name;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty @Length(min = 8)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
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
        return new User(name, email, password);
    }

    public User update(Long id, UserRepository userRepository){
        User user = userRepository.getById(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
