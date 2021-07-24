/**
 * 
 */
package persistence.patient.util;

import persistence.patient.model.Invoice;

/**
 * @author Gurleen Saluja
 *
 */
public interface PatientInvoiceUtil {
	
	/**
	 * <pre>
	 * Populates patient details for invoice.
	 * </pre>
	 * 
	 * @return invoice
	 */
	Invoice getGenericInvoiceDetails();
	
	/**
	 * <pre>
	 * Populates doctor's appointment details.
	 * </pre>
	 * 
	 * @param date
	 * @param invoice
	 * @return invoice
	 */
	Invoice generateAppointmentInvoice(String date, Invoice invoice);
	
	/**
	 * <pre>
	 * Populates lab test booking details.
	 * </pre>
	 * 
	 * @param date
	 * @param invoice
	 * @return
	 */
	Invoice generateLabCheckInvoice(String date, Invoice invoice);
	
	/**
	 * <pre>
	 * Populates medication details.
	 * </pre>
	 * 
	 * @param date
	 * @param invoice
	 * @return
	 */
	Invoice generatePharmaInvoice(String date, Invoice invoice);
	
	/**
	 * <pre>
	 * Populates immunization appointment details.
	 * </pre>
	 * 
	 * @param date
	 * @param invoice
	 * @return
	 */
	Invoice generateImmunizationInvoice(String date, Invoice invoice);
}
