package persistence.patient.util;

/**
 * Interface for handling utility functions of Patient registration 
 * @author Deeksha Sareen
 *
 */
public interface RegistrationUtil {
  
  public String validateDate(String date);

  public String validateContact(Long Contact);

  public String validateEmail(String email);

  public String validatePassword(String password);

  public String validateNames(String name);

  public String validateCity(String city);
}
