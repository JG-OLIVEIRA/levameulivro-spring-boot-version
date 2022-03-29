package com.levameulivro.controllers;

import com.levameulivro.models.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.levameulivro.controllers.dto.UserDetailDto;
import com.levameulivro.controllers.dto.UserDto;
import com.levameulivro.form.UserForm;
import com.levameulivro.repository.UserRepository;

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
    private UserRepository userRepository;


    @GetMapping
    public List<UserDto> show(){
        List<User> users = userRepository.findAll();
        return UserDto.convert(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailDto> detail(@PathVariable Long id){
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(new UserDetailDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder){
        User user = form.convert();
        userRepository.save(user);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserForm form){
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            User user = form.update(id, userRepository);
            return ResponseEntity.ok(new UserDto(user));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> destroy(@PathVariable Long id){
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }

}