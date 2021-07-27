package persistence.admin.utilImpl;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import persistence.admin.dao.PharmaInvoiceDAO;
import persistence.admin.daoImpl.PharmaInvoiceDAOImpl;
import persistence.admin.model.PharmaInvoice;
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

	@Override
	public Map<String, List<PharmaInvoice>> fetchMapFromDatabase(Date date) {
		PharmaInvoiceDAO pharmaInvoiceDAO = new PharmaInvoiceDAOImpl();
		return pharmaInvoiceDAO.getInvoiceDetailsByDate(date);
	}
	
}
