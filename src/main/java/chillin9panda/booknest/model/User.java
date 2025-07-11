package chillin9panda.booknest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {
  @Id
  private String username;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Column(name = "profile_picture_path")
  private String profilePicturePath;

  @Column(name = "joined_date", nullable = false, updatable = false)
  private LocalDate joinedDate;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @Embedded
  private UserProperties userProperties;

  public enum Role {
    Admin,
    User
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getProfilePicturePath() {
    return profilePicturePath;
  }

  public void setProfilePicturePath(String profilePicturePath) {
    this.profilePicturePath = profilePicturePath;
  }

  public LocalDate getJoinedDate() {
    return joinedDate;
  }

  @PrePersist
  public void onRegister() {
    this.joinedDate = LocalDate.now();
  }

  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  public UserProperties getUserProperties() {
    return userProperties;
  }

  public void setUserProperties(UserProperties userProperties) {
    this.userProperties = userProperties;
  }
}
