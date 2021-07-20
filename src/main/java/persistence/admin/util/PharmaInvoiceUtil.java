package persistence.admin.util;

import java.util.List;

public interface PharmaInvoiceUtil {
	public double calculateTotalAmount(double unitPrice, int quantity);
	public double calculateGrandTotalAmount(List<Double> prices);
}
