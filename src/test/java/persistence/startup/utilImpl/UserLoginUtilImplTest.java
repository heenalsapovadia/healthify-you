package persistence.startup.utilImpl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import persistence.patient.utilImpl.RegistrationUtilImpl;

/**
 * Test cases for User login utility methods
 * 
 * @author Deeksha Sareen
 *
 */
public class UserLoginUtilImplTest {
  /* Testing valid emails that have an single @,single .,no spaces */
  @Test
  public void testValidateCorrectEmail() {
    String email1 = "Sofia@gmail.com";
    String email2 = "sofia_vargeras@yahoo.com";
    UserLoginUtilImpl login = new UserLoginUtilImpl();
    assertEquals(null, login.validateEmail(email1));
    assertEquals(null, login.validateEmail(email2));
  }

  /*
   * Testing invalid emails that do not contain @,. or contains duplicate symbols
   */
  @Test
  public void testValidateIncorrectEmail() {
    String email1 = "sofiagmail.com";
    String email2 = "sofiagmailcom";
    String email3 = "sofia@@gmail.com";
    String email4 = "sofia@yahoo..com";
    String email5 = " sofia@gmailcom";
    UserLoginUtilImpl login = new UserLoginUtilImpl();
    assertEquals("Invalid Email address! Enter valid email address! (should contain @ and . mandatorily! Can contain alphanumeric characters and special characters except spaces!)", login.validateEmail(email1));
    assertEquals("Invalid Email address! Enter valid email address! (should contain @ and . mandatorily! Can contain alphanumeric characters and special characters except spaces!)", login.validateEmail(email2));
    assertEquals("Invalid Email address! Enter valid email address! (should contain @ and . mandatorily! Can contain alphanumeric characters and special characters except spaces!)", login.validateEmail(email3));
    assertEquals("Invalid Email address! Enter valid email address! (should contain @ and . mandatorily! Can contain alphanumeric characters and special characters except spaces!)", login.validateEmail(email4));
    assertEquals("Invalid Email address! Enter valid email address! (should contain @ and . mandatorily! Can contain alphanumeric characters and special characters except spaces!)", login.validateEmail(email5));

  }

  /*
   * Testing correct password that contains a upper-case, lower-case, special
   * character, less than length 20 and greater than length 8 and contains numbers
   */
  @Test
  public void testValidateCorrectPassword() {
    String password = "Sofia1234@";
    String password1 = "Sofia12345678900@";
    UserLoginUtilImpl login = new UserLoginUtilImpl();
    assertEquals(null, login.validatePassword(password));
    assertEquals(null, login.validatePassword(password1));
  }

  /* Testing invalid password that do not meet the password requirements */
  @Test
  public void testValidateIncorrectPassword() {
    String password1 = "Sofia1234";
    String password2 = "sofia234@";
    String password3 = "SOFIA23@";
    String password4 = "sofia1234@";
    String password5 = "SofiaVargeres12345678901@";
    UserLoginUtilImpl login = new UserLoginUtilImpl();
    assertEquals("Password is invalid! Enter a valid password", login.validatePassword(password1));
    assertEquals("Password is invalid! Enter a valid password", login.validatePassword(password2));
    assertEquals("Password is invalid! Enter a valid password", login.validatePassword(password3));
    assertEquals("Password is invalid! Enter a valid password", login.validatePassword(password4));
    assertEquals("Password is invalid! Enter a valid password", login.validatePassword(password5));

  }

}
