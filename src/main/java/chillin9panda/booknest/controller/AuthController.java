package chillin9panda.booknest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
  @GetMapping("/login")
  public String LoginPage() {
    return "/auth/login";
  }

  @GetMapping("/register")
  public String RegisterPage() {
    return "/auth/register";
  }
}
