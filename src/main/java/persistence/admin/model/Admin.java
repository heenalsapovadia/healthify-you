package persistence.admin.model;

/**
 * @author Gurleen Saluja
 *
 */
public class Admin {
	
	private String email;
	
	private static Admin admin;
	
	private Admin() {}
	
	public static void setAdmin(String email) {
		if(admin == null) {
			admin = new Admin();
			admin.setEmail(email);
		}
	}
	
	public static Admin instance() {
		return admin;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
