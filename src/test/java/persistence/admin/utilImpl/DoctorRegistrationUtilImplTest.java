package persistence.admin.utilImpl;

import static org.junit.Assert.*;

import org.junit.Test;
import persistence.admin.model.DoctorRegistration;
import presentation.startup.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
* <pre>
* Perform tests for registering doctor in the system
* </pre>
*
* @author Samiksha Salgaonkar
*
*/

public class DoctorRegistrationUtilImplTest {

    @Test
    public void validateCorrectFirstName() {
        String fName = "Karolina";
        String fName2 = "Jia Zhelu";
        String fName3 = "jia";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertTrue(doc.validateFirstName(fName));
        assertTrue(doc.validateFirstName(fName2));
        assertTrue(doc.validateFirstName(fName3));

    }

    @Test
    public void validateIncorrectFirstName() {
        String fName = "Karolina12";
        String fName2 = "Jia@";
        String fName3 = "jia%";
        String fName4 = "1234";
        String fName5 = "";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertFalse(doc.validateFirstName(fName));
        assertFalse(doc.validateFirstName(fName2));
        assertFalse(doc.validateFirstName(fName3));
        assertFalse(doc.validateFirstName(fName4));
        assertFalse(doc.validateFirstName(fName5));

    }

    @Test
    public void validateCorrectLastName() {
        String lName = "Blix";
        String lName2 = "Zhelu";
        String lName3 = "zhelu";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertTrue(doc.validateLastName(lName));
        assertTrue(doc.validateLastName(lName2));
        assertTrue(doc.validateLastName(lName3));

    }

    @Test
    public void validateIncorrectLastName() {
        String lName = "Karolina12";
        String lName2 = "Jia@";
        String lName3 = "jia%";
        String lName4 = "1234";
        String lName5 = "Jia ";
        String lName6 = "";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertFalse(doc.validateLastName(lName));
        assertFalse(doc.validateLastName(lName2));
        assertFalse(doc.validateLastName(lName3));
        assertFalse(doc.validateLastName(lName4));
        assertFalse(doc.validateLastName(lName5));
        assertFalse(doc.validateLastName(lName6));

    }

    @Test
    public void validateCorrectDegree() {
        String dName = "M B B S";
        String dName2 = "jia";
        String dName3 = "Mbbs";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertTrue(doc.validateDegree(dName));
        assertTrue(doc.validateDegree(dName2));
        assertTrue(doc.validateDegree(dName3));

    }

    @Test
    public void validateIncorrectDegree() {
        String dName = "Karolina12";
        String dName2 = "Jia@";
        String dName3 = "jia%";
        String dName4 = "1234";
        String dName5 = "";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertFalse(doc.validateDegree(dName));
        assertFalse(doc.validateDegree(dName2));
        assertFalse(doc.validateDegree(dName3));
        assertFalse(doc.validateDegree(dName4));
        assertFalse(doc.validateDegree(dName5));

    }

    @Test
    public void validateCorrectSpecialization() {
        String sName = "M B B S";
        String sName2 = "jia";
        String sName3 = "Mbbs";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertTrue(doc.validateDegree(sName));
        assertTrue(doc.validateDegree(sName2));
        assertTrue(doc.validateDegree(sName3));

    }

    @Test
    public void validateIncorrectSpecialization() {
        String sName = "Karolina12";
        String sName2 = "Jia@";
        String sName3 = "jia%";
        String sName4 = "1234";
        String sName5 = "";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertFalse(doc.validateDegree(sName));
        assertFalse(doc.validateDegree(sName2));
        assertFalse(doc.validateDegree(sName3));
        assertFalse(doc.validateDegree(sName4));
        assertFalse(doc.validateDegree(sName5));

    }

    @Test
    public void validateCorrectCity() {
        String cName = "Halifax";
        String cName2 = "Nova Scotia";
        String cName3 = "nova scotia";
        String cName4 = "halifax";
        String cName5 = "HALIFAX";
        String cName6 = "NOVA SCOTIA";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertTrue(doc.validateDegree(cName));
        assertTrue(doc.validateDegree(cName2));
        assertTrue(doc.validateDegree(cName3));
        assertTrue(doc.validateDegree(cName4));
        assertTrue(doc.validateDegree(cName5));
        assertTrue(doc.validateDegree(cName6));
    }

