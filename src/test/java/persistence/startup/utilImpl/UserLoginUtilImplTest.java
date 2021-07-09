package persistence.startup.utilImpl;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.patient.utilImpl.RegistrationUtilImpl;

public class UserLoginUtilImplTest {
    /*Testing valid emails that have an single @,single .,no spaces*/
	@Test
	public void testValidateCorrectEmail() {
		String email1 = "Sofia@gmail.com";
        String email2 = "sofia_vargeras@yahoo.com";
        UserLoginUtilImpl login = new UserLoginUtilImpl();
        assertEquals(null, login.ValidateEmail(email1));
	    assertEquals(null, login.ValidateEmail(email2));
	}
	
	/*Testing invalid emails that do not contain @,. or contains duplicate symbols*/
	@Test
	public void testValidateIncorrectEmail() {
		String email1 = "sofiagmail.com";
	    String email2 = "sofiagmailcom";
	    String email3= "sofia@@gmail.com";
	    String email4 = "sofia@yahoo..com";
	    String email5 = " sofia@gmailcom";
	    UserLoginUtilImpl login = new UserLoginUtilImpl();
        assertEquals("The email ID is invalid.Please enter a valid email address", login.ValidateEmail(email1));
	    assertEquals("The email ID is invalid.Please enter a valid email address", login.ValidateEmail(email2));
	    assertEquals("The email ID is invalid.Please enter a valid email address", login.ValidateEmail(email3));
	    assertEquals("The email ID is invalid.Please enter a valid email address", login.ValidateEmail(email4));
	    assertEquals("The email ID is invalid.Please enter a valid email address", login.ValidateEmail(email5));
	   
	}
	
	/*Testing correct password that contains a upper-case, lower-case, special character, less than length 20 and greater
	 * than length 8 and contains numbers*/
	@Test
	public void testValidateCorrectPassword() {
		String password = "Sofia1234@";
        String password1 = "Sofia12345678900@";
        UserLoginUtilImpl login = new UserLoginUtilImpl();
	    assertEquals(null,login.ValidatePassword(password));
	    assertEquals(null,login.ValidatePassword(password1));
	}
	
	/*Testing invalid password that do not meet the password requirements*/
	@Test
	public void testValidateIncorrectPassword() {
		String password1 = "Sofia1234";
        String password2 = "sofia234@";
        String password3 = "SOFIA23@";
        String password4 = "sofia1234@";
        String password5 = "SofiaVargeres12345678901@";
        UserLoginUtilImpl login = new UserLoginUtilImpl();
	    assertEquals("The password is invalid. Please enter a valid password",login.ValidatePassword(password1));
	    assertEquals("The password is invalid. Please enter a valid password",login.ValidatePassword(password2));
	    assertEquals("The password is invalid. Please enter a valid password",login.ValidatePassword(password3));
	    assertEquals("The password is invalid. Please enter a valid password",login.ValidatePassword(password4));
	    assertEquals("The password is invalid. Please enter a valid password",login.ValidatePassword(password5));
        
	}

}

