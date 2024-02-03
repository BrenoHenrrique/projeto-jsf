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
    private UserDTO params;

    @Getter
    @Setter
    private UserDTO user;

    @PostConstruct
    public void postContruct() {
        params = new UserDTO();
    }

    public List<UserDTO> index() {
        return service.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
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