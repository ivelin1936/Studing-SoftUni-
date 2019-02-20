package sobjGbApp.repository.userRepo;

import sobjGbApp.domain.entities.User;
import sobjGbApp.repository.genericRepo.GenericRepositoryImpl;

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

    @Override
    public Long size() {
        try {
            return super.entityManager
                    .createQuery("SELECT count(u) FROM User u", Long.class)
                    .getSingleResult();
        } catch (Exception ex) {
            //LOG Here...
            logger().log(Level.SEVERE, "Failed to get Users count: " + ex);
            return Long.MIN_VALUE;
        }
    }
}
