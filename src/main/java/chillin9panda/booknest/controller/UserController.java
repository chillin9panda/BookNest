package chillin9panda.booknest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import chillin9panda.booknest.dto.request.RegisterUserRequest;
import chillin9panda.booknest.dto.response.CustomResponse;
import chillin9panda.booknest.model.User;
import chillin9panda.booknest.service.UserService;

@Controller
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public String registerUser(@ModelAttribute RegisterUserRequest request, Model model) {
    try {
      CustomResponse response = userService.registerUser(request);
      model.addAttribute("success", response.getMessage());
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
    }
    return "auth/register";
  }

  @GetMapping("/users")
  public String getAllUsers(Model model) {
    List<User> users = userService.getAll();
    model.addAttribute("users", users);

    return "booknest/users";

  }
}
