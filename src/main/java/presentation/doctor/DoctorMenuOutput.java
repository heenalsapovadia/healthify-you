package presentation.doctor;

import presentation.common.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Loads Doctor dashboard in the application.
 * </pre>
 *
 * @author Heenal Sapovadia
 *
 */
public class DoctorMenuOutput {

	private PrintToConsole consoleObj;

	public DoctorMenuOutput(){
		consoleObj = PrintToConsole.getInstance();
	}

	public void displayOutput() {
		List<String> selectionOptions = getSelectionOptions();
		while (true) {
			consoleObj.printHeader(ScreenTitles.DOCTOR_DASHBOARD);
			int option = consoleObj.printSelection(selectionOptions);
			switch (option) {
				case 1:
					PrescribeMedicineOutput prescribeMedicineOutput = new PrescribeMedicineOutput();
					prescribeMedicineOutput.prescribeMedication();
					break;
				case 2:
					ScheduledAppointmentsOutput scheduledAppointmentsOutput = new ScheduledAppointmentsOutput();
					scheduledAppointmentsOutput.scheduledAppointmentsDetails();
					break;
				case 3:
					consoleObj.flushResources();
					System.out.println(ScreenFields.LOGOUT_MESSAGE);
					System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
					return;
				default:
					consoleObj.printError(CommonErrors.INVALID_SELECTION);
			}
		}
	}

	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.PRESCRIBE_MEDICATION);
		selectionOptions.add(ScreenFields.VIEW_APPOINTMENTS);
		selectionOptions.add(ScreenFields.LOGOUT);
		return selectionOptions;
	}
}
