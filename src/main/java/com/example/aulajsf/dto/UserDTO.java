package com.example.aulajsf.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String cpf;
    private String name;
    private String email;
    private String password;
}
