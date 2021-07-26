package presentation.doctor;

import presentation.common.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DoctorMenuOutput {

	private static final Logger LOGGER = Logger.getLogger(DoctorMenuOutput.class.getName());

	private PrintToConsole consoleObj = PrintToConsole.getInstance();

	private DoctorMenuOutput() {
	}

	private static DoctorMenuOutput doctorMenuOutput;

	public static DoctorMenuOutput getInstance() {
		if (doctorMenuOutput == null)
			doctorMenuOutput = new DoctorMenuOutput();
		return doctorMenuOutput;
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
					consoleObj.printError(CommonErrors.invalidSelection);
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
