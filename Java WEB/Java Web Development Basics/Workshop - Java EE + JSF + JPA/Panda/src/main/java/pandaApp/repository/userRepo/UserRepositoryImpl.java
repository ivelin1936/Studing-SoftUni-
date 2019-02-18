package pandaApp.repository.userRepo;

import pandaApp.domain.entities.User;
import pandaApp.repository.genericRepo.GenericRepositoryImpl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserRepositoryImpl extends GenericRepositoryImpl<User, String> implements UserRepository {

    private EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUsername(String username) {
        try {
            User user = this.entityManager
                    .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            return user;
        } catch (Exception ex) {
            //LOG here...
            return null;
        }
    }

    @Override
    public Long size() {
        try {
            return this.entityManager
                    .createQuery("SELECT count(u) FROM User u", Long.class)
                    .getSingleResult();
        } catch (Exception ex) {
            //LOG Here...
            return Long.MIN_VALUE;
        }
    }
}
