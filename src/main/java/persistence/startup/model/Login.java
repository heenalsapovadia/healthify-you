/**
 * 
 */
/**
 * @author Deeksha Sareen
 *
 */
package persistence.startup.model;

public class Login{
	private static String UserEmail;
	private static String UserPassword;
	private static String UserType;
	
	public  String getUserEmail() {
		return UserEmail;
	}
	public  void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public  void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public  String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	
}