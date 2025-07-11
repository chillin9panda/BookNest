package chillin9panda.booknest.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  private Long bookId;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "added_at", updatable = false)
  private LocalDateTime addedAt;

  @ManyToOne(optional = false)
  @JoinColumn(name = "added_by")
  private User addedBy;

  @Embedded
  private BookMetadata metadata;

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDateTime getAddedAt() {
    return addedAt;
  }

  @PrePersist
  public void onAdd() {
    this.addedAt = LocalDateTime.now();
  }

  public User getAddedBy() {
    return addedBy;
  }

  public void setAddedBy(User addedBy) {
    this.addedBy = addedBy;
  }

  public BookMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(BookMetadata metadata) {
    this.metadata = metadata;
  }

}
