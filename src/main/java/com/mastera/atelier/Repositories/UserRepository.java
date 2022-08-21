package com.mastera.atelier.Repositories;

import com.mastera.atelier.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    User findUserByUsername(String username);

    List<User> findUsersByRole(String role);

    List<User> findAll();
}
