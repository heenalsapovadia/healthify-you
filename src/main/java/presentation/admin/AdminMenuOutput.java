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
	
private AdminMenuOutput() {}
	
	private static class AdminMenuOutputHelper {
		private static final AdminMenuOutput instance = new AdminMenuOutput();
	}
	
	public static AdminMenuOutput getInstance() {
		return AdminMenuOutputHelper.instance;
	}
	
	public void displayOutput() {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.adminDashboard);
		loadScreenOptions(consoleObj);
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.getInvoices);
		selectionOptions.add(ScreenFields.getRecommendations);
		selectionOptions.add(ScreenFields.registerDoctor);
		selectionOptions.add(ScreenFields.logout);
		return selectionOptions;
	}
	
	private int loadScreenOptions(PrintToConsole consoleObj) {
		List<String> selectionOptions = getSelectionOptions();
		int sel = consoleObj.printSelection(selectionOptions);
		if(sel == 1) {
			InvoiceOutput invoiceOutput = new InvoiceOutput();
			invoiceOutput.displayInvoice();
			sel = loadScreenOptions(consoleObj);
		}
		else if(sel == 2) {
			//add code for blood bank output here
		}
		
		else if(sel == 3) {
			DoctorRegistrationOutput doctorRegistrationOutput = new DoctorRegistrationOutput();
			doctorRegistrationOutput.registerDoctor();
			sel = loadScreenOptions(consoleObj);
		}

		else if(sel == 4) {
			System.out.println(ScreenFields.logoutMessage);
			System.out.println(ScreenFields.applicationTerminationMessage);
			System.exit(0);
		}
		else {
			consoleObj.printError(CommonErrors.invalidSelection);
			sel = loadScreenOptions(consoleObj);
		}
		return sel;
	}
}
