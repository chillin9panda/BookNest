package chillin9panda.files.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "booknest.storage")
public class StorageProperties {
  private String rootPath;
  private String booksPath;
  private String thumbnailsPath;

  public String getRootPath() {
    return rootPath;
  }

  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  public String getBooksPath() {
    return booksPath;
  }

  public void setBooksPath(String booksPath) {
    this.booksPath = booksPath;
  }

  public String getThumbnailsPath() {
    return thumbnailsPath;
  }

  public void setThumbnailsPath(String thumbnailsPath) {
    this.thumbnailsPath = thumbnailsPath;
  }

}
