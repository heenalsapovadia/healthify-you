/**
 * 
 */
package presentation.startup;

import java.util.ArrayList;
import java.util.List;
import persistence.admin.model.Admin;
import persistence.doctor.model.Doctor;
import persistence.patient.model.Patient;
import presentation.admin.AdminMenuOutput;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;
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
			return new ApplicationOutput();
		return applicationOutput;
	}
	
	void displayOutput() {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.mainScreen);
		loadMainScreenContent(consoleObj);
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
	
	private void loadMainScreenContent(PrintToConsole consoleObj) {
		List<String> selectionOptions = getSelectionOptions();
		int sel = consoleObj.printSelection(selectionOptions);
		if(sel == 1) {
			UserLogin userLogin = new UserLogin();
			userLogin.LoginUser();
		}
		else if(sel == 2) {
			RegisterPatientOutput registerPatient = new RegisterPatientOutput();
			registerPatient.RegisterPatient();
		}
		else if(sel == 3) {
			System.exit(0);
		}
		else {
			consoleObj.printError(CommonErrors.invalidSelection);
			loadMainScreenContent(consoleObj);
		}
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.login);
		selectionOptions.add(ScreenFields.signUp);
		selectionOptions.add(ScreenFields.exit);
		return selectionOptions;
	}
}
