package chillin9panda.booknest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserProperties {

  @Column(name = "is_active")
  private boolean isOnline;

  @Column(name = "has_system_access")
  private boolean hasSystemAccess;

  public boolean isOnline() {
    return isOnline;
  }

  public void setOnline(boolean isOnline) {
    this.isOnline = isOnline;
  }

  public boolean isHasSystemAccess() {
    return hasSystemAccess;
  }

  public void setHasSystemAccess(boolean hasSystemAccess) {
    this.hasSystemAccess = hasSystemAccess;
  }
}
