package users.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.system.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
