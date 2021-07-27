package persistence.admin.utilImpl;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import persistence.admin.util.PharmaInvoiceUtil;

/**
 * <pre>
 * Tests for pharma supplies util.
 * 
 * Test for fetchMapFromDatabase is covered 
 * in PharmaInvpiceDAOImplTest.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PharmaInvoiceUtilImplTest {

	/**
	 * Tests the total amount calculation.
	 */
	@Test
	public void testCalculateTotalAmount() {
		PharmaInvoiceUtil util = new PharmaInvoiceUtilImpl();
		double calculatedValue = util.calculateTotalAmount(155.76, 3);
		assertEquals(467.28, calculatedValue, 0);
	}

	/**
	 * Tests summation of prices in an invoice.
	 */
	@Test
	public void testCalculateGrandTotalAmount() {
		PharmaInvoiceUtil util = new PharmaInvoiceUtilImpl();
		List<Double> prices = new ArrayList<>();
		prices.add(467.28);
		prices.add(500.00);
		double calculatedValue = util.calculateGrandTotalAmount(prices); 
		assertEquals(967.28, calculatedValue, 0);
	}

}
