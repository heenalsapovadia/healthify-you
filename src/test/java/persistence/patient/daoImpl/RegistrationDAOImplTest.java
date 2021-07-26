package persistence.patient.daoImpl;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.patient.dao.RegistrationDAO;
import persistence.patient.model.Patient;
import presentation.startup.DatabaseConnection;

public class RegistrationDAOImplTest {
		private String PatientName= "Ronnie";
	    private String PatientGender= "M";
	    private String PatientDob= "1990/05/24";
	    private String PatientEmail ="ronnie@gmail.com";
	    private String PatientEmail2=null;
	    private String PatientAddress ="Brazil";
	    private long PatientContact=902847593;
	    private String Password = "Ronnie@123";
	    private String PatientType = "P";
	    
	/*Test when user is already Registered*/    
	@Test
	public void testExistingUserRegistration() {
		Patient obj = Patient.instance();
		RegistrationDAOImpl dao = new RegistrationDAOImpl();
		DatabaseConnection.loadDatabaseConnection();
		obj.setPatientName(PatientName);
		obj.setPatientDob(PatientDob);
		obj.setPatientContact(PatientContact);
		obj.setPatientEmail(PatientEmail);
		obj.setPassword(Password);
		obj.setPatientAddress(PatientAddress);
		obj.setPassword(PatientType);
		obj.setPatientGender(PatientGender);
		String successOutput= "Successfully Registered";
		String userExistsOutput="User details already exist";
		dao.addPatientDetails(obj);
		assertEquals(userExistsOutput, dao.addPatientDetails(obj));
		
	}
	/* Test when an error occurs in Registration */
	@Test
	public void testErrorInRegistration() {
		Patient obj = Patient.instance();
		RegistrationDAOImpl dao = new RegistrationDAOImpl();
		DatabaseConnection.loadDatabaseConnection();
		obj.setPatientName(PatientName);
		obj.setPatientDob(PatientDob);
		obj.setPatientContact(PatientContact);
		obj.setPatientEmail(PatientEmail2);
		obj.setPassword(Password);
		obj.setPatientAddress(PatientAddress);
		obj.setPassword(PatientType);
		obj.setPatientGender(PatientGender);
		String error="Error Occured";
		dao.addPatientDetails(obj);
		assertEquals(error, dao.addPatientDetails(obj));
		
	}

}
