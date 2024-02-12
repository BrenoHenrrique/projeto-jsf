package com.example.projectjsf.repositories;

import com.example.projectjsf.dto.UserDTO;
import com.example.projectjsf.entities.UserEntity;
import com.example.projectjsf.connection.HibernateConnector;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    protected Session session;

    protected Session open() {
        return HibernateConnector.getSessionFactory().openSession();
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
            session = open();
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
            session = open();
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
            session = open();
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

    public List<UserEntity> search(UserDTO params) {
        List<UserEntity> user;
        try {
            session = open();
            StringBuilder sql = new StringBuilder("from UserEntity u ");
            if (params.getName() != null) {
                sql.append("where upper(u.name) like " + "'").append(params.getName().toUpperCase()).append("' ");
            }
            if (params.getEmail() != null) {
                if (sql.toString().contains("where")) {
                    sql.append("or upper(u.email) like " + "'").append(params.getEmail().toUpperCase()).append("' ");
                } else sql.append("where upper(u.email) like " + "'").append(params.getEmail().toUpperCase()).append("' ");
            }
            TypedQuery<UserEntity> query = session.createQuery(sql.toString(), UserEntity.class);
            user = query.getResultList();
        } finally {
            destroy();
        }

        return user;
    }
}
