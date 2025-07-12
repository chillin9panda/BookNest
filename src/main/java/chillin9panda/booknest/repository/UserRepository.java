package chillin9panda.booknest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chillin9panda.booknest.model.User;

public interface UserRepository extends JpaRepository<User, String> {
  boolean existsByUsername(String username);

}
