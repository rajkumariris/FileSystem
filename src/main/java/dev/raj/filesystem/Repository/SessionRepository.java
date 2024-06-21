package dev.raj.filesystem.Repository;

import dev.raj.filesystem.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Session  save(Session session);

    Session   findSessionByUserIdAndToken(Long userId, String token);
}
