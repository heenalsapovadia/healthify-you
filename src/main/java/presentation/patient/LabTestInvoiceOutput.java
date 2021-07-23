package presentation.patient;

import java.sql.Date;
import java.util.List;

import persistence.patient.model.Invoice;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.util.PatientInvoiceUtil;
import persistence.patient.utilImpl.PatientInvoiceUtilImpl;
import presentation.common.CommonConstants;
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
	
	/**
	 * <pre>
	 * Fetches data from database using DAO and prints output to console.
	 * </pre>
	 * 
	 * @param date
	 */
	public void displayInvoice(Date date) {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.LAB_TEST_RECEIPT);
		PatientInvoiceUtil invoiceUtil = new PatientInvoiceUtilImpl();
		Invoice invoice = invoiceUtil.getGenericInvoiceDetails();
		invoice = invoiceUtil.generateLabCheckInvoice(date.toString(), invoice);
		loadScreen(consoleObj, invoice);
	}
	
	/**
	 * <pre>
	 * Loads details on the screen.
	 * </pre>
	 * 
	 * @param consoleObj
	 * @param invoice
	 */
	private void loadScreen(PrintToConsole consoleObj, Invoice invoice) {
		System.out.println(ScreenFields.PATIENT_NAME+CommonConstants.commonTextSeparator+invoice.getPatientName());
		System.out.println(ScreenFields.address+CommonConstants.singleSpace+CommonConstants.commonTextSeparator+invoice.getAddress());
		System.out.println(ScreenFields.contact+CommonConstants.commonTextSeparator+invoice.getContactNumber());
		System.out.println(ScreenFields.AGE_SEX+CommonConstants.singleTab+CommonConstants.commonTextSeparator+invoice.getAge()
			+CommonConstants.SLASH+invoice.getGender());
		consoleObj.printLineSeparator();
		List<LabCheckBooking> labCheckBookings = invoice.getLabCheckBookingList();
		double total = 0d;
		for(int i=0; i<labCheckBookings.size(); i++) {
			System.out.println(ScreenFields.APPOINTMENT_ID+CommonConstants.commonTextSeparator+labCheckBookings.get(i).getAppointment_id());
			System.out.println(ScreenFields.dateTime+CommonConstants.commonTextSeparator+labCheckBookings.get(i).getBooked_for_date());
			System.out.println(ScreenFields.LAB_TEST_NAME+CommonConstants.singleTab+CommonConstants.COLON+CommonConstants.DOUBLE_TAB
					+ invoice.getLabCheckMap().get(labCheckBookings.get(i).getHealthcheck_id()));
			consoleObj.printLineSeparator();
			total += invoice.getPaymentMap().get(labCheckBookings.get(i).getBilling_id()).getBill_amount();
		}
		System.out.println(ScreenFields.BILL_AMT+CommonConstants.DOUBLE_TAB+CommonConstants.singleTab+total);
		consoleObj.printLineSeparator();
		System.out.println(ScreenFields.CREATED_ON+CommonConstants.commonTextSeparator+invoice.getOriginalDatetime());
		consoleObj.printLineSeparator();
	}
}
