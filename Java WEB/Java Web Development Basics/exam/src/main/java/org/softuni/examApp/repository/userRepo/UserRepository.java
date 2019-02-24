package org.softuni.examApp.repository.userRepo;

import org.softuni.examApp.domain.entities.User;
import org.softuni.examApp.repository.genericRepo.GenericRepository;


public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);
}
