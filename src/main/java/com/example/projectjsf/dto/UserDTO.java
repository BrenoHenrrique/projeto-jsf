package com.example.projectjsf.dto;

import com.example.projectjsf.entities.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String cpf;
    private String name;
    private String email;
    private String password;

    public UserDTO(UserEntity entity) {
        id = entity.getId();
        cpf = entity.getCpf();
        name = entity.getName();
        email = entity.getEmail();
        password = entity.getPassword();
    }

    public void setName(String name) {
        this.name = name;
    }
}
