package com.example.projectjsf.repositories;

import com.example.projectjsf.entities.CompanyEntity;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public interface CompanyRepository {
    List<CompanyEntity> findAll();
    CompanyEntity findById(Long id);
}
