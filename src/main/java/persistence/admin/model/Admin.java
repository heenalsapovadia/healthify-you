package persistence.admin.model;

/**
 * @author Gurleen Saluja
 *
 */
public class Admin {
	
	private static Admin admin;
	
	private Admin() {}
	
	public static void setAdmin(String email) {
		if(admin == null) {
			admin = new Admin();
		}
	}
	
	public static Admin instance() {
		return admin;
	}
}
