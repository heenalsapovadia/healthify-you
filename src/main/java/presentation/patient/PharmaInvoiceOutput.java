/**
 * 
 */
package presentation.patient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import persistence.admin.model.PaymentMode;
import persistence.admin.model.PharmaInvoice;
import persistence.admin.util.PharmaInvoiceUtil;
import persistence.admin.utilImpl.PharmaInvoiceUtilImpl;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.patient.model.Invoice;
import persistence.patient.util.PatientInvoiceUtil;
import persistence.patient.utilImpl.PatientInvoiceUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Generates medication invoice by interacting 
 * with database.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PharmaInvoiceOutput {
	
	/**
	 * <pre>
	 * Fetches data from database using DAO and prints output to console.
	 * </pre>
	 * 
	 * @param date
	 */
	public void displayInvoice(Date date) {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.PHARMACY_RECEIPT);
		PatientInvoiceUtil invoiceUtil = new PatientInvoiceUtilImpl();
		Invoice invoice = invoiceUtil.getGenericInvoiceDetails();
		invoice = invoiceUtil.generatePharmaInvoice(date.toString(), invoice);
		PharmaInvoiceUtil pharmaInvoiceUtil = new PharmaInvoiceUtilImpl();
		List<Double> pricesList = new ArrayList<>();
		loadScreen(consoleObj, invoice);
		for(PharmaInvoice pharmInvoice: invoice.getPharmaInvoiceList()) {
			Double totalPrice = pharmaInvoiceUtil.calculateTotalAmount(pharmInvoice.getItemUnitPrice(), pharmInvoice.getOrderedQuantity());
			System.out.println(
					pharmInvoice.getItemName()+CommonConstants.singleSpace
					+ pharmInvoice.getItemDosage()+CommonConstants.singleSpace
					+ pharmInvoice.getItemManufacturer()+"\t\t\t\t"
					+ pharmInvoice.getOrderedQuantity()+"\t\t"
					+ pharmInvoice.getExpiryDate()+"\t\t"
					+ totalPrice+CommonConstants.singleSpace);
			pricesList.add(totalPrice);
		}
		consoleObj.printLineSeparator();
		Double grandTotal = pharmaInvoiceUtil.calculateGrandTotalAmount(pricesList);
		System.out.println(ScreenFields.BILL_AMT+CommonConstants.commonTextSeparator+grandTotal);
		consoleObj.printLineSeparator();
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
		System.out.println(ScreenFields.PRESCRIPTION_ID+CommonConstants.singleTab+CommonConstants.COLON+CommonConstants.DOUBLE_TAB+invoice.getPrescriptionId());
		System.out.println(ScreenFields.PATIENT_NAME+CommonConstants.commonTextSeparator+invoice.getPatientName());
		System.out.println(ScreenFields.address+CommonConstants.singleSpace+CommonConstants.commonTextSeparator+invoice.getAddress());
		System.out.println(ScreenFields.contact+CommonConstants.commonTextSeparator+invoice.getContactNumber());
		for(Map.Entry<Integer, PaymentInterface> entry: invoice.getPaymentMap().entrySet()) {
			String output = ScreenFields.mop+CommonConstants.commonTextSeparator
					+ PaymentMode.getMop(entry.getValue().getCurrentPaymentMode().toString()) + ", ";
			System.out.print(output.substring(0, output.length()-2));
		}
		consoleObj.printSingleNewLine();
		System.out.println(ScreenFields.CREATED_ON+CommonConstants.commonTextSeparator+invoice.getOriginalDatetime());
		consoleObj.printLineSeparator();
		System.out.println(
				ScreenFields.description+CommonConstants.singleSpace+ScreenFields.descriptionExtras+CommonConstants.singleTab
				+ ScreenFields.quantity+CommonConstants.singleTab
				+ ScreenFields.EXPIRY_DATE+CommonConstants.singleTab
				+ ScreenFields.total+CommonConstants.singleTab);
		consoleObj.printLineSeparator();
	}
}
