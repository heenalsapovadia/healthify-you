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

  public boolean validateFirstName(String fname);

  public boolean validateLastName(String lname);

  public boolean validateDegree(String degree);

  public boolean validateDate(String date);

  public boolean validateSpecialization(String specialization);

  public boolean validateCity(String city);

  public boolean validateContact(Long contact);

  public boolean validateEmail(String email);

  public boolean validatePassword(String password);

}
