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
	
	private PrintToConsole consoleObj;
	
	public PatientMenuOutput() {
		consoleObj = PrintToConsole.getInstance();
	}
	
	public int displayOutput() throws SQLException {
		consoleObj.printHeader(ScreenTitles.PATIENT_DASHBOARD);
		List<String> selectionOptions = getSelectionOptions();
		int sel = consoleObj.printSelection(selectionOptions);
		if(sel == 1) {
			BookingDashboard bookingDashboard = new BookingDashboard();
			bookingDashboard.displayOutput();
			displayOutput();
			sel = displayOutput();
		}
		else if(sel == 2) {
			InvoiceOutput invoiceOutput = new InvoiceOutput();
			invoiceOutput.displayInvoice();
			sel = displayOutput();
		}
		else if(sel == 3) {
			RequestMedicationOutput requestMedicationDetails = new RequestMedicationOutput();
			requestMedicationDetails.requestMedicationDashboard();
			sel = displayOutput();
		}
		else if(sel == 4) {
			ViewReportsOutput viewReports = new ViewReportsOutput();
			viewReports.displayOutput();
			sel = displayOutput();
		}
		else if(sel == 5) {
			RedeemableVoucherOutput redeemableVoucherOutput = new RedeemableVoucherOutput();
			redeemableVoucherOutput.displayOutput();
			sel = displayOutput();
		}
		else if(sel == 6) {
			consoleObj.flushResources();
			System.out.println(ScreenFields.LOGOUT_MESSAGE);
			System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
			System.exit(0);
		}
		else {
			consoleObj.printError(CommonErrors.INVALID_SELECTION);
			sel = displayOutput();
		}
		return sel;
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
}
