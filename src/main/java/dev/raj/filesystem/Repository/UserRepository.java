package dev.raj.filesystem.Repository;

import dev.raj.filesystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        User save(User user);

        User findByEmailAndPassword(String email, String password);

}
