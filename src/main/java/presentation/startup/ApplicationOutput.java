/**
 * 
 */
package presentation.startup;

import java.util.InputMismatchException;
import java.util.Scanner;
import persistence.admin.model.Admin;
import persistence.doctor.model.Doctor;
import persistence.patient.model.Patient;
import presentation.CommonConstants;
import presentation.CommonErrors;
import presentation.ScreenFields;
import presentation.ScreenTitles;
import presentation.admin.AdminMenuOutput;
import presentation.patient.RegisterPatientOutput;

/**
 * @author Gurleen Saluja
 *
 */
public class ApplicationOutput {
	
	private ApplicationOutput() {}
	
	private static class ApplicationOutputHelper {
		private static final ApplicationOutput instance = new ApplicationOutput();
	}
	
	public static ApplicationOutput getInstance() {
		return ApplicationOutputHelper.instance;
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
		System.out.println(CommonConstants.titleSpace+ScreenTitles.adminDashboard+CommonConstants.titleSpace);
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
	}
	
	private boolean loadMainScreenContent() {
		int sel = -1;
		boolean isLoggedIn = false;
		Scanner sc = null;
		try {
			System.out.println("1. "+ScreenFields.login);
			System.out.println("2. "+ScreenFields.signUp);
			System.out.println("3. "+ScreenFields.exit);
			System.out.println(ScreenFields.selection);
			sc = new Scanner(System.in);
			sel = sc.nextInt();
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
				isLoggedIn = loadMainScreenContent();
			}
		}
		catch(InputMismatchException e) {
			System.err.println(CommonErrors.invalidSelection);
			isLoggedIn = loadMainScreenContent();
		}
		return isLoggedIn;
	}
}
