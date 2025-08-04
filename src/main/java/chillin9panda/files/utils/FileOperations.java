package chillin9panda.files.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileOperations {

  public static String getFileExtention(MultipartFile file) {
    String fileName = file.getOriginalFilename();

    if (null != fileName && fileName.contains(".")) {
      return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }
    return "";
  }

}
