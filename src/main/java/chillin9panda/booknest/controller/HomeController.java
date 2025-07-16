package chillin9panda.booknest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/home")
  public String home(Model model, Authentication authentication) {
    model.addAttribute("username", authentication.getName());
    model.addAttribute("role", authentication.getAuthorities());

    return "booknest/home";
  }

}
