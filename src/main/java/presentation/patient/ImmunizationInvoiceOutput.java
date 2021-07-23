package presentation.patient;

import java.sql.Date;
import java.util.Map;

import persistence.patient.model.Invoice;
import persistence.patient.util.PatientInvoiceUtil;
import persistence.patient.utilImpl.PatientInvoiceUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 *  Generates immunization invoice by interacting 
 *  with database.
 * </pre>
 * @author Gurleen Saluja
 *
 */
public class ImmunizationInvoiceOutput {
	
	/**
	 * <pre>
	 * Fetches data from database using DAO and prints output to console.
	 * </pre>
	 * 
	 * @param date
	 */
	public void displayInvoice(Date date) {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.APPOINTMENT_RECEIPT);
		PatientInvoiceUtil invoiceUtil = new PatientInvoiceUtilImpl();
		Invoice invoice = invoiceUtil.getGenericInvoiceDetails();
		invoice = invoiceUtil.generateImmunizationInvoice(date.toString(), invoice);
		loadTableHeader(consoleObj, invoice);
	}
	
	/**
	 * <pre>
	 * Loads common header on the screen.
	 * </pre>
	 * 
	 * @param invoice
	 */
	private void loadTableHeader(PrintToConsole consoleObj, Invoice invoice) {
		System.out.println(ScreenFields.PATIENT_NAME+CommonConstants.commonTextSeparator+invoice.getPatientName());
		System.out.println(ScreenFields.address+CommonConstants.singleSpace+CommonConstants.commonTextSeparator+invoice.getAddress());
		System.out.println(ScreenFields.contact+CommonConstants.commonTextSeparator+invoice.getContactNumber());
		System.out.println(ScreenFields.AGE_SEX+CommonConstants.singleTab+CommonConstants.commonTextSeparator+invoice.getAge()
			+CommonConstants.SLASH+invoice.getGender());
		System.out.println(ScreenFields.CREATED_ON+CommonConstants.commonTextSeparator+invoice.getOriginalDatetime());
		consoleObj.printLineSeparator();
		for(Map.Entry<Integer, String> entry: invoice.getVaccineMap().entrySet()) {
			System.out.println(ScreenFields.VACCINE_NAME+CommonConstants.commonTextSeparator+entry.getValue());
		}
		consoleObj.printLineSeparator();
	}
}
