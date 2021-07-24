/**
 * 
 */
package presentation.patient;

/**
 * <pre>
 * Displays following invoices for patient.
 * 	1. Dcotor's appointment
 * 	2. Medication Invoice
 * 	3. Immunization Invoice
 * 	4. Lab Test Invoice
 * 
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

public class InvoiceOutput {
	
	public void displayInvoice(){
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.INVOICES);
		List<String> selectionOptions = getSelectionOptions();
		int sel;
		do {
			sel = consoleObj.printSelection(selectionOptions);
			switch(sel) {
				case 1: AppointmentInvoiceOutput output = new AppointmentInvoiceOutput();
						output.displayInvoice(parseDateInput(consoleObj, new Scanner(System.in)));
						break;
				case 2: PharmaInvoiceOutput invoiceOutput = new PharmaInvoiceOutput();
						invoiceOutput.displayInvoice(parseDateInput(consoleObj, new Scanner(System.in)));
						break;
				case 3: ImmunizationInvoiceOutput immunizationOutput = new ImmunizationInvoiceOutput();
						immunizationOutput.displayInvoice(parseDateInput(consoleObj, new Scanner(System.in)));
						break;
				case 4: LabTestInvoiceOutput labCheckInvoice = new LabTestInvoiceOutput();
						labCheckInvoice.displayInvoice(parseDateInput(consoleObj, new Scanner(System.in)));
						break;
				case 5: return;
				default: consoleObj.printError(CommonErrors.invalidSelection);
			}
		}
		while(sel != 5);
	}
	
	private Date parseDateInput(PrintToConsole consoleObj, Scanner sc) {
		System.out.println(ScreenFields.DATEINPUT);
		Date date = null;
		try {
			date = Date.valueOf(sc.next());
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			System.out.println("Loading all receipts for the date "+((cal.get(Calendar.YEAR))+"-"+(cal.get(Calendar.MONTH)+1))+"-"+(cal.get(Calendar.DAY_OF_MONTH)));
		}
		catch(IllegalArgumentException e) {
			consoleObj.printError(CommonErrors.invalidDateFormat);
			date = parseDateInput(consoleObj, new Scanner(System.in));
		}
		return date;
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.APPT_BY_DATE);
		selectionOptions.add(ScreenFields.PHARMA_BY_DATE);
		selectionOptions.add(ScreenFields.IMMUNIZATION_BY_DATE);
		selectionOptions.add(ScreenFields.LAB_TEST_BY_DATE);
		selectionOptions.add(ScreenFields.EXIT);
		return selectionOptions;
	}
}
