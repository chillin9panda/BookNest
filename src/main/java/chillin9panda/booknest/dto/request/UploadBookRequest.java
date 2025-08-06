package chillin9panda.booknest.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadBookRequest {
  private String title;
  private String author;
  private String publishedDate;
  private MultipartFile book;

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

  public String getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  public MultipartFile getBook() {
    return book;
  }

  public void setBook(MultipartFile book) {
    this.book = book;
  }
}
