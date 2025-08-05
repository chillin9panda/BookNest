package chillin9panda.booknest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import chillin9panda.booknest.dto.request.RegisterUserRequest;
import chillin9panda.booknest.dto.response.CustomResponse;
import chillin9panda.booknest.model.User;
import chillin9panda.booknest.model.User.Role;
import chillin9panda.booknest.model.UserProperties;
import chillin9panda.booknest.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public CustomResponse registerUser(RegisterUserRequest request) {
    if (request.getUsername() == null) {
      throw new IllegalArgumentException("Username required!");
    }

    if (request.getPassword() == null) {
      throw new IllegalArgumentException("Password required!");
    }

    if (userRepository.existsByUsername(request.getUsername())) {
      throw new IllegalArgumentException("Username taken!");
    }

    if (!request.getPassword().equals(request.getConfirmPassword())) {
      throw new IllegalArgumentException("Passwords do not match!");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    UserProperties properties = new UserProperties();
    if (userRepository.count() == 0) {
      user.setRole(Role.Admin);
      properties.setHasSystemAccess(true);
    } else {
      user.setRole(Role.User);
      properties.setHasSystemAccess(false);
    }
    properties.setOnline(false);
    user.setUserProperties(properties);

    userRepository.save(user);

    CustomResponse response = new CustomResponse();
    response.setSuccess(true);
    response.setMessage("New user added!");

    return response;
  }

  public List<User> getAll() {
    List<User> users = userRepository.findAll();
    return users;
  }

  public User getUserByUserName(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }

}
