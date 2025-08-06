package chillin9panda.booknest.dto.response;

public class BookOverviewRespose {
  private Long bookId;
  private String thumbnailUrl;
  private String title;
  private String author;

  public BookOverviewRespose(Long bookId, String thumbnailUrl, String title, String author) {
    this.bookId = bookId;
    this.thumbnailUrl = thumbnailUrl;
    this.title = title;
    this.author = author;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