    @Test
    public void validateIncorrectCity() {
        String cName = "Halifax123";
        String cName2 = "Nova Scotia@";
        String cName3 = "nova scotia&";
        String cName4 = "halifax234";
        String cName5 = "3457-";
        String cName6 = "";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertFalse(doc.validateDegree(cName));
        assertFalse(doc.validateDegree(cName2));
        assertFalse(doc.validateDegree(cName3));
        assertFalse(doc.validateDegree(cName4));
        assertFalse(doc.validateDegree(cName5));
        assertFalse(doc.validateDegree(cName6));
    }

    @Test
    public void validateCorrectContact() {
        Long number1 = new Long("9021234567");
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertTrue(doc.validateContact(number1));

    }

    @Test
    public void validateIncorrectContact() {
        Long number1 = new Long("9031234567");
        Long number2 = new Long("902123456");
        Long number3 = new Long("90212345621");
        Long number4 = new Long("90312345621");
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertFalse("The contact number should begin with 902", doc.validateContact(number1));
        assertFalse("Contact number should be 10 digits long!", doc.validateContact(number2));
        assertFalse("Contact number should be 10 digits long!", doc.validateContact(number3));
        assertFalse("The contact number should begin with 902", doc.validateContact(number4));

    }

    @Test
    public void validateCorrectEmail() {
        DatabaseConnection.loadDatabaseConnection();

        String email1 = "karolina12@healthifyyou.com";
        String email2 = "karolina_blix@healthifyyou.com";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertTrue(doc.validateEmail(email1));
        assertTrue(doc.validateEmail(email2));

    }

    @Test
    public void validateIncorrectEmail() {
        DatabaseConnection.loadDatabaseConnection();

        String email1 = "karolina12@gmail.com";
        String email2 = "karolinahealthifyyou.com";
        String email3 = "karolinahealthifyyoucom";
        String email4 = "karolina@@healthifyyou.com";
        String email5 = "karolina@healthifyyou..com";
        String email6 = "karolina@healthifyyou.com";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertFalse(doc.validateEmail(email1));
        assertFalse(doc.validateEmail(email2));
        assertFalse(doc.validateEmail(email3));
        assertFalse(doc.validateEmail(email4));
        assertFalse(doc.validateEmail(email5));
        assertFalse(doc.validateEmail(email6));

    }

    @Test
    public void validateCorrectPassword() {
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();
        String password = "Karo1234@";
        String password1 = "Karolina12345678900@";

        assertTrue(doc.validatePassword(password));
        assertTrue(doc.validatePassword(password1));

    }

    @Test
    public void validateIncorrectPassword() {
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();
        String password1 = "Karo1234";
        String password2 = "karo1234@";
        String password3 = "KARO123@";
        String password4 = "karo1234@";
        String password5 = "Karolina123456789001@";
        assertFalse(doc.validatePassword(password1));
        assertFalse(doc.validatePassword(password2));
        assertFalse(doc.validatePassword(password3));
        assertFalse(doc.validatePassword(password4));
        assertFalse(doc.validatePassword(password5));

    }

    @Test
    public void validateCorrectDate() {
        String date1 = "1997-09-12";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertTrue(doc.validateDate(date1));

    }

    @Test
    public void validateIncorrectDate() {
        String date1 = "1995/09/12";
        String date2 = "12-09-2019";
        String date3 = "12/09/2019";
        String date4 = "12/092019";
        String date5 = "12092019";
        DoctorRegistrationUtilImpl doc = new DoctorRegistrationUtilImpl();

        assertFalse(doc.validateDate(date1));
        assertFalse(doc.validateDate(date2));
        assertFalse(doc.validateDate(date3));
        assertFalse(doc.validateDate(date4));
        assertFalse(doc.validateDate(date5));

    }

}