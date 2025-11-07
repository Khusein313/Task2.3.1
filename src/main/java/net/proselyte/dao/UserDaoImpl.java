package net.proselyte.dao;


import net.proselyte.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository

public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteById(Long id) {
//        String hql = "delete from User where id = :myId";
        entityManager.createQuery("delete from User where id = :myId").setParameter("myId",id).executeUpdate();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
}
