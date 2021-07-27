package persistence.patient.util;

/**
 * Interface for handling utility functions of Patient registration
 * 
 * @author Deeksha Sareen
 *
 */
public interface RegistrationUtil {

	/**
	 * @param date
	 * @return String
	 */
	public String validateDate(String date);

	/**
	 * @param Contact
	 * @return String
	 */
	public String validateContact(Long contact);

	/**
	 * @param email
	 * @return String
	 */
	public String validateEmail(String email);

	/**
	 * @param password
	 * @return String
	 */
	public String validatePassword(String password);

	/**
	 * @param name
	 * @return String
	 */
	public String validateNames(String name);

	/**
	 * @param city
	 * @return String
	 */
	public String validateCity(String city);
}
