package com.levameulivro.controllers;

import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.dto.UserResponseDTO;
import com.levameulivro.models.User;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final String ID = "/{userId}";

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUser(){
        return ResponseEntity.ok().body(userServiceImp.findAllUser()
            .stream().map(UserResponseDTO::new).collect(Collectors.toList()));
    }

    @GetMapping(value = ID)
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId){
        try{
            User user = userServiceImp.findUserById(userId);
            return ResponseEntity.ok(new UserResponseDTO(user));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(userServiceImp.createUser(userDTO).getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    @Transactional
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid UserRequestDTO userDTO){
        userServiceImp.findUserById(userId);
        userDTO.setId(userId);
        try{
            User user = userServiceImp.updateUser(userDTO);
            return ResponseEntity.ok(new UserResponseDTO(user));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserResponseDTO> destroyUser(@PathVariable Long userId){
        try{
            userServiceImp.deleteUserById(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        
    }

}