package chillin9panda.booknest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chillin9panda.booknest.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
