package chillin9panda.booknest.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import chillin9panda.booknest.dto.request.UploadBookRequest;
import chillin9panda.booknest.dto.response.BookDetailsResponse;
import chillin9panda.booknest.dto.response.BookOverviewRespose;
import chillin9panda.booknest.dto.response.CustomResponse;
import chillin9panda.booknest.model.Book;
import chillin9panda.booknest.model.BookMetadata;
import chillin9panda.booknest.repository.BookRepository;
import chillin9panda.files.books.BookMetadatas;
import chillin9panda.files.books.GoogleBooksAPI;
import chillin9panda.files.books.PDFMetadataIdentifier;
import chillin9panda.files.storage.Files;
import chillin9panda.files.storage.ProcessImage;
import chillin9panda.files.storage.ProcessUploads;
import chillin9panda.files.utils.FileOperations;
import chillin9panda.files.utils.SupportedFileTypes.BookExtension;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final ProcessUploads processUploads;
  private final UserService userService;
  private final ProcessImage processImage;
  private final Files files;

  public BookService(ProcessUploads processUploads, BookRepository bookRepository, UserService userService,
      ProcessImage processImage, Files files) {
    this.processUploads = processUploads;
    this.bookRepository = bookRepository;
    this.userService = userService;
    this.processImage = processImage;
    this.files = files;
  }

  public List<BookOverviewRespose> bookOverviews() {
    return bookRepository.findAll().stream()
        .map(book -> new BookOverviewRespose(
            book.getBookId(),
            "/books/thumbnail/" + book.getBookId(),
            book.getTitle(),
            book.getMetadata().getAuthor()))
        .collect(Collectors.toList());
  }

  public ResponseEntity<Resource> getBookThumbnail(Long bookId) {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new EntityNotFoundException("Guest not found!"));

    try {
      Path filePath = Paths.get(book.getMetadata().getCoverImagePath());
      Resource resource = new UrlResource(filePath.toUri());

      if (resource.exists() && resource.isReadable()) {
        return ResponseEntity.ok().body(resource);
      } else {
        return ResponseEntity.notFound().build();
      }

    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  public BookDetailsResponse bookDetailsResponse(Long bookId) {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new EntityNotFoundException("Book missing!"));

    BookDetailsResponse bookDetails = new BookDetailsResponse();
    bookDetails.setBookId(book.getBookId());
    bookDetails.setTitle(book.getTitle());
    bookDetails.setAuthor(book.getMetadata().getAuthor());
    bookDetails.setPublicationDate(book.getMetadata().getPublishedDate());
    bookDetails.setPublisher(book.getMetadata().getPublisher());
    bookDetails.setDescription(book.getMetadata().getDescription());
    bookDetails.setPageCount(book.getMetadata().getPageCount());
    bookDetails.setThumbnailUrl("/books/thumbnail/" + book.getBookId());

    return bookDetails;
  }

  @Transactional
  public CustomResponse uploadBook(UploadBookRequest request, String username) {
    String title = request.getTitle();
    MultipartFile bookFile = request.getBook();

    if (null == title || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be empty");
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

    Book book = new Book();
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
  public CustomResponse deleteBook(Long bookId) {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new EntityNotFoundException("Book not found!"));

    files.deleteFile(book.getMetadata().getCoverImagePath());
    files.deleteFile(book.getPathToFile());

    bookRepository.delete(book);

    CustomResponse response = new CustomResponse();
    response.setMessage("Book deleted!");

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
