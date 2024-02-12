package com.example.projectjsf.services;

import com.example.projectjsf.dto.UserDTO;
import com.example.projectjsf.entities.UserEntity;
import com.example.projectjsf.messages.UtilMessages;
import com.example.projectjsf.repositories.UserRepository;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
public class UserServiceImpl implements UserService, Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserRepository repository;

    @Override
    @SneakyThrows
    public List<UserEntity> findAll() {
        List<UserEntity> entities = repository.findAll();
        if (entities == null || entities.isEmpty()) {
            UtilMessages.warnningMessage("Usuários não encontrados.");
            throw new Exception("Usuários não encontrados.");
        }

        return entities;
    }

    @Override
    @SneakyThrows
    public UserEntity findById(Long id) {
        UserEntity entity = repository.findById(id);
        if (entity == null) {
            UtilMessages.errorMessage("Usuário não encontrado, tente novamente ou entre em contato com o adm.");
            throw new Exception("Usuário não encontrado");
        }

        return entity;
    }

    @Override
    @SneakyThrows
    public UserEntity authenticate(UserDTO params) {
        return repository.authenticate(params);
    }

    @Override
    @SneakyThrows
    public List<UserEntity> search(UserDTO params) {
        List<UserEntity> entities = repository.search(params);
        if (entities == null || entities.isEmpty()) {
            UtilMessages.warnningMessage("Usuários não encontrados.");
            throw new Exception("Usuários não encontrados.");
        }

        return entities;
    }
}
