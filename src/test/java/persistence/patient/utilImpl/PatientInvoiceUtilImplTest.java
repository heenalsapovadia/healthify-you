package persistence.patient.utilImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import persistence.patient.model.Invoice;
import persistence.patient.model.Patient;
import persistence.patient.util.PatientInvoiceUtil;
import presentation.startup.DatabaseConnection;

/**
 * <pre>
 * Test to set patient details in an invoice.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
class PatientInvoiceUtilImplTest {
	
	private PatientInvoiceUtil invoiceUtil;
	
	public PatientInvoiceUtilImplTest(){
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
	}

	@Test
	void testGetGenericInvoiceDetails() {
		invoiceUtil = new PatientInvoiceUtilImpl();
		Invoice invoice = invoiceUtil.getGenericInvoiceDetails();
		assertEquals("Gur S", invoice.getPatientName());
		assertEquals("Halifax", invoice.getAddress());
		assertEquals(22, invoice.getAge());
		assertEquals("9024567890", invoice.getContactNumber());
		assertEquals('F', invoice.getGender());
	}
}
