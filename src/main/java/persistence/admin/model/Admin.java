/**
 * 
 */
package persistence.admin.model;

/**
 * @author Gurleen Saluja
 *
 */
public class Admin {
	private static String email;
	private static String password;
	
	private Admin() {}
	
	private static class AdminHelper {
		private static final Admin instance = new Admin();
	}
	
	public static Admin getAdmin() {
		return AdminHelper.instance;
	}
	
	public static void setAdmin(String email) {
		Admin.setEmail(email);
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		Admin.email = email;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Admin.password = password;
	}
}
