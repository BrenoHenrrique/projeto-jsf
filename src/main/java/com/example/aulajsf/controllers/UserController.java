package com.example.aulajsf.controllers;

import com.example.aulajsf.dto.UserDTO;
import com.example.aulajsf.entities.UserEntity;
import com.example.aulajsf.messages.UtilMessages;
import com.example.aulajsf.services.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("all")
@ViewScoped
@Named(value = "user")
public class UserController implements Serializable {

    @Inject
    private UserService service;

    @Getter
    @Setter
    private UserDTO params = new UserDTO();

    @Getter
    @Setter
    private UserDTO user = new UserDTO();

    public List<UserEntity> index() {
        return service.findAll();
    }

    public UserEntity get() {
        return service.findById(params.getId());
    }

    public void authenticate() {
        try {
            UserEntity entity = service.authenticate(params);
            if (entity == null) {
                UtilMessages.warningMessage("Usuário ou senha inválidos");
            } else {
                user = new UserDTO(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}