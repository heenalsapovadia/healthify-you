package persistence.admin.util;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import persistence.admin.model.PharmaInvoice;

public interface PharmaInvoiceUtil {
	
	/**
	 * <pre>
	 * Calculates amount of different pharma supplies by
	 * multiplying quantity with unit price.
	 * </pre>
	 * 
	 * @param unitPrice
	 * @param quantity
	 * @return
	 */
	double calculateTotalAmount(double unitPrice, int quantity);
	
	/**
	 * <pre>
	 * Performs summation on all prices in present in an invoice.
	 * </pre>
	 * 
	 * @param prices
	 * @return
	 */
	double calculateGrandTotalAmount(List<Double> prices);
	
	/**
	 * <pre>
	 * Fetches data for all invoices for a given date from the database.
	 * </pre>
	 * 
	 * @return
	 */
	Map<String, List<PharmaInvoice>> fetchMapFromDatabase(Date date);
}
