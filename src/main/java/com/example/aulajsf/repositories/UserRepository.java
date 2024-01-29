package com.example.aulajsf.repositories;

import com.example.aulajsf.dto.UserDTO;
import com.example.aulajsf.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserRepository {

    private final Session session;

    public UserRepository() {
        session = HibernateConnector.getSessionFactory().openSession();
    }

    public void destroy() {
        if (session != null) {
            session.clear();
            session.close();
        }
    }

    public List<UserEntity> findAll() {
        try {
            String sql = "from UserEntity u";
            TypedQuery<UserEntity> query = session.createQuery(sql, UserEntity.class);
            return new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Error, try again.");
        } finally {
            destroy();
        }
    }

    public UserEntity findById(Long id) {
        try {
            String sql = "from UserEntity u where u.id = :id";
            TypedQuery<UserEntity> query = session.createQuery(sql, UserEntity.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error, try again.");
        } finally {
            destroy();
        }
    }

    public UserEntity authenticate(UserDTO params) {
        try {
            String sql = "from UserEntity u where u.email = :email and u.password = :password";
            TypedQuery<UserEntity> query = session.createQuery(sql, UserEntity.class);
            query.setParameter("email", params.getEmail());
            query.setParameter("password", params.getPassword());
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar o usuário.");
        } finally {
            destroy();
        }
    }
}
