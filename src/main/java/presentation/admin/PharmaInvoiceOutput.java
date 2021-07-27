package presentation.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import persistence.admin.model.PaymentMode;
import persistence.admin.model.PharmaInvoice;
import persistence.admin.util.PharmaInvoiceUtil;
import persistence.admin.utilImpl.PharmaInvoiceUtilImpl;
import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Prints the invoice to console.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PharmaInvoiceOutput {
	
	private PrintToConsole consoleObj;
	
	/**
	 * <pre>
	 * Fetches data from database using DAO and prints output to console.
	 * </pre>
	 * 
	 * @param date
	 */
	public void displayInvoice(Date date) {
		PharmaInvoiceUtil invoiceUtil = new PharmaInvoiceUtilImpl();
		Map<String, List<PharmaInvoice>> invoicesMap = invoiceUtil.fetchMapFromDatabase(date);
		List<Double> pricesList;
		consoleObj = PrintToConsole.getInstance();
		if(invoicesMap != null && !invoicesMap.isEmpty()) {
			consoleObj.printHeader(ScreenTitles.PHARMA_INVOICE);
			for(Map.Entry<String, List<PharmaInvoice>> entry: invoicesMap.entrySet()) {
				loadTableHeader(entry.getValue().get(0), fetchAllReceipts(entry.getValue()));
				pricesList = new ArrayList<>();
				for(PharmaInvoice invoice: entry.getValue()) {
					Double totalPrice = invoiceUtil.calculateTotalAmount(invoice.getItemUnitPrice(), invoice.getItemQuantity());
					System.out.println(
							invoice.getItemName()+CommonConstants.SINGLE_SPACE
							+ invoice.getItemDosage()+CommonConstants.SINGLE_SPACE
							+ invoice.getItemManufacturer()+"\t\t\t\t"
							+ invoice.getItemQuantity()+"\t\t"
							+ invoice.getItemUnitPrice()+"\t\t"
							+ totalPrice+CommonConstants.SINGLE_SPACE);
					pricesList.add(totalPrice);
				}
				consoleObj.printLineSeparator();
				Double grandTotal = invoiceUtil.calculateGrandTotalAmount(pricesList);
				System.out.println(ScreenFields.GRAND_TOTAL+CommonConstants.COMMON_TEXT_SEPARATOR+grandTotal);
				consoleObj.printLineSeparator();
			}
		}
		else {
			System.err.println(CommonErrors.NO_RECEIPTS);
		}
	}
	
	private List<Integer> fetchAllReceipts(List<PharmaInvoice> invoices) {
		List<Integer> receiptList = new ArrayList<>();
		for(PharmaInvoice invoice: invoices) {
			receiptList.add(invoice.getInvoiceId());
		}
		return receiptList;
	}
	
	/**
	 * <pre>
	 * Loads common header on the screen.
	 * </pre>
	 * 
	 * @param invoice
	 */
	private void loadTableHeader(PharmaInvoice invoice, List<Integer> receiptList) {
		Iterator<Integer> itr = receiptList.iterator();
		System.out.print(ScreenFields.RECEIPT_NO+CommonConstants.COMMON_TEXT_SEPARATOR);
		while(itr.hasNext()) {
			System.out.print(itr.next());
			if(itr.hasNext()) {
				System.out.print(CommonConstants.COMMA_DELIMITER+CommonConstants.SINGLE_SPACE);
			}
		}
		System.out.println();
		System.out.println(ScreenFields.PHARMA_NAME+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getPharmaName());
		System.out.println(ScreenFields.ADDRESS+CommonConstants.SINGLE_SPACE+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getPharmaAddress());
		System.out.println(ScreenFields.MOP+CommonConstants.COMMON_TEXT_SEPARATOR+PaymentMode.getMop(invoice.getPaymentMode()).toString());
		System.out.println(ScreenFields.DATETIME+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getDate()+CommonConstants.COMMA_DELIMITER+CommonConstants.SINGLE_SPACE+invoice.getTime());
		System.out.println(ScreenFields.CONTACT+CommonConstants.COMMON_TEXT_SEPARATOR+invoice.getPharmaContact());
		consoleObj.printLineSeparator();
		System.out.println(
				ScreenFields.DESCRIPTION+CommonConstants.SINGLE_SPACE+ScreenFields.DESCRIPTION_EXTRAS+CommonConstants.SINGLE_TAB
				+ ScreenFields.QUANTITY+CommonConstants.SINGLE_TAB
				+ ScreenFields.UNIT_PRICE+CommonConstants.SINGLE_TAB
				+ ScreenFields.TOTAL+CommonConstants.SINGLE_TAB);
		consoleObj.printLineSeparator();
	}
}