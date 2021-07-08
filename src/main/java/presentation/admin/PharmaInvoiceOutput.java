package presentation.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import persistence.admin.dao.PharmaInvoiceDAO;
import persistence.admin.daoImpl.PharmaInvoiceDAOImpl;
import persistence.admin.model.PharmaInvoice;
import persistence.admin.util.PharmaInvoiceUtil;
import persistence.admin.utilImpl.PharmaInvoiceUtilImpl;
import presentation.CommonConstants;
import presentation.ScreenFields;
import presentation.ScreenTitles;

/**
 * <pre>
 * Prints the invoice to console.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PharmaInvoiceOutput {
	
	private static final Logger LOGGER = Logger.getLogger(PharmaInvoiceOutput.class.getName());
	
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
		List<Double> pricesList = new ArrayList<>();
		for(Map.Entry<String, List<PharmaInvoice>> entry: invoicesMap.entrySet()) {
			for(int i=0; i<100; i++)
				System.out.print(CommonConstants.headingChar);
			System.out.println();
			System.out.println(CommonConstants.titleSpace+ScreenTitles.pharmaInvoice+CommonConstants.titleSpace);
			for(int i=0; i<100; i++)
				System.out.print(CommonConstants.headingChar);
			System.out.println();
			loadHeader(entry.getValue().get(0));
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
			for(int i=0; i<100; i++)
				System.out.print(CommonConstants.headingChar);
			System.out.println();
			Double grandTotal = invoiceUtil.calculateGrandTotalAmount(pricesList);
			System.out.println(ScreenFields.grandTotal+CommonConstants.commonTextSeparator+grandTotal);
		}
	}
	
	/**
	 * <pre>
	 * Loads common header on the screen.
	 * </pre>
	 * 
	 * @param invoice
	 */
	private void loadHeader(PharmaInvoice invoice) {
		System.out.println(ScreenFields.receiptNo+CommonConstants.commonTextSeparator+invoice.getInvoiceId());
		System.out.println(ScreenFields.pharmaName+CommonConstants.commonTextSeparator+invoice.getPharmaName());
		System.out.println(ScreenFields.address+CommonConstants.commonTextSeparator+invoice.getPharmaAddress());
		System.out.println(ScreenFields.mop+CommonConstants.commonTextSeparator+invoice.getPaymentMode());
		System.out.println(ScreenFields.dateTime+CommonConstants.commonTextSeparator+invoice.getDate()+CommonConstants.commaDelimiter+invoice.getTime());
		System.out.println(ScreenFields.contact+CommonConstants.commonTextSeparator+invoice.getPharmaContact());
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		System.out.println(
				ScreenFields.description+CommonConstants.singleSpace+ScreenFields.descriptionExtras+CommonConstants.singleTab
				+ ScreenFields.quantity+CommonConstants.singleTab
				+ ScreenFields.unitprice+CommonConstants.singleTab
				+ ScreenFields.total+CommonConstants.singleTab);
	}
}