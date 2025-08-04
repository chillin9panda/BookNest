package chillin9panda.booknest.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import chillin9panda.booknest.dto.request.UploadBookRequest;
import chillin9panda.booknest.dto.response.CustomResponse;
import chillin9panda.booknest.model.Book;
import chillin9panda.booknest.model.BookMetadata;
import chillin9panda.booknest.repository.BookRepository;
import chillin9panda.files.storage.ProcessUploads;
import chillin9panda.files.utils.FileOperations;
import chillin9panda.files.utils.SupportedFileTypes.BookExtension;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final ProcessUploads processUploads;
  private final UserService userService;

  public BookService(ProcessUploads processUploads, BookRepository bookRepository, UserService userService) {
    this.processUploads = processUploads;
    this.bookRepository = bookRepository;
    this.userService = userService;
  }

  @Transactional
  public CustomResponse uploadBook(UploadBookRequest request, String username) {

    String title = request.getTitle();
    String author = request.getAuthor();
    LocalDate publishedDate = request.getPublishedDate();
    MultipartFile bookFile = request.getBook();

    if (null == title || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }

    if (null == author || author.isEmpty()) {
      throw new IllegalArgumentException("Author cannot be empty");
    }

    if (null == bookFile || bookFile.isEmpty()) {
      throw new IllegalArgumentException("Need a book file to upload");
    }

    String fileExtension = FileOperations.getFileExtention(bookFile);
    boolean isSupportedExtension = Arrays.stream(BookExtension.values()).map(Enum::name)
        .anyMatch(fileExtension::equals);
    if (!isSupportedExtension) {
      throw new IllegalArgumentException("File type not supported!");
    }

    String path = processUploads.uploadBook(bookFile);

    BookMetadata bookMetadata = new BookMetadata();
    if (null != publishedDate) {
      bookMetadata.setPublishedDate(publishedDate);
    }
    bookMetadata.setAuthor(author);

    Book book = new Book();
    book.setMetadata(bookMetadata);
    book.setTitle(title);
    book.setPathToFile(path);
    book.setAddedBy(userService.getUserByUserName(username));
    bookRepository.save(book);

    CustomResponse response = new CustomResponse();
    response.setMessage("Book uploaded!");

    return response;
  }
}
