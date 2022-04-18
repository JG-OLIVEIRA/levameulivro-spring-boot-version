package com.levameulivro.services.impl;

import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.models.User;
import com.levameulivro.repositories.UserRepository;
import com.levameulivro.services.UserService;
import com.levameulivro.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        findByEmail(userDTO);
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserRequestDTO userDTO){
        findByEmail(userDTO);
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);

    }

    public void findByEmail(UserRequestDTO userDTO){
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if(user.isPresent() && !user.get().getId().equals(userDTO.getId())){
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }

    @Override
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
}
