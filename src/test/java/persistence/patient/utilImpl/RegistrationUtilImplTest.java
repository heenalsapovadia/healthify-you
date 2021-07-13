package persistence.patient.utilImpl;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.admin.utilImpl.DoctorRegistrationUtilImpl;

public class RegistrationUtilImplTest {

	@Test
	public void testValidateCorrectDate() {
		String date1 = "1997-09-12";
		RegistrationUtilImpl register = new RegistrationUtilImpl();
	    assertEquals(null, register.ValidateDate(date1));
	}
	@Test
	public void testValidateIncorrectDate() {
		String date1 = "1995/09/12";
	    String date2 = "12-09-2019";
	    String date3 = "12-09/2019";
	    String date4 = "12/092019";
	    String date5 = "12092019";
		RegistrationUtilImpl register = new RegistrationUtilImpl();
	    assertEquals("The DOB is invalid.Please enter a valid DOB", register.ValidateDate(date1));
	    assertEquals("The DOB is invalid.Please enter a valid DOB", register.ValidateDate(date2));
	    assertEquals("The DOB is invalid.Please enter a valid DOB", register.ValidateDate(date3));
	    assertEquals("The DOB is invalid.Please enter a valid DOB", register.ValidateDate(date4));
	    assertEquals("The DOB is invalid.Please enter a valid DOB", register.ValidateDate(date5));
	    
	}
	@Test
	public void testValidateCorrectCity() {
	    String cName = "Halifax";
	    String cName2 = "Nova Scotia";
	    String cName3 = "nova scotia";
	    String cName4 = "halifax";
	    String cName5 = "HALIFAX";
	    String cName6 = "NOVA SCOTIA";
	    RegistrationUtilImpl register = new RegistrationUtilImpl();
	     
		assertEquals(null, register.ValidateCity(cName));
		assertEquals(null, register.ValidateCity(cName2));
		assertEquals(null, register.ValidateCity(cName3));
		assertEquals(null, register.ValidateCity(cName4));
		assertEquals(null, register.ValidateCity(cName5));
		assertEquals(null, register.ValidateCity(cName6));
		
	    }

	 @Test
	 public void testValidateIncorrectCity() {
	    String cName = "Halifax123";
	    String cName2 = "Nova Scotia@";
	    String cName3 = "nova scotia&";
	    String cName4 = "halifax234";
	    String cName5 = "3457-";
	    String cName6 = "";
        RegistrationUtilImpl register = new RegistrationUtilImpl();
	     
	    assertEquals("Incorrect City name", register.ValidateCity(cName));
	    assertEquals("Incorrect City name", register.ValidateCity(cName2));
	    assertEquals("Incorrect City name", register.ValidateCity(cName3));
		assertEquals("Incorrect City name", register.ValidateCity(cName4));
		assertEquals("Incorrect City name", register.ValidateCity(cName5));
		assertEquals("Incorrect City name", register.ValidateCity(cName6));
	    }

	@Test
	public void validateCorrectContact() {
	    Long number1 = new Long("9021234567");
	    RegistrationUtilImpl register = new RegistrationUtilImpl();
		     
		assertEquals(null, register.ValidateContact(number1));
	}
    
	@Test
	public void testValidateIncorrectContact() {
	    Long number1 = new Long("9031234567");
	    Long number2 = new Long("902123456");
	    Long number3 = new Long("90212345621");
	    Long number4 = new Long("90312345621");
	    RegistrationUtilImpl register = new RegistrationUtilImpl();
	     
	    assertEquals("The contact number should begin with 902", register.ValidateContact(number1));
	    assertEquals("Contact number should be 10 digits long!", register.ValidateContact(number2));
	    assertEquals("Contact number should be 10 digits long!", register.ValidateContact(number3));
	    assertEquals("Contact number should be 10 digits long!", register.ValidateContact(number4));
	  
	}    
	

	@Test
	public void testValidateCorrectEmail() {
		String email1 = "Sofia@gmail.com";
        String email2 = "sofia_vargeras@yahoo.com";
        RegistrationUtilImpl register = new RegistrationUtilImpl();
        assertEquals(null, register.ValidateEmail(email1));
	    assertEquals(null, register.ValidateEmail(email2));
	   
	}
	@Test
	public void testValidateIncorrectEmail() {
		String email1 = "sofiagmail.com";
	    String email2 = "sofiagmailcom";
	    String email3= "sofia@@gmail.com";
	    String email4 = "sofia@yahoo..com";
	    String email5 = " sofia@gmailcom";
        RegistrationUtilImpl register = new RegistrationUtilImpl();
        assertEquals("The email ID is invalid.Please enter a valid email address", register.ValidateEmail(email1));
	    assertEquals("The email ID is invalid.Please enter a valid email address", register.ValidateEmail(email2));
	    assertEquals("The email ID is invalid.Please enter a valid email address", register.ValidateEmail(email3));
	    assertEquals("The email ID is invalid.Please enter a valid email address", register.ValidateEmail(email4));
	    assertEquals("The email ID is invalid.Please enter a valid email address", register.ValidateEmail(email5));
	   
	}
	@Test
	public void testValidateCorrectPassword() {
	    String password = "Sofia1234@";
        String password1 = "Sofia12345678900@";
        RegistrationUtilImpl register = new RegistrationUtilImpl();
	    assertEquals(null,register.ValidatePassword(password));
	    assertEquals(null,register.ValidatePassword(password1));
	}
	@Test
	public void testValidateIncorrectPassword() {
		String password1 = "Sofia1234";
        String password2 = "sofia234@";
        String password3 = "SOFIA23@";
        String password4 = "sofia1234@";
        String password5 = "SofiaVargeres12345678901@";
        RegistrationUtilImpl register = new RegistrationUtilImpl();
	    assertEquals("The password is invalid. Please enter a valid password",register.ValidatePassword(password1));
	    assertEquals("The password is invalid. Please enter a valid password",register.ValidatePassword(password2));
	    assertEquals("The password is invalid. Please enter a valid password",register.ValidatePassword(password3));
	    assertEquals("The password is invalid. Please enter a valid password",register.ValidatePassword(password4));
	    assertEquals("The password is invalid. Please enter a valid password",register.ValidatePassword(password5));
        
	}
	@Test
	public void testValidateCorrectNames() {
		 String Name = "Deeksha";
	     String Name2 = "Sofia";
	     String Name3 = "sofia";
	     RegistrationUtilImpl register = new RegistrationUtilImpl();
	     assertEquals(null,register.ValidateNames(Name));
	     assertEquals(null,register.ValidateNames(Name2));
	     assertEquals(null,register.ValidateNames(Name3));
		
	}
	
	@Test
	public void testValidateIncorrectNames() {
	     String Name = "Karolina12";
	     String Name2 = "Jia@";
	     String Name3 = "jia%";
	     String Name4 = "1234";
	     String Name5 = "";
	     RegistrationUtilImpl register = new RegistrationUtilImpl();
	     assertEquals("Contains characters other than alphabets. Please retry",register.ValidateNames(Name));
	     assertEquals("Contains characters other than alphabets. Please retry",register.ValidateNames(Name2));
	     assertEquals("Contains characters other than alphabets. Please retry",register.ValidateNames(Name3));
	     assertEquals("Contains characters other than alphabets. Please retry",register.ValidateNames(Name4));
	     assertEquals("Contains characters other than alphabets. Please retry",register.ValidateNames(Name5));
	 
	}

}
