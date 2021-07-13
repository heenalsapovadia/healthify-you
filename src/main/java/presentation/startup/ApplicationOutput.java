/**
 * 
 */
package presentation.startup;

import java.util.Scanner;
import persistence.admin.model.Admin;
import persistence.doctor.model.Doctor;
import persistence.patient.model.Patient;
import presentation.CommonConstants;
import presentation.CommonErrors;
import presentation.ScreenFields;
import presentation.ScreenTitles;
import presentation.admin.AdminMenuOutput;
import presentation.doctor.DoctorMenuOutput;
import presentation.patient.RegisterPatientOutput;

/**
 * <pre>
 * This class controls the application flow.
 * User objects are instantiated and used throughout
 * the application.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class ApplicationOutput {
	
	private ApplicationOutput() {}
	
	private static ApplicationOutput applicationOutput;
	
	public static ApplicationOutput getInstance() {
		if(applicationOutput == null)
			applicationOutput = new ApplicationOutput();
		return applicationOutput;
	}
	
	void displayOutput() {
		loadMainScreenHeader();
		if(loadMainScreenContent()) {
			if(Admin.getAdmin() != null) {
				AdminMenuOutput adminMenuOutput = AdminMenuOutput.getInstance();
				adminMenuOutput.displayOutput();
			}
			else if(Doctor.getDoctor() != null) {
				//load doctor dashboard
				DoctorMenuOutput doctorMenuOutput = DoctorMenuOutput.getInstance();
				doctorMenuOutput.displayOutput();
			}
			/*
			 * else if(Patient.getInstance() != null) { 
			 * //load patient dashboard 
			 * }
			 */
		}
	}
	
	private void loadMainScreenHeader() {
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		System.out.println(CommonConstants.titleSpace+ScreenTitles.mainScreen+CommonConstants.titleSpace);
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
	}
	
	private boolean loadMainScreenContent() {
		boolean isLoggedIn = false;
		int sel = loadScreenOptions();
		if(sel == 1) {
			UserLogin userLogin = new UserLogin();
			userLogin.LoginUser();
			isLoggedIn = true;
		}
		else if(sel == 2) {
			RegisterPatientOutput registerPatient = new RegisterPatientOutput();
			registerPatient.RegisterPatient();
			isLoggedIn = false;
		}
		else if(sel == 3) {
			System.exit(0);
		}
		else {
			System.err.println(CommonErrors.invalidSelection);
			isLoggedIn = loadMainScreenContent();
		}
		return isLoggedIn;
	}
	
	private int loadScreenOptions() {
		int sel = -1;
		System.out.println("1. "+ScreenFields.login);
		System.out.println("2. "+ScreenFields.signUp);
		System.out.println("3. "+ScreenFields.exit);
		System.out.println(ScreenFields.selection);
		Scanner sc = new Scanner(System.in);
		if(sc.hasNextInt())
			sel = sc.nextInt();
		else {
			System.out.println(CommonErrors.invalidSelection);
			sel = loadScreenOptions();
		}
		return sel;
	}
}
