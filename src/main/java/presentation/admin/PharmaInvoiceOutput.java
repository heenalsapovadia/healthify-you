package presentation.admin;

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
import presentation.common.CommonConstants;
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
	
	/**
	 * <pre>
	 * Fetches data from database using DAO and prints output to console.
	 * </pre>
	 * 
	 * @param date
	 */
	public void displayInvoice(Date date) {
		PharmaInvoiceUtil invoiceUtil = new PharmaInvoiceUtilImpl();
		PharmaInvoiceDAO invoiceDAO = new PharmaInvoiceDAOImpl();
		Map<String, List<PharmaInvoice>> invoicesMap = invoiceDAO.getInvoiceDetailsByDate(date);
		List<Double> pricesList;
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.pharmaInvoice);
		if(invoicesMap != null && !invoicesMap.isEmpty()) {
			for(Map.Entry<String, List<PharmaInvoice>> entry: invoicesMap.entrySet()) {
				loadTableHeader(consoleObj, entry.getValue().get(0), fetchAllReceipts(entry.getValue()));
				pricesList = new ArrayList<>();
				for(PharmaInvoice invoice: entry.getValue()) {
					Double totalPrice = invoiceUtil.calculateTotalAmount(invoice.getItemUnitPrice(), invoice.getItemQuantity());
					System.out.println(
							invoice.getItemName()+CommonConstants.singleSpace
							+ invoice.getItemDosage()+CommonConstants.singleSpace
							+ invoice.getItemManufacturer()+"\t\t\t\t"
							+ invoice.getItemQuantity()+"\t\t"
							+ invoice.getItemUnitPrice()+"\t\t"
							+ totalPrice+CommonConstants.singleSpace);
					pricesList.add(totalPrice);
				}
				consoleObj.printLineSeparator();
				Double grandTotal = invoiceUtil.calculateGrandTotalAmount(pricesList);
				System.out.println(ScreenFields.grandTotal+CommonConstants.commonTextSeparator+grandTotal);
				consoleObj.printLineSeparator();
			}
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
	private void loadTableHeader(PrintToConsole consoleObj, PharmaInvoice invoice, List<Integer> receiptList) {
		Iterator<Integer> itr = receiptList.iterator();
		System.out.print(ScreenFields.receiptNo+CommonConstants.commonTextSeparator);
		while(itr.hasNext()) {
			System.out.print(itr.next());
			if(itr.hasNext()) {
				System.out.print(CommonConstants.commaDelimiter+CommonConstants.singleSpace);
			}
		}
		System.out.println();
		System.out.println(ScreenFields.pharmaName+CommonConstants.commonTextSeparator+invoice.getPharmaName());
		System.out.println(ScreenFields.address+CommonConstants.singleSpace+CommonConstants.commonTextSeparator+invoice.getPharmaAddress());
		System.out.println(ScreenFields.mop+CommonConstants.commonTextSeparator+PaymentMode.getMop(invoice.getPaymentMode()).toString());
		System.out.println(ScreenFields.dateTime+CommonConstants.commonTextSeparator+invoice.getDate()+CommonConstants.commaDelimiter+CommonConstants.singleSpace+invoice.getTime());
		System.out.println(ScreenFields.contact+CommonConstants.commonTextSeparator+invoice.getPharmaContact());
		consoleObj.printLineSeparator();
		System.out.println(
				ScreenFields.description+CommonConstants.singleSpace+ScreenFields.descriptionExtras+CommonConstants.singleTab
				+ ScreenFields.quantity+CommonConstants.singleTab
				+ ScreenFields.unitprice+CommonConstants.singleTab
				+ ScreenFields.total+CommonConstants.singleTab);
		consoleObj.printLineSeparator();
	}
}