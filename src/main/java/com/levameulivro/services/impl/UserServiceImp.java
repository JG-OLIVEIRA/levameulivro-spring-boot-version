package com.levameulivro.services.impl;

import com.levameulivro.controllers.dto.UserRequestDTO;
import com.levameulivro.models.User;
import com.levameulivro.repository.UserRepository;
import com.levameulivro.services.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public User createUser(UserRequestDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserRequestDTO userDTO){
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            User user = optional.get();
            user.setEmail(userDTO.getEmail());
            user.setName(userDTO.getName());
            user.setPassword(userDTO.getPassword());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}
