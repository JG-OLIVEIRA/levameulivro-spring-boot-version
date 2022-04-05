package com.levameulivro.controllers;

import com.levameulivro.models.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.levameulivro.controllers.dto.UserRequestDTO;
import com.levameulivro.controllers.dto.UserResponseDTO;
import com.levameulivro.services.impl.UserServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        List<User> users = userServiceImp.findAllUser();
        return users.stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId){
        Optional<User> optional = userServiceImp.findUserById(userId);
        if(optional.isPresent()){
            return ResponseEntity.ok(new UserResponseDTO(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserRequestDTO> addUser(@RequestBody @Valid UserRequestDTO userDTO, UriComponentsBuilder uriBuilder){
        User user = userServiceImp.createUser(userDTO);

        URI uri = uriBuilder.path("/users/{userId}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserRequestDTO(user));
    }

    @PutMapping("/{userId}")
    @Transactional
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid UserRequestDTO userDTO){
        Optional<User> optional = userServiceImp.findUserById(userId);
        if(optional.isPresent()){
            User user = userServiceImp.updateUser(userId, userDTO);
            return ResponseEntity.ok(new UserResponseDTO(user));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserRequestDTO> destroyUser(@PathVariable Long userId){
        Optional<User> optional = userServiceImp.findUserById(userId);
        if(optional.isPresent()){
            userServiceImp.deleteUserById(userId);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }

}