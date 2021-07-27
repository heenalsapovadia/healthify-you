package presentation.admin;

import java.util.ArrayList;
import java.util.List;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Loads admin dashboard in the application.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class AdminMenuOutput {
	
	private PrintToConsole consoleObj;

	public AdminMenuOutput() {
		consoleObj = PrintToConsole.getInstance();
	}

	public int displayOutput() {
		consoleObj.printHeader(ScreenTitles.ADMIN_DASHBOARD);
		List<String> selectionOptions = getSelectionOptions();
		int sel = consoleObj.printSelection(selectionOptions);
		
		if (sel == 1) {
			InvoiceOutput invoiceOutput = new InvoiceOutput();
			invoiceOutput.displayInvoice();
			sel = displayOutput();
		} else if (sel == 2) {
			BloodBankRecommendationOutput bloodBankRecommendationOutput = new BloodBankRecommendationOutput();
			bloodBankRecommendationOutput.getBloodBankRecommendations();
			sel = displayOutput();
		} else if (sel == 3) {
			DoctorRegistrationOutput doctorRegistrationOutput = new DoctorRegistrationOutput();
			doctorRegistrationOutput.registerDoctor();
			sel = displayOutput();
		} else if (sel == 4) {
			ImmunizationDashboard immunizationdashboard = ImmunizationDashboard.getInstance();
			immunizationdashboard.displayOutput();
			sel = displayOutput();
		} else if (sel == 5) {
			System.out.println(ScreenFields.LOGOUT_MESSAGE);
			System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
			consoleObj.flushResources();
			System.exit(0);
		} else {
			consoleObj.printError(CommonErrors.INVALID_SELECTION);
			sel = displayOutput();
		}
		
		return sel;
	}

	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.GET_INVOICES);
		selectionOptions.add(ScreenFields.GET_RECOMMENDATIONS);
		selectionOptions.add(ScreenFields.REGISTER_DOCTOR);
		selectionOptions.add(ScreenFields.IMMUNIZATION_MANAGEMENT);
		selectionOptions.add(ScreenFields.LOGOUT);
		return selectionOptions;
	}
}
