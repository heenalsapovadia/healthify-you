/**
 * Interface for user login validations
 * @author Deeksha Sareen
 *
 */
package persistence.startup.util;

public interface UserLoginUtil {

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

}