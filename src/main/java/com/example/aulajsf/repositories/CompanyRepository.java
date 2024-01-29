package com.example.aulajsf.repositories;

import com.example.aulajsf.entities.CompanyEntity;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final Session session;

    public CompanyRepository() {
        session = HibernateConnector.getSessionFactory().openSession();
    }

    public void destroy() {
        if (session != null) {
            session.clear();
            session.close();
        }
    }

    public List<CompanyEntity> findAll() {
        try {
            String sql = "from CompanyEntity c";
            TypedQuery<CompanyEntity> query = session.createQuery(sql, CompanyEntity.class);
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Error, try again.");
        } finally {
            destroy();
        }
    }

    public CompanyEntity findById(Long id) {
        try {
            String sql = "from CompanyEntity c where c.id = :id";
            TypedQuery<CompanyEntity> query = session.createQuery(sql, CompanyEntity.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error, try again.");
        } finally {
            destroy();
        }
    }
}