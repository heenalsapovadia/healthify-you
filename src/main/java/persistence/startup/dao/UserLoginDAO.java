package persistence.startup.dao;

import persistence.startup.model.Login;

/**
 * Interface for user login This interface consists of methods required to
 * handle incoming user data when user logins
 * 
 * @author Deeksha Sareen
 *
 */
public interface UserLoginDAO {

	/**
	 * getting user details whenever any user logins into the system
	 * 
	 * @param Login object
	 * @return String
	 */
	public String getuserDetails(Login l);

}
