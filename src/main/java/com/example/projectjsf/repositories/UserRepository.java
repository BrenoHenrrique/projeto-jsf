package com.example.projectjsf.repositories;

import com.example.projectjsf.dto.UserDTO;
import com.example.projectjsf.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

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
        List<UserEntity> users;
        try {
            String sql = "from UserEntity u";
            TypedQuery<UserEntity> query = session.createQuery(sql, UserEntity.class);
            users = new ArrayList<>(query.getResultList());
        } finally {
            destroy();
        }

        return users;
    }

    public UserEntity findById(Long id) {
        UserEntity user;
        try {
            String sql = "from UserEntity u where u.id = :id";
            TypedQuery<UserEntity> query = session.createQuery(sql, UserEntity.class);
            query.setParameter("id", id);
            user = query.getSingleResult();
        } finally {
            destroy();
        }

        return user;
    }

    public UserEntity authenticate(UserDTO params) {
        UserEntity user;
        try {
            String sql = "from UserEntity u where u.email = :email and u.password = :password";
            TypedQuery<UserEntity> query = session.createQuery(sql, UserEntity.class);
            query.setParameter("email", params.getEmail());
            query.setParameter("password", params.getPassword());
            user = query.getSingleResult();
        } finally {
            destroy();
        }

        return user;
    }
}
