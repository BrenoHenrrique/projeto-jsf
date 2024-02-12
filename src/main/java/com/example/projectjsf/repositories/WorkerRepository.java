package com.example.projectjsf.repositories;

import com.example.projectjsf.entities.WorkerEntity;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public interface WorkerRepository {
    List<WorkerEntity> findAll();

    WorkerEntity findById(Long id);
}
