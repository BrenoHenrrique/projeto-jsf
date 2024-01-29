package com.example.aulajsf.controllers;

import com.example.aulajsf.dto.UserDTO;
import com.example.aulajsf.entities.UserEntity;
import com.example.aulajsf.services.UserService;
import lombok.Getter;
import lombok.Setter;

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

    public List<UserEntity> index() {
        return service.findAll();
    }

    public UserEntity get() {
        return service.findById(params.getId());
    }

    public UserEntity authenticate() {
        UserEntity user = service.authenticate(params);
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Usuário ou senha inválidos"));
            return null;
        } else {
            return user;
        }
    }

}