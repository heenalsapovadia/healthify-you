package persistence.admin.util;

public interface DoctorRegistrationUtil {

    public boolean validateID(String doctorID);

    public boolean validateFirstName(String fname);

    public boolean validateLastName(String lname);

    public boolean validateDegree(String degree);

    public boolean validateDate(String date);

    public boolean validateSpecialization(String specialization);

    public boolean validateCity(String city);

    public boolean validateContact(String contact);

    public boolean validateEmail(String email);

    public boolean validatePassword(String password);

}
