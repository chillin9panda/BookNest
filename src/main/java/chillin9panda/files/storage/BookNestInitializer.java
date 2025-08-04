package chillin9panda.files.storage;

import java.io.File;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class BookNestInitializer {
  private final StorageProperties storageProperties;

  public BookNestInitializer(StorageProperties storageProperties) {
    this.storageProperties = storageProperties;
  }

  @PostConstruct
  public void initDirectories() {
    createDirectory(storageProperties.getRootPath());
    createDirectory(storageProperties.getBooksPath());
  }

  private void createDirectory(String path) {
    File dir = new File(path);
    if (!dir.exists() && dir.mkdirs()) {
      System.out.println("Created directory: " + path);
    }

  }
}
