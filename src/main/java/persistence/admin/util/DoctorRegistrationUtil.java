package persistence.admin.util;

/**
* <pre>
* Perform operations for registering doctor in the system
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public interface DoctorRegistrationUtil {

  /**
   * @param fname
   * @return boolean
   */
  public boolean validateFirstName(String fname);

  /**
   * @param lname
   * @return boolean
   */
  public boolean validateLastName(String lname);

  /**
   * @param degree
   * @return boolean
   */
  public boolean validateDegree(String degree);

  /**
   * @param date
   * @return boolean
   */
  public boolean validateDate(String date);

  /**
   * @param specialization
   * @return boolean
   */
  public boolean validateSpecialization(String specialization);

  /**
   * @param city
   * @return boolean
   */
  public boolean validateCity(String city);

  /**
   * @param contact
   * @return boolean
   */
  public boolean validateContact(Long contact);

  /**
   * @param email
   * @return boolean
   */
  public boolean validateEmail(String email);

  /**
   * @param password
   * @return boolean
   */
  public boolean validatePassword(String password);

}
