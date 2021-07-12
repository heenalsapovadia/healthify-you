/**
 * 
 */
package persistence.admin.model;

/**
 * @author Gurleen Saluja
 *
 */
public class Admin {
	private String email;
	private String password;
	
	private static Admin admin;
	
	private Admin() {}
	
	public static void setAdmin(String email) {
		if(admin == null) {
			admin = new Admin();
			admin.setEmail(email);
		}
	}
	
	public static Admin getAdmin() {
		return admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
