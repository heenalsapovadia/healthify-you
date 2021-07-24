package presentation.patient;

import java.sql.SQLException;
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
	
	public void displayOutput() throws SQLException {
		PrintToConsole consoleObj = PrintToConsole.getInstance();

		loadScreenOptions(consoleObj);
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.BOOK);
		selectionOptions.add(ScreenFields.INVOICES);
		selectionOptions.add(ScreenFields.REQUEST_MEDICATION);
		selectionOptions.add(ScreenFields.VIEW_REPORTS);
		selectionOptions.add(ScreenFields.VOUCHERS);
		selectionOptions.add(ScreenFields.LOGOUT);
		return selectionOptions;
	}
	
	private int loadScreenOptions(PrintToConsole consoleObj) throws SQLException {
		consoleObj.printHeader(ScreenTitles.PATIENT_DASHBOARD);
		List<String> selectionOptions = getSelectionOptions();
		int sel = consoleObj.printSelection(selectionOptions);
		if(sel == 1) {
			//make a booking
			BookingDashboard bookingDashboard = new BookingDashboard();
			bookingDashboard.displayOutput();
			loadScreenOptions(consoleObj);
			sel = loadScreenOptions(consoleObj);

		}
		else if(sel == 2) {
			//invoices
			InvoiceOutput invoiceOutput = new InvoiceOutput();
			invoiceOutput.displayInvoice();
			sel = loadScreenOptions(consoleObj);
		}
		else if(sel == 3) {
			//request medication
			RequestMedicationOutput requestMedicationDetails = new RequestMedicationOutput();
			requestMedicationDetails.requestMedicationDetails();
			sel = loadScreenOptions(consoleObj);
		}
		else if(sel == 4) {
			ViewReportsOutput viewReports = new ViewReportsOutput();
			viewReports.displayOutput();
			sel = loadScreenOptions(consoleObj);
		}
		else if(sel == 5) {
			RedeemableVoucherOutput redeemableVoucherOutput = new RedeemableVoucherOutput();
			redeemableVoucherOutput.displayOutput();
			sel = loadScreenOptions(consoleObj);
		}
		else if(sel == 6) {
			System.out.println(ScreenFields.LOGOUT_MESSAGE);
			System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
			System.exit(0);
		}
		else {
			consoleObj.printError(CommonErrors.invalidSelection);
			sel = loadScreenOptions(consoleObj);
		}
		return sel;
	}
}
