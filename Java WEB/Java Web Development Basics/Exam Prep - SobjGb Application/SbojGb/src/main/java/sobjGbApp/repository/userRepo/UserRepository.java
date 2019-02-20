package sobjGbApp.repository.userRepo;

import sobjGbApp.domain.entities.User;
import sobjGbApp.repository.genericRepo.GenericRepository;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);

    Long size();
}
