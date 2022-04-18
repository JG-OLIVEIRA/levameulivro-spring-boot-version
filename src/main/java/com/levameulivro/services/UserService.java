package com.levameulivro.services;

import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.models.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    User findUserById(Long userId);
    User createUser(UserRequestDTO userDTO);
    User updateUser(UserRequestDTO userDTO);
    void deleteUserById(Long userId);
}
