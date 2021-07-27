package presentation.patient;

import java.sql.Date;
import java.util.List;
import persistence.patient.model.Invoice;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.util.PatientInvoiceUtil;
import persistence.patient.utilImpl.PatientInvoiceUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Generates lab test invoice by interacting 
 * with database.
 * </pre>
 * @author Gurleen Saluja
 *
 */
public class LabTestInvoiceOutput {
	
	private PrintToConsole consoleObj;
	
	public LabTestInvoiceOutput() {
		consoleObj = PrintToConsole.getInstance();
	}
	
	/**
	 * <pre>
	 * Fetches data from database using DAO and prints output to console.
	 * </pre>
	 * 
	 * @param date
	 */
	public void displayInvoice(Date date) {
		PatientInvoiceUtil invoiceUtil = new PatientInvoiceUtilImpl();
		Invoice invoice = invoiceUtil.getGenericInvoiceDetails();
		invoice = invoiceUtil.generateLabCheckInvoice(date.toString(), invoice);
		List<LabCheckBooking> labCheckBookings = invoice.getLabCheckBookingList();
		if(labCheckBookings != null && !labCheckBookings.isEmpty()) {
			consoleObj.printHeader(ScreenTitles.LAB_TEST_RECEIPT);
			loadScreen(invoice, labCheckBookings);
		}
		else {
			System.err.println(CommonErrors.NO_RECEIPTS);
		}
	}
	
	/**
	 * <pre>
	 * Loads details on the screen.
	 * </pre>
	 * 
	 * @param consoleObj
	 * @param invoice
	 */
	private void loadScreen(Invoice invoice, List<LabCheckBooking> labCheckBookings) {
		System.out.println(ScreenFields.PATIENT_NAME+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getPatientName());
		System.out.println(ScreenFields.ADDRESS+CommonConstants.SINGLE_SPACE+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getAddress());
		System.out.println(ScreenFields.CONTACT+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getContactNumber());
		System.out.println(ScreenFields.AGE_SEX+CommonConstants.SINGLE_TAB+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getAge()
			+CommonConstants.SLASH+invoice.getGender());
		consoleObj.printLineSeparator();
		double total = 0d;
		for(int i=0; i<labCheckBookings.size(); i++) {
			System.out.println(ScreenFields.APPOINTMENT_ID+CommonConstants.COMMON_TEXT_SEPARATOR+labCheckBookings.get(i).getAppointmentId());
			System.out.println(ScreenFields.DATETIME+CommonConstants.COMMON_TEXT_SEPARATOR+labCheckBookings.get(i).getBookedForDate());
			System.out.println(ScreenFields.LAB_TEST_NAME+CommonConstants.SINGLE_TAB+CommonConstants.COLON+CommonConstants.DOUBLE_TAB
					+ invoice.getLabCheckMap().get(labCheckBookings.get(i).getHealthcheckId()));
			consoleObj.printLineSeparator();
			total += invoice.getPaymentMap().get(labCheckBookings.get(i).getBillingId()).getBill_amount();
		}
		System.out.println(ScreenFields.BILL_AMT+CommonConstants.DOUBLE_TAB+CommonConstants.SINGLE_TAB+total);
		consoleObj.printLineSeparator();
		System.out.println(ScreenFields.CREATED_ON+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getOriginalDatetime());
		consoleObj.printLineSeparator();
	}
}
