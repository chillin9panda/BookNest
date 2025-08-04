package chillin9panda.files.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProcessUploads {

  private final StorageProperties storageProperties;

  public ProcessUploads(StorageProperties storageProperties) {
    this.storageProperties = storageProperties;
  }

  public String uploadBook(MultipartFile bookFile) {
    String bookFileName = bookFile.getOriginalFilename();

    if (null == bookFileName || bookFileName.isBlank()) {
      throw new IllegalArgumentException("Invalid file name!");
    }

    try {
      // clean up file name
      String fileName = Paths.get(bookFileName).getFileName().toString();

      Path destination = Paths.get(storageProperties.getBooksPath(), fileName);
      bookFile.transferTo(destination);

      return destination.toString();

    } catch (IOException e) {
      throw new IllegalStateException("Failed to save file: ", e);
    }
  }
}
