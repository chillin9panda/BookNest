package chillin9panda.booknest.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Bookmark {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bookmark_id")
  private Long bookmarkId;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Status status;

  @Column(name = "reading_page")
  private int readingPage;

  @Column(name = "created_at")
  private LocalDateTime created_at;

  @Column(name = "updated_at")
  private LocalDateTime updated_at;

  public enum Status {
    Reading,
    Completed,
    Dropped,
    Planned
  }

  public Long getBookmarkId() {
    return bookmarkId;
  }

  public void setBookmarkId(Long bookmarkId) {
    this.bookmarkId = bookmarkId;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public int getReadingPage() {
    return readingPage;
  }

  public void setReadingPage(int readingPage) {
    this.readingPage = readingPage;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  @PrePersist
  public void onCreate() {
    this.created_at = LocalDateTime.now();
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public LocalDateTime getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(LocalDateTime updated_at) {
    this.updated_at = updated_at;
  }

}
