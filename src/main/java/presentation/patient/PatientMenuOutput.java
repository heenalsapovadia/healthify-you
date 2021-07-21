package presentation.patient;

import java.util.ArrayList;
import java.util.List;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Loads patient dashboard in the application.
 * </pre>
 *
 */
public class PatientMenuOutput {
	
	private static class PatientMenuOutputHelper {
		private static final PatientMenuOutput instance = new PatientMenuOutput();
	}
	
	public static PatientMenuOutput getInstance() {
		return PatientMenuOutputHelper.instance;
	}
	
	public void displayOutput() {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.patientDashboard);
		loadScreenOptions(consoleObj);
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.book);
		selectionOptions.add(ScreenFields.invoices);
		selectionOptions.add(ScreenFields.requestMedication);
		selectionOptions.add(ScreenFields.vouchers);
		selectionOptions.add(ScreenFields.logout);
		return selectionOptions;
	}
	
	private int loadScreenOptions(PrintToConsole consoleObj) {
		List<String> selectionOptions = getSelectionOptions();
		int sel = consoleObj.printSelection(selectionOptions);
		if(sel == 1) {
			//make a booking
			BookingDashboard bookingDashboard = new BookingDashboard();
			bookingDashboard.displayOutput();
			sel = loadScreenOptions(consoleObj);
		}
		else if(sel == 2) {
			//invoices
		}
		else if(sel == 3) {
			//request medication
		}
		else if(sel == 4) {
			RedeemableVoucherOutput redeemableVoucherOutput = new RedeemableVoucherOutput();
			redeemableVoucherOutput.displayOutput();
			sel = loadScreenOptions(consoleObj);
		}
		else if(sel == 5) {
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
