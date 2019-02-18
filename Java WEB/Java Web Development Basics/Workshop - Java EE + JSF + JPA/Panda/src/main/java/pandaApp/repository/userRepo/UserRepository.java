package pandaApp.repository.userRepo;

import pandaApp.domain.entities.User;
import pandaApp.repository.genericRepo.GenericRepository;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);

    Long size();
}
