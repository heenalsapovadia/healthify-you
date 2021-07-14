package persistence.admin.util;

/**
 * <pre>
 * Util interface for doctor registration
 * </pre>
 *
 * @author Samiksha Salgaonkar
 *
 **/

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
