package com.levameulivro.controllers;

import com.levameulivro.models.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.levameulivro.controllers.dto.UserDetailDto;
import com.levameulivro.controllers.dto.UserDto;
import com.levameulivro.form.UserForm;
import com.levameulivro.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<UserDto> lista(){
        List<User> users = userRepository.findAll();
        return UserDto.converter(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailDto> detalhar(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(new UserDetailDto(user.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> cadastrar(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder){
        User user = form.converter();
        userRepository.save(user);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

}