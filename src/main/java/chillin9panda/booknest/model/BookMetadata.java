package chillin9panda.booknest.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BookMetadata {

  @Column(name = "external_id")
  private String externalId;

  @Column(name = "author")
  private String author;

  @Column(name = "publisher")
  private String publisher;

  @Column(name = "isbn")
  private String isbn;

  @Column(name = "cover_image_path")
  private String coverImagePath;

  @Column(name = "description")
  private String description;

  @Column(name = "page_count")
  private int pageCount;

  @Column(name = "published_date")
  private LocalDate publishedDate;

  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getCoverImagePath() {
    return coverImagePath;
  }

  public void setCoverImagePath(String coverImagePath) {
    this.coverImagePath = coverImagePath;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public LocalDate getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(LocalDate publishedDate) {
    this.publishedDate = publishedDate;
  }

}
