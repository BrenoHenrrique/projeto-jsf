package com.example.projectjsf.entities;

import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SuppressWarnings("all")
@Table(name = "company", schema = "public")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_seq", allocationSize = 1)
    private Long id;

    private String cnpj;

    private String email;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<WorkerEntity> workers = new ArrayList<>();
}
