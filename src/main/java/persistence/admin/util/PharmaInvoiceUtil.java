package persistence.admin.util;

import java.util.List;

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
	public double calculateTotalAmount(double unitPrice, int quantity);
	
	/**
	 * <pre>
	 * Performs summation on all prices in present in an invoice.
	 * </pre>
	 * 
	 * @param prices
	 * @return
	 */
	public double calculateGrandTotalAmount(List<Double> prices);
}
