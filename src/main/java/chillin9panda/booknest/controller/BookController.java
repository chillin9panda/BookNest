package chillin9panda.booknest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

  @GetMapping("/books")
  public String booksPage() {
    return "booknest/books/books";
  }

}
