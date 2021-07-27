package presentation.startup;

import java.sql.SQLException;
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
import presentation.doctor.DoctorMenuOutput;
import presentation.patient.PatientMenuOutput;
import presentation.patient.RegisterPatientOutput;

/**
 * <pre>
 * This class controls the application flow.
 * User objects are instantiated and used throughout
 * the application.
 * </pre>
 * 
 * @author G12
 *
 */
public class ApplicationOutput {
	
	private ApplicationOutput() {}
	
	private static ApplicationOutput applicationOutput;
	
	private PrintToConsole consoleObj;
	
	public static ApplicationOutput getInstance() {
		if(applicationOutput == null)
			applicationOutput = new ApplicationOutput();
		return applicationOutput;
	}
	
	void displayOutput() throws SQLException {
		consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.MAIN_SCREEN);
		loadMainScreenContent();
		if(Admin.instance() != null) {
			AdminMenuOutput adminMenuOutput = new AdminMenuOutput();
			adminMenuOutput.displayOutput();
		}
		else if(Doctor.instance() != null) {
			DoctorMenuOutput doctorMenuOutput = new DoctorMenuOutput();
			doctorMenuOutput.displayOutput();
		}
		else if(Patient.instance() != null) { 
			PatientMenuOutput patientMenuOutput = new PatientMenuOutput();
			patientMenuOutput.displayOutput();
		} 
	}
	
	private void loadMainScreenContent() {
		List<String> selectionOptions = getSelectionOptions();
		int sel = consoleObj.printSelection(selectionOptions);
		if(sel == 1) {
			UserLogin userLogin = new UserLogin();
			userLogin.loginUser();
		}
		else if(sel == 2) {
			RegisterPatientOutput registerPatient = new RegisterPatientOutput();
			registerPatient.registerPatient();
			loadMainScreenContent();
		}
		else if(sel == 3) {
			System.exit(0);
		}
		else {
			consoleObj.printError(CommonErrors.INVALID_SELECTION);
			loadMainScreenContent();
		}
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.LOGIN);
		selectionOptions.add(ScreenFields.SIGNUP);
		selectionOptions.add(ScreenFields.EXIT);
		return selectionOptions;
	}
}
