package com.example.projectjsf.controllers;

import com.example.projectjsf.dto.UserDTO;
import com.example.projectjsf.entities.UserEntity;
import com.example.projectjsf.services.UserService;
import com.example.projectjsf.utils.RedirectUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@ViewScoped
@Named(value = "user")
public class UserController implements Serializable {

    @Inject
    private UserService service;

    @Getter
    @Setter
    private List<UserDTO> entities;

    @Getter
    @Setter
    private UserDTO params;

    @PostConstruct
    public void postContruct() {
        params = new UserDTO();
        entities = this.index();
    }

    public List<UserDTO> index() {
        List<UserEntity> entities = service.findAll();
        List<UserDTO> dtos = entities.stream().map(UserDTO::new).collect(Collectors.toList());
        return dtos;
    }

    public List<UserDTO> search() {
        List<UserEntity> values = service.search(params);
        entities = values.stream().map(UserDTO::new).collect(Collectors.toList());
        return entities;
    }

    public UserDTO get() {
        UserEntity user = service.findById(params.getId());
        return new UserDTO(user);
    }

    public UserDTO authenticate() {
        UserEntity user = service.authenticate(params);
        RedirectUtils.redirectTo("/pages/DashboardPage.xhtml");
        return new UserDTO(user);
    }
}