package com.example.projectjsf.services;

import com.example.projectjsf.dto.UserDTO;
import com.example.projectjsf.entities.UserEntity;
import com.example.projectjsf.messages.UtilMessages;
import com.example.projectjsf.repositories.UserRepository;
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
            UtilMessages.warnningMessage("Usuários não encontrados.");
            throw new Exception("Usuários não encontrados.");
        }

        return entities;
    }

    @Override
    @SneakyThrows
    public UserEntity findById(Long id) {
        UserEntity entity = new UserRepository().findById(id);
        if (entity == null) {
            UtilMessages.errorMessage("Usuário não encontrado, tente novamente ou entre em contato com o adm.");
            throw new Exception("Usuário não encontrado");
        }

        return entity;
    }

    @Override
    @SneakyThrows
    public UserEntity authenticate(UserDTO params) {
        return new UserRepository().authenticate(params);
    }
}
