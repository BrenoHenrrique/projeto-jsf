package com.example.aulajsf.services;

import com.example.aulajsf.dto.UserDTO;
import com.example.aulajsf.entities.UserEntity;
import com.example.aulajsf.repositories.UserRepository;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.List;

public class UserServiceImpl implements UserService, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    @SneakyThrows
    public List<UserEntity> findAll() {
        List<UserEntity> entities = new UserRepository().findAll();
        if (entities == null || entities.isEmpty()) {
            throw new Exception("Not found registries.");
        }

        return entities;
    }

    @Override
    @SneakyThrows
    public UserEntity findById(Long id) {
        UserEntity entity = new UserRepository().findById(id);
        if (entity == null) {
            throw new Exception("Not found registry.");
        }

        return entity;
    }

    @Override
    @SneakyThrows
    public UserEntity authenticate(UserDTO params) {
        return new UserRepository().authenticate(params);
    }
}
