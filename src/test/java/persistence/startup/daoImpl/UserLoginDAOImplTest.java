package persistence.startup.daoImpl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import persistence.startup.dao.UserLoginDAO;
import persistence.startup.model.Login;
import presentation.startup.DatabaseConnection;

/**
 * Test cases for user login data layer
 * 
 * @author Deeksha Sareen
 *
 */
public class UserLoginDAOImplTest {

  String userEmail1 = "susan@gmail.com";
  String userEmail2 = "sofia@yahoo.co.in";
  String userPassword1 = "Susan@123";
  String userPassword2 = "yahoo";

  /* When user exists in database but password does not match */
  @Test
  public void testWhenUserExistsSuccess() {
    DatabaseConnection.loadDatabaseConnection();
    Login obj = new Login();
    obj.setUserEmail(userEmail1);
    obj.setUserPassword(userPassword1);
    UserLoginDAO dao = new UserLoginDAOImpl();
    String output = "Successfully logged in!";
    dao.getuserDetails(obj);
    assertEquals(output, dao.getuserDetails(obj));
  }

  /* When user exists in database and password matches */
  @Test
  public void testWhenUserExistsUnsuccessful() {
    Login obj = new Login();
    DatabaseConnection.loadDatabaseConnection();
    obj.setUserEmail(userEmail1);
    obj.setUserPassword(userPassword2);
    UserLoginDAOImpl dao = new UserLoginDAOImpl();
    String output = "Incorrect Password!";
    assertEquals(output, dao.getuserDetails(obj));
  }

  /* When user does not exist in database */
  @Test
  public void testWhenUserDoesnotExists() {
    Login obj = new Login();
    DatabaseConnection.loadDatabaseConnection();
    obj.setUserEmail(userEmail2);
    obj.setUserPassword(userPassword1);
    String output = "User ID not found! Please Sign up first";
    UserLoginDAOImpl dao = new UserLoginDAOImpl();
    assertEquals(output, dao.getuserDetails(obj));
  }

}
