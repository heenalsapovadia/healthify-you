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
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Generates medication invoice for patient by interacting 
 * with database.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PharmaInvoiceOutput {
	
	private PrintToConsole consoleObj;
	
	public PharmaInvoiceOutput() {
		consoleObj = PrintToConsole.getInstance();
	}
	
	/**
	 * <pre>
	 * Fetches data from database using Utility and prints output to console.
	 * </pre>
	 * 
	 * @param date
	 */
	public void displayInvoice(Date date) {
		PatientInvoiceUtil invoiceUtil = new PatientInvoiceUtilImpl();
		Invoice invoice = invoiceUtil.getGenericInvoiceDetails();
		invoice = invoiceUtil.generatePharmaInvoice(date.toString(), invoice);
		PharmaInvoiceUtil pharmaInvoiceUtil = new PharmaInvoiceUtilImpl();
		List<Double> pricesList = new ArrayList<>();
		List<PharmaInvoice> invoicesList = invoice.getPharmaInvoiceList();
		if(invoicesList != null && !invoicesList.isEmpty()) {
			consoleObj.printHeader(ScreenTitles.PHARMACY_RECEIPT);
			loadScreen(invoice);
			for(int i=0; i<invoicesList.size(); i++) {
				System.out.println(
						invoicesList.get(i).getItemName()+CommonConstants.SINGLE_SPACE
						+ invoicesList.get(i).getItemDosage()+CommonConstants.SINGLE_SPACE
						+ invoicesList.get(i).getItemManufacturer()+"\t\t\t\t"
						+ invoicesList.get(i).getOrderedQuantity()+"\t\t"
						+ invoicesList.get(i).getExpiryDate()+"\t\t"
						+ invoice.getPaymentMap().get(invoice.getBillId()).getBill_amount()+CommonConstants.SINGLE_SPACE);
				pricesList.add(invoice.getPaymentMap().get(invoice.getBillId()).getBill_amount());
			}
			consoleObj.printLineSeparator();
			Double grandTotal = pharmaInvoiceUtil.calculateGrandTotalAmount(pricesList);
			System.out.println(ScreenFields.BILL_AMT+CommonConstants.COMMON_TEXT_SEPARATOR+grandTotal);
			consoleObj.printLineSeparator();
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
	private void loadScreen(Invoice invoice) {
		System.out.println(ScreenFields.PRESCRIPTION_ID+CommonConstants.SINGLE_TAB+CommonConstants.COLON
				+CommonConstants.DOUBLE_TAB+invoice.getPrescriptionId());
		System.out.println(ScreenFields.PATIENT_NAME+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getPatientName());
		System.out.println(ScreenFields.ADDRESS+CommonConstants.SINGLE_SPACE+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getAddress());
		System.out.println(ScreenFields.CONTACT+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getContactNumber());
		for(Map.Entry<Integer, PaymentInterface> entry: invoice.getPaymentMap().entrySet()) {
			String output = ScreenFields.MOP+CommonConstants.COMMON_TEXT_SEPARATOR
					+ PaymentMode.getMop(entry.getValue().getCurrentPaymentMode().toString()) + ", ";
			System.out.print(output.substring(0, output.length()-2));
		}
		consoleObj.printSingleNewLine();
		System.out.println(ScreenFields.CREATED_ON+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getOriginalDatetime());
		consoleObj.printLineSeparator();
		System.out.println(
				ScreenFields.DESCRIPTION+CommonConstants.SINGLE_SPACE+ScreenFields.DESCRIPTION_EXTRAS+CommonConstants.SINGLE_TAB
				+ ScreenFields.QUANTITY+CommonConstants.SINGLE_TAB
				+ ScreenFields.EXPIRY_DATE+CommonConstants.SINGLE_TAB
				+ ScreenFields.TOTAL+CommonConstants.SINGLE_TAB);
		consoleObj.printLineSeparator();
	}
}
