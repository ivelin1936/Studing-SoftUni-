package org.softuni.examApp.repository.userRepo;

import org.softuni.examApp.domain.entities.User;
import org.softuni.examApp.repository.genericRepo.GenericRepositoryImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepositoryImpl extends GenericRepositoryImpl<User, String> implements UserRepository {

    private static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public User findByUsername(String username) {
        try {
            User user = super.entityManager
                    .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            return user;
        } catch (Exception ex) {
            //LOG here...
            logger().log(Level.SEVERE, "Invalid arguments for find User entity provided: " + username, ex);
            return null;
        }
    }

}
