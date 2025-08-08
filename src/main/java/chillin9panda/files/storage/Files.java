package chillin9panda.files.storage;

import java.io.File;
import org.springframework.stereotype.Service;

@Service
public class Files {

  public void deleteFile(String path) {
    File file = new File(path);

    if (file.exists()) {
      file.delete();
    }
  }

}
