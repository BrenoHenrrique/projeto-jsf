package com.example.aulajsf.entities;

import javax.persistence.*;
import lombok.Data;

@SuppressWarnings("all")
@Data
@Entity
@Table(name = "user", schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;
    private String cpf;
    private String name;
    private String email;
    private String password;
}
