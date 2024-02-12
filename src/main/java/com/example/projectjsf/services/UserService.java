package com.example.projectjsf.services;

import com.example.projectjsf.dto.UserDTO;
import com.example.projectjsf.entities.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();
    UserEntity findById(Long id);
    UserEntity authenticate(UserDTO params);
    List<UserEntity> search(UserDTO params);
}
