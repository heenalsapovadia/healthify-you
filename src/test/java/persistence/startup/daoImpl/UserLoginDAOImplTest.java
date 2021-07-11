package persistence.startup.daoImpl;

import static org.junit.Assert.*;
import org.junit.Test;
import persistence.startup.dao.UserLoginDAO;
import persistence.startup.model.Login;
import presentation.startup.DatabaseConnection;

public class UserLoginDAOImplTest {
   
    String userEmail1= "susan@gmail.com";
	String userEmail2= "sofia@yahoo.co.in";
	String userPassword1= "Susan@123";
	String userPassword2="yahoo";

	
	/*When user exists in database but password does not match*/
	@Test
	public void testWhenUserExistsSuccess() {
		DatabaseConnection.loadDatabaseConnection();
		Login obj= new Login();
		obj.setUserEmail(userEmail1);
		obj.setUserPassword(userPassword1);
		UserLoginDAO dao= new UserLoginDAOImpl();
		String output="Successfully logged in!";
		dao.GetuserDetails(obj);
		assertEquals(output, dao.GetuserDetails(obj));
	}
	
	/*When user exists in database and password matches*/
	@Test
	public void testWhenUserExistsUnsuccessful() {
		Login obj= new Login();
		DatabaseConnection.loadDatabaseConnection();
		obj.setUserEmail(userEmail1);
		obj.setUserPassword(userPassword2);
		UserLoginDAOImpl dao= new UserLoginDAOImpl();
		String output="Incorrect Password!";
		assertEquals(output, dao.GetuserDetails(obj));
    }
		
	/*When user does not exist in database*/
	@Test
	public void testWhenUserDoesnotExists() {
		Login obj= new Login();
		DatabaseConnection.loadDatabaseConnection();
		obj.setUserEmail(userEmail2);
		obj.setUserPassword(userPassword1);
		String output="User Id NOT found! Please Register first";
		UserLoginDAOImpl dao= new UserLoginDAOImpl();
		assertEquals(output, dao.GetuserDetails(obj));
	}
	
}
