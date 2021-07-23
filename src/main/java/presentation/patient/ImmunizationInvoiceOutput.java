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
		consoleObj.printHeader(ScreenTitles.IMMUNIZATION_RECEIPT);
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
		System.out.println(ScreenFields.PATIENT_NAME+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getPatientName());
		System.out.println(ScreenFields.ADDRESS+CommonConstants.SINGLE_SPACE+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getAddress());
		System.out.println(ScreenFields.CONTACT+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getContactNumber());
		System.out.println(ScreenFields.AGE_SEX+CommonConstants.SINGLE_TAB+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getAge()
			+CommonConstants.SLASH+invoice.getGender());
		System.out.println(ScreenFields.CREATED_ON+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getOriginalDatetime());
		consoleObj.printLineSeparator();
		for(Map.Entry<Integer, String> entry: invoice.getVaccineMap().entrySet()) {
			System.out.println(ScreenFields.VACCINE_NAME+CommonConstants.COMMON_TEXT_SEPARATOR+entry.getValue());
		}
		consoleObj.printLineSeparator();
	}
}
