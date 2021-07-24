package persistence.admin.utilImpl;

import java.util.List;
import persistence.admin.util.PharmaInvoiceUtil;

public class PharmaInvoiceUtilImpl implements PharmaInvoiceUtil{

	@Override
	public double calculateTotalAmount(double unitPrice, int quantity) {
		return unitPrice * quantity;
	}

	@Override
	public double calculateGrandTotalAmount(List<Double> prices) {
		return prices.stream().reduce(0.0d, (leftOperand, rightOperand)->leftOperand+rightOperand);
	}
	
}
