package chillin9panda.booknest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import chillin9panda.booknest.model.User;

public interface UserRepository extends JpaRepository<User, String> {
  boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);

}
