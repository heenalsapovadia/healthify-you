package persistence.admin.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import persistence.admin.model.PharmaInvoice;

/**
 * <pre>
 * Fetches pharma supplies details from the database.
 * </pre>
 * @author Gurleen Saluja
 *
 */
public interface PharmaInvoiceDAO {
	/**
	 * <pre>
	 * Finds invoice details by date.
	 * </pre>
	 * 
	 * @param date
	 * @return map
	 */
	Map<String, List<PharmaInvoice>> getInvoiceDetailsByDate(Date date);
	
	/**
	 * <pre>
	 * Returns a list of pharma supplies using a list of medicine names.
	 * </pre>
	 * 
	 * @param medicineName
	 * @return
	 */
	List<PharmaInvoice> getPharmaSupplies(List<String> medicineName);

	/**
	 * <pre>
	 * Returns a set of medicine names bought by the hospital.
	 * </pre>
	 * 
	 * @return set
	 */
	Set<String> getMedicineList();
}
