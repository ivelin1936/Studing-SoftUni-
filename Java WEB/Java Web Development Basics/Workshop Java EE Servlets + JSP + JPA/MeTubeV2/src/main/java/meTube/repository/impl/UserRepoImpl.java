package meTube.repository.impl;

import meTube.domain.entities.User;
import meTube.domain.models.service.UserServiceModel;
import meTube.repository.interfaces.UserRepo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    private final EntityManager entityManager;

    @Inject
    public UserRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User findById(String id) {
        User user = (User) this.entityManager
                .createQuery("SELECT u FROM users u WHERE u.id = :id")
                .setParameter("id", id);

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = this.entityManager
                .createQuery("SELECT u FROM users u", User.class)
                .getResultList();

        return users;
    }

    @Override
    public User findByName(String username) {
        List<User> users = this.entityManager
                .createQuery("SELECT u FROM users u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        if (users.size() == 0) {
            return null;
        }

        return users.get(0);
    }

    @Override
    public User findUser(String username, String sha256hexPassword) {
        List<User> users = this.entityManager
                .createQuery("SELECT u from users u " +
                        "WHERE u.username = :username AND u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", sha256hexPassword)
                .getResultList();

        if (users.size() == 0) {
            return null;
        }

        return users.get(0);
    }
}
