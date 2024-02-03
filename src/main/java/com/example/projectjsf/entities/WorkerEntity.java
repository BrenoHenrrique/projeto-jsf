package com.example.projectjsf.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SuppressWarnings("all")
@Table(name = "worker", schema = "public")
public class WorkerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "worker_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}
