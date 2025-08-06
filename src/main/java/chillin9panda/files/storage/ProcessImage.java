package chillin9panda.files.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import chillin9panda.files.utils.FileOperations;

@Service
public class ProcessImage {

  private final StorageProperties storageProperties;

  public ProcessImage(StorageProperties storageProperties) {
    this.storageProperties = storageProperties;
  }

  public MultipartFile downloadImage(String link) {
    try {
      URL url = new URI(link).toURL();
      InputStream inputStream = url.openStream();

      String fileName = "thumbnail.jpg";
      String contentType = "image/jpeg";

      return new MockMultipartFile(fileName, fileName, contentType, inputStream);
    } catch (Exception e) {
      throw new RuntimeException("Failed to download image:", e);
    }
  }

  /**
   * @return pathtocoverimage
   */
  public String saveBookImage(MultipartFile image, Long bookId) {
    if (null == image || image.isEmpty()) {
      return null;
    }

    try {
      String extension = FileOperations.getFileExtention(image);
      String fileName = bookId + "_thumbnail." + extension;

      Path path = Paths.get(storageProperties.getThumbnailsPath(), fileName);
      image.transferTo(path);

      return path.toString();
    } catch (IOException e) {
      throw new IllegalStateException("Failed save book image: ", e);
    }
  }
}
