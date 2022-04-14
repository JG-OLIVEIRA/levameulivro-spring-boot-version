package com.levameulivro.services.impl;

import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.models.User;
import com.levameulivro.repositories.UserRepository;
import com.levameulivro.services.UserService;
import com.levameulivro.services.exceptions.ObjectNotFoundException;

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
    public User findUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
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
    public User updateUser(Long userId, UserRequestDTO userDTO){
        Optional<User> optional = userRepository.findById(userId);
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
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
}
