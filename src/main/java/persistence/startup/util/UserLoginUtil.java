/**
 * Interface for user login validations
 * @author Deeksha Sareen
 *
 */
package persistence.startup.util;

public interface UserLoginUtil {

  public String validateEmail(String email);

  public String validatePassword(String password);
  
}