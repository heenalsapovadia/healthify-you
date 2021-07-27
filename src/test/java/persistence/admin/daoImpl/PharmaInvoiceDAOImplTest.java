package persistence.admin.daoImpl;

import static org.junit.Assert.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import persistence.admin.dao.PharmaInvoiceDAO;
import persistence.admin.model.PharmaInvoice;
import presentation.startup.DatabaseConnection;

/**
 * @author Gurleen Saluja
 *
 */
public class PharmaInvoiceDAOImplTest {

	/**
	 * <pre>
	 * Tests invoices by date.
	 * </pre>
	 * 
	 */
	@Test
	public void testGetInvoiceDetailsByDate() {
		DatabaseConnection.loadDatabaseConnection();
		PharmaInvoiceDAO pharmaInvoiceDAO = new PharmaInvoiceDAOImpl(); 
		Map<String, List<PharmaInvoice>> invoicesMap = new HashMap<>();
		invoicesMap.put("Cyano Pharma", getPharmaInvoice1());
		invoicesMap.put("Hydro Pharma", getPharmaInvoice2());
		Map<String, List<PharmaInvoice>> generatedMap = pharmaInvoiceDAO.getInvoiceDetailsByDate(Date.valueOf("2021-07-04"));
	    assertEquals(generatedMap.size(), invoicesMap.size());
	    assertTrue(generatedMap.keySet().containsAll(invoicesMap.keySet()));
	    generatedMap.keySet().stream().forEach((key) -> {
	        List<PharmaInvoice> expectedList = generatedMap.get(key);
	        List<PharmaInvoice> actualList = invoicesMap.get(key);
	        for(int i=0; i<expectedList.size(); i++) {
	        	assertTrue(expectedList.get(i).equals(actualList.get(i)));
	        }
	    });
	}
	
	/**
	 * <pre>
	 * Generates static data list 1.
	 * </pre>
	 * @return list
	 */
	private List<PharmaInvoice> getPharmaInvoice1() {
		List<PharmaInvoice> invoicesList = new ArrayList<>();
		PharmaInvoice pharmaInvoice = new PharmaInvoice();
		pharmaInvoice.setInvoiceId(1);
		pharmaInvoice.setPharmaName("Cyano Pharma");
		pharmaInvoice.setPharmaAddress("115/C, Pologround Industrial Estate");
		pharmaInvoice.setPharmaContact("+19028201920");
		pharmaInvoice.setPaymentMode("C");
		pharmaInvoice.setItemName("Crocin");
		pharmaInvoice.setItemDosage("500mg");
		pharmaInvoice.setItemQuantity(10000);
		pharmaInvoice.setItemUnitPrice(155.76);
		pharmaInvoice.setItemManufacturer("GSKAP");
		pharmaInvoice.setDate(Date.valueOf("2021-07-04"));
		pharmaInvoice.setTime(Time.valueOf("10:11:41"));
		invoicesList.add(pharmaInvoice);
		PharmaInvoice pharmaInvoiceNew = new PharmaInvoice();
		pharmaInvoiceNew.setInvoiceId(2);
		pharmaInvoiceNew.setPharmaName("Cyano Pharma");
		pharmaInvoiceNew.setPharmaAddress("115/C, Pologround Industrial Estate");
		pharmaInvoiceNew.setPharmaContact("+19028201920");
		pharmaInvoiceNew.setPaymentMode("C");
		pharmaInvoiceNew.setItemName("Combiflam");
		pharmaInvoiceNew.setItemDosage("500mg");
		pharmaInvoiceNew.setItemQuantity(2000);
		pharmaInvoiceNew.setItemUnitPrice(50.0);
		pharmaInvoiceNew.setItemManufacturer("GSKAP");
		pharmaInvoiceNew.setDate(Date.valueOf("2021-07-04"));
		pharmaInvoiceNew.setTime(Time.valueOf("11:08:42"));
		invoicesList.add(pharmaInvoiceNew);
		return invoicesList;
	}
	
	/**
	 * <pre>
	 * Generates static data list 2.
	 * </pre>
	 * 
	 * @return
	 */
	private List<PharmaInvoice> getPharmaInvoice2() {
		List<PharmaInvoice> invoicesList = new ArrayList<>();
		PharmaInvoice pharmaInvoiceLatest = new PharmaInvoice();
		pharmaInvoiceLatest.setInvoiceId(3);
		pharmaInvoiceLatest.setPharmaName("Hydro Pharma");
		pharmaInvoiceLatest.setPharmaAddress("226/C Medicinal Zone");
		pharmaInvoiceLatest.setPharmaContact("+19028201930");
		pharmaInvoiceLatest.setPaymentMode("OTH");
		pharmaInvoiceLatest.setItemName("Dynapar");
		pharmaInvoiceLatest.setItemDosage("50mg");
		pharmaInvoiceLatest.setItemQuantity(2000);
		pharmaInvoiceLatest.setItemUnitPrice(64.5);
		pharmaInvoiceLatest.setItemManufacturer("Troikaa");
		pharmaInvoiceLatest.setDate(Date.valueOf("2021-07-04"));
		pharmaInvoiceLatest.setTime(Time.valueOf("16:10:05"));
		invoicesList.add(pharmaInvoiceLatest);
		return invoicesList;
	}
}
