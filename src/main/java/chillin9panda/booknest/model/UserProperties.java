package chillin9panda.booknest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserProperties {

  @Column(name = "is_approved")
  private boolean isApproved;

  @Column(name = "is_active")
  private boolean isOnline;

  @Column(name = "has_system_access")
  private boolean hasSystemAccess;

  @Column(name = "account_locked")
  private boolean accountLocked;

  public boolean isApproved() {
    return isApproved;
  }

  public void setApproved(boolean isApproved) {
    this.isApproved = isApproved;
  }

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

  public boolean isAccountLocked() {
    return accountLocked;
  }

  public void setAccountLocked(boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

}
