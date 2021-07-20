package persistence.admin.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import persistence.admin.model.PharmaInvoice;

public interface PharmaInvoiceDAO {
	/**
	 * <pre>
	 * Finds invoice details by date.
	 * </pre>
	 * 
	 * @param date
	 * @return map
	 */
	public Map<String, List<PharmaInvoice>> getInvoiceDetailsByDate(Date date);
}
