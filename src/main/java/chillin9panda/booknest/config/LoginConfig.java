package chillin9panda.booknest.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import chillin9panda.booknest.repository.UserRepository;

@Service
class LoginConfig implements UserDetailsService {

  private final UserRepository userRepository;

  public LoginConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    chillin9panda.booknest.model.User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    String role = user.getRole().name();
    System.out.println(role);

    return User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .authorities(new SimpleGrantedAuthority(role))
        .build();

  }

}
