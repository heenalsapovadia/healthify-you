/**
 * This class is used to get and set the user login details to be for persistence usage throughout the system.
 * @author Deeksha Sareen
 *
 */
package persistence.startup.model;

public class Login {

	private static String userEmail;
	private static String userPassword;
	private static String userType;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}