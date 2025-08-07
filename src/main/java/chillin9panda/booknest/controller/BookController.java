package chillin9panda.booknest.controller;

import java.security.Principal;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import chillin9panda.booknest.dto.request.UploadBookRequest;
import chillin9panda.booknest.dto.response.BookDetailsResponse;
import chillin9panda.booknest.dto.response.CustomResponse;
import chillin9panda.booknest.service.BookService;

@Controller
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books")
  public String booksPage(Model model) {
    model.addAttribute("books", bookService.bookOverviews());
    return "booknest/books/books";
  }

  @GetMapping("/books/thumbnail/{bookId}")
  public ResponseEntity<Resource> bookThumbnail(@PathVariable Long bookId) {
    return bookService.getBookThumbnail(bookId);
  }

  @GetMapping("/books/upload-book")
  public String uploadBookPage() {
    return "booknest/books/upload-book";
  }

  @PostMapping("/books/upload-book")
  public String uploadBook(@ModelAttribute UploadBookRequest request, RedirectAttributes redirectAttributes,
      Principal principal) {
    try {
      CustomResponse response = bookService.uploadBook(request, principal.getName());
      redirectAttributes.addFlashAttribute("successMessage", response.getMessage());
    } catch (IllegalArgumentException | IllegalStateException | UsernameNotFoundException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    return "redirect:/books/upload-book";
  }

  @GetMapping("/books/detail/{bookId}")
  public String bookDetails(@PathVariable Long bookId, Model model) {
    BookDetailsResponse response = bookService.bookDetailsResponse(bookId);
    model.addAttribute("bookDetails", response);

    return "/booknest/books/book-details";
  }

  @PostMapping("/books/delete/{bookId}")
  public String deleteBook(@PathVariable Long bookId, RedirectAttributes redirectAttributes) {
    CustomResponse response = bookService.deleteBook(bookId);
    redirectAttributes.addFlashAttribute("successMessage", response.getMessage());

    return "redirect:/books";
  }

}
