package chillin9panda.booknest.service;

import java.util.Arrays;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import chillin9panda.booknest.dto.request.UploadBookRequest;
import chillin9panda.booknest.dto.response.CustomResponse;
import chillin9panda.booknest.model.Book;
import chillin9panda.booknest.model.BookMetadata;
import chillin9panda.booknest.repository.BookRepository;
import chillin9panda.files.books.BookMetadatas;
import chillin9panda.files.books.GoogleBooksAPI;
import chillin9panda.files.books.PDFMetadataIdentifier;
import chillin9panda.files.storage.ProcessImage;
import chillin9panda.files.storage.ProcessUploads;
import chillin9panda.files.utils.FileOperations;
import chillin9panda.files.utils.SupportedFileTypes.BookExtension;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final ProcessUploads processUploads;
  private final UserService userService;
  private final ProcessImage processImage;

  public BookService(ProcessUploads processUploads, BookRepository bookRepository, UserService userService,
      ProcessImage processImage) {
    this.processUploads = processUploads;
    this.bookRepository = bookRepository;
    this.userService = userService;
    this.processImage = processImage;
  }

  @Transactional
  public CustomResponse uploadBook(UploadBookRequest request, String username) {

    String title = request.getTitle();
    String author = request.getAuthor();
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
    bookMetadata.setAuthor(author);

    Book book = new Book();
    book.setMetadata(bookMetadata);
    book.setTitle(title);
    book.setPathToFile(path);
    book.setAddedBy(userService.getUserByUserName(username));
    bookRepository.save(book);

    identifyBook(book);

    CustomResponse response = new CustomResponse();
    response.setMessage("Book uploaded!");

    return response;
  }

  @Transactional
  public void identifyBook(Book book) {
    BookMetadatas bookInfo = PDFMetadataIdentifier.getPDFMetadata(book.getPathToFile());
    JSONObject result = GoogleBooksAPI.searchBook(bookInfo.getTitle(), bookInfo.getAuthor());
    BookMetadatas metadatas = GoogleBooksAPI.extractMetadata(result);

    BookMetadata metadata = new BookMetadata();
    metadata.setAuthor(metadatas.getAuthor());
    metadata.setPublisher(metadatas.getPublisher());
    metadata.setPageCount(metadatas.getPageCount());
    metadata.setIsbn(metadatas.getIsbn());
    metadata.setDescription(metadatas.getDescription());
    metadata.setPublishedDate(metadatas.getPublicationDate());

    MultipartFile thumbnailImage = processImage.downloadImage(metadatas.getThumbnailLink());
    String pathToImage = processImage.saveBookImage(thumbnailImage, book.getBookId());

    metadata.setCoverImagePath(pathToImage);

    book.setTitle(metadatas.getTitle());
    book.setMetadata(metadata);

    bookRepository.save(book);
  }
}
