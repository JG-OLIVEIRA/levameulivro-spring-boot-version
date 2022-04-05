package com.levameulivro.services;

import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser();
    Optional<User> findUserById(Long userId);
    User createUser(UserRequestDTO userDTO);
    User updateUser(Long userId, UserRequestDTO userDTO);
    void deleteUserById(Long userId);
}
