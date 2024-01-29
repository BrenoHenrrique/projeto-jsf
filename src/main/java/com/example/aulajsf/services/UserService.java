package com.example.aulajsf.services;

import com.example.aulajsf.dto.UserDTO;
import com.example.aulajsf.entities.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();
    UserEntity findById(Long id);
    UserEntity authenticate(UserDTO params);
}
