package persistence.patient.utilImpl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import persistence.admin.utilImpl.DoctorRegistrationUtilImpl;

/**
 * Test cases for user registration utility module.
 * @author Deeksha Sareen
 *
 */

public class RegistrationUtilImplTest {

  @Test
  public void testValidateCorrectDate() {
    String date1 = "1997-09-12";
    RegistrationUtilImpl register = new RegistrationUtilImpl();
    assertEquals(null, register.validateDate(date1));
  }

  @Test
  public void testValidateIncorrectDate() {
    String date1 = "1995/09/12";
    String date2 = "12-09-2019";
    String date3 = "12-09/2019";
    String date4 = "12/092019";
    String date5 = "12092019";
    RegistrationUtilImpl register = new RegistrationUtilImpl();
    assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!", register.validateDate(date1));
    assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!", register.validateDate(date2));
    assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!", register.validateDate(date3));
    assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!", register.validateDate(date4));
    assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!", register.validateDate(date5));

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

    assertEquals(null, register.validateCity(cName));
    assertEquals(null, register.validateCity(cName2));
    assertEquals(null, register.validateCity(cName3));
    assertEquals(null, register.validateCity(cName4));
    assertEquals(null, register.validateCity(cName5));
    assertEquals(null, register.validateCity(cName6));

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

    assertEquals("Invalid city. Contains characters other than alphabets", register.validateCity(cName));
    assertEquals("Invalid city. Contains characters other than alphabets", register.validateCity(cName2));
    assertEquals("Invalid city. Contains characters other than alphabets", register.validateCity(cName3));
    assertEquals("Invalid city. Contains characters other than alphabets", register.validateCity(cName4));
    assertEquals("Invalid city. Contains characters other than alphabets", register.validateCity(cName5));
    assertEquals("Invalid city. Contains characters other than alphabets", register.validateCity(cName6));
  }

  @Test
  public void validateCorrectContact() {
    Long number1 = new Long("9021234567");
    RegistrationUtilImpl register = new RegistrationUtilImpl();

    assertEquals(null, register.validateContact(number1));
  }

  @Test
  public void testValidateIncorrectContact() {
    Long number1 = new Long("9031234567");
    Long number2 = new Long("902123456");
    Long number3 = new Long("90212345621");
    Long number4 = new Long("90312345621");
    RegistrationUtilImpl register = new RegistrationUtilImpl();

    assertEquals("The contact number should begin with 902", register.validateContact(number1));
    assertEquals("Contact number should be 10 digits long!", register.validateContact(number2));
    assertEquals("Contact number should be 10 digits long!", register.validateContact(number3));
    assertEquals("Contact number should be 10 digits long!", register.validateContact(number4));

  }

  @Test
  public void testValidateCorrectEmail() {
    String email1 = "Sofia@gmail.com";
    String email2 = "sofia_vargeras@yahoo.com";
    RegistrationUtilImpl register = new RegistrationUtilImpl();
    assertEquals(null, register.validateEmail(email1));
    assertEquals(null, register.validateEmail(email2));

  }

  @Test
  public void testValidateIncorrectEmail() {
    String email1 = "sofiagmail.com";
    String email2 = "sofiagmailcom";
    String email3 = "sofia@@gmail.com";
    String email4 = "sofia@yahoo..com";
    String email5 = " sofia@gmailcom";
    RegistrationUtilImpl register = new RegistrationUtilImpl();
    assertEquals("Invalid Email address!(should contain @ and ., alphanumeric characters and special characters except spaces!)", register.validateEmail(email1));
    assertEquals("Invalid Email address!(should contain @ and ., alphanumeric characters and special characters except spaces!)", register.validateEmail(email2));
    assertEquals("Invalid Email address!(should contain @ and ., alphanumeric characters and special characters except spaces!)", register.validateEmail(email3));
    assertEquals("Invalid Email address!(should contain @ and ., alphanumeric characters and special characters except spaces!)", register.validateEmail(email4));
    assertEquals("Invalid Email address!(should contain @ and ., alphanumeric characters and special characters except spaces!)", register.validateEmail(email5));

  }

  @Test
  public void testValidateCorrectPassword() {
    String password = "Sofia1234@";
    String password1 = "Sofia12345678900@";
    RegistrationUtilImpl register = new RegistrationUtilImpl();
    assertEquals(null, register.validatePassword(password));
    assertEquals(null, register.validatePassword(password1));
  }

  @Test
  public void testValidateIncorrectPassword() {
    String password1 = "Sofia1234";
    String password2 = "sofia234@";
    String password3 = "SOFIA23@";
    String password4 = "sofia1234@";
    String password5 = "SofiaVargeres12345678901@";
    RegistrationUtilImpl register = new RegistrationUtilImpl();
    assertEquals("Invalid Password!(should be atleast 8 and atmost 20 characters long, contains atleast one " +
            "digit, a lowercase and uppercase alphabet, one special character except white-spaces", register.validatePassword(password1));
    assertEquals("Invalid Password!(should be atleast 8 and atmost 20 characters long, contains atleast one " +
            "digit, a lowercase and uppercase alphabet, one special character except white-spaces"
	, register.validatePassword(password2));
    assertEquals("Invalid Password!(should be atleast 8 and atmost 20 characters long, contains atleast one " +
            "digit, a lowercase and uppercase alphabet, one special character except white-spaces"
	, register.validatePassword(password3));
    assertEquals("Invalid Password!(should be atleast 8 and atmost 20 characters long, contains atleast one " +
            "digit, a lowercase and uppercase alphabet, one special character except white-spaces" 
	, register.validatePassword(password4));
    assertEquals("Invalid Password!(should be atleast 8 and atmost 20 characters long, contains atleast one " +
            "digit, a lowercase and uppercase alphabet, one special character except white-spaces"
	, register.validatePassword(password5));

  }

  @Test
  public void testValidateCorrectNames() {
    String Name = "Deeksha";
    String Name2 = "Sofia";
    String Name3 = "sofia";
    RegistrationUtilImpl register = new RegistrationUtilImpl();
    assertEquals(null, register.validateNames(Name));
    assertEquals(null, register.validateNames(Name2));
    assertEquals(null, register.validateNames(Name3));

  }

  @Test
  public void testValidateIncorrectNames() {
    String Name = "Karolina12";
    String Name2 = "Jia@";
    String Name3 = "jia%";
    String Name4 = "1234";
    String Name5 = "";
    RegistrationUtilImpl register = new RegistrationUtilImpl();
    assertEquals("Invalid name. Contains characters other than alphabets", register.validateNames(Name));
    assertEquals("Invalid name. Contains characters other than alphabets", register.validateNames(Name2));
    assertEquals("Invalid name. Contains characters other than alphabets", register.validateNames(Name3));
    assertEquals("Invalid name. Contains characters other than alphabets", register.validateNames(Name4));
    assertEquals("Invalid name. Contains characters other than alphabets", register.validateNames(Name5));

  }

}
