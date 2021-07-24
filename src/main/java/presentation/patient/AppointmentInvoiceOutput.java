package presentation.patient;

import java.sql.Date;
import java.util.List;
import persistence.admin.model.PaymentMode;
import persistence.doctor.model.Appointment;
import persistence.patient.model.Invoice;
import persistence.patient.util.PatientInvoiceUtil;
import persistence.patient.utilImpl.PatientInvoiceUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * @author Gurleen Saluja
 *
 */
public class AppointmentInvoiceOutput {
	
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
		invoice = invoiceUtil.generateAppointmentInvoice(date.toString(), invoice);
		loadScreen(consoleObj, invoice);
	}
	
	/**
	 * <pre>
	 * Loads common header on the screen.
	 * </pre>
	 * 
	 * @param consoleObj
	 * @param invoice
	 */
	private void loadScreen(PrintToConsole consoleObj, Invoice invoice) {
		System.out.println(ScreenFields.PATIENT_NAME+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getPatientName());
		System.out.println(ScreenFields.ADDRESS+CommonConstants.SINGLE_SPACE+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getAddress());
		System.out.println(ScreenFields.CONTACT+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getContactNumber());
		System.out.println(ScreenFields.AGE_SEX+CommonConstants.SINGLE_TAB+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getAge()
			+CommonConstants.SLASH+invoice.getGender());
		consoleObj.printLineSeparator();
		List<Appointment> appointments = invoice.getAppointmentList();
		for(int i=0; i<appointments.size(); i++) {
			System.out.println(ScreenFields.APPOINTMENT_ID+CommonConstants.COMMON_TEXT_SEPARATOR+appointments.get(i).getAppointment_id());
			System.out.println(ScreenFields.DATETIME+CommonConstants.COMMON_TEXT_SEPARATOR+appointments.get(i).getBooked_for_date());
			System.out.println(ScreenFields.MOP+CommonConstants.COMMON_TEXT_SEPARATOR+PaymentMode.getMop(invoice.getPaymentMap().get(appointments.get(i).getBilling_id()).getCurrentPaymentMode().toString()));
			System.out.println(ScreenFields.DOCTOR_NAME+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getDoctorDetail().get(appointments.get(i).getDoctor_id()));
			System.out.println(ScreenFields.CREATED_ON+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getOriginalDatetime());
			consoleObj.printLineSeparator();
			System.out.println(ScreenFields.BILL_AMT+CommonConstants.DOUBLE_TAB+invoice.getPaymentMap().get(appointments.get(i).getBilling_id()).getBill_amount());
			consoleObj.printLineSeparator();
		}
	}
}
