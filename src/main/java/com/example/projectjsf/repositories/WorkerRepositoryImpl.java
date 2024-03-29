package com.example.projectjsf.repositories;

import com.example.projectjsf.entities.WorkerEntity;
import com.example.projectjsf.connection.HibernateConnector;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class WorkerRepositoryImpl implements WorkerRepository {
    private final Session session;

    public WorkerRepositoryImpl() {
        session = HibernateConnector.getSessionFactory().openSession();
    }

    public void destroy() {
        if (session != null) {
            session.clear();
            session.close();
        }
    }

    public List<WorkerEntity> findAll() {
        try {
            String sql = "from WorkerEntity w";
            TypedQuery<WorkerEntity> query = session.createQuery(sql, WorkerEntity.class);
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Error, try again.");
        } finally {
            destroy();
        }
    }

    public WorkerEntity findById(Long id) {
        try {
            String sql = "from WorkerEntity w where w.id = :id";
            TypedQuery<WorkerEntity> query = session.createQuery(sql, WorkerEntity.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error, try again.");
        } finally {
            destroy();
        }
    }
}
