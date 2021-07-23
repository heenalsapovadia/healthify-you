/**
 * 
 */
package presentation.patient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import persistence.admin.dao.PharmaInvoiceDAO;
import persistence.admin.daoImpl.PharmaInvoiceDAOImpl;
import persistence.admin.model.PaymentMode;
import persistence.admin.model.PharmaInvoice;
import persistence.admin.util.PharmaInvoiceUtil;
import persistence.admin.utilImpl.PharmaInvoiceUtilImpl;
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
	 * @param invoice
	 */
	private void loadScreen(PrintToConsole consoleObj, Invoice invoice) {
		System.out.println(ScreenFields.PATIENT_NAME+CommonConstants.commonTextSeparator+invoice.getPatientName());
		System.out.println(ScreenFields.address+CommonConstants.singleSpace+CommonConstants.commonTextSeparator+invoice.getAddress());
		System.out.println(ScreenFields.contact+CommonConstants.commonTextSeparator+invoice.getContactNumber());
		System.out.println(ScreenFields.AGE_SEX+CommonConstants.singleTab+CommonConstants.commonTextSeparator+invoice.getAge()
			+CommonConstants.SLASH+invoice.getGender());
		consoleObj.printLineSeparator();
		List<Appointment> appointments = invoice.getAppointmentList();
		for(int i=0; i<appointments.size(); i++) {
			System.out.println(ScreenFields.APPOINTMENT_ID+CommonConstants.commonTextSeparator+appointments.get(i).getAppointment_id());
			System.out.println(ScreenFields.dateTime+CommonConstants.commonTextSeparator+appointments.get(i).getBooked_for_date());
			System.out.println(ScreenFields.mop+CommonConstants.commonTextSeparator+PaymentMode.getMop(invoice.getPaymentMap().get(appointments.get(i).getBilling_id()).getCurrentPaymentMode().toString()));
			System.out.println(ScreenFields.DOCTOR_NAME+CommonConstants.commonTextSeparator+invoice.getDoctorDetail().get(appointments.get(i).getDoctor_id()));
			System.out.println(ScreenFields.CREATED_ON+CommonConstants.commonTextSeparator+invoice.getOriginalDatetime());
			consoleObj.printLineSeparator();
			System.out.println(ScreenFields.BILL_AMT+CommonConstants.commonTextSeparator+invoice.getPaymentMap().get(appointments.get(i).getBilling_id()).getBill_amount());
			consoleObj.printLineSeparator();
		}
	}
}
