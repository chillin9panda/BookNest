package chillin9panda.booknest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import chillin9panda.booknest.model.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
  Optional<Bookmark> findByBookmarkId(Long bookmarkId);
}
