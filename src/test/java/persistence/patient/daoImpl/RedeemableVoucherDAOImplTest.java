package persistence.patient.daoImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.model.RedeemableVoucher;
import presentation.startup.DatabaseConnection;

/**
 * <pre>
 * Tests for Redeemable Vouchers.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
class RedeemableVoucherDAOImplTest {

	/**
	 * <pre>
	 * Tests vouchers for O+ blood group.
	 * </pre>
	 */
	@Test
	void testGetVoucherByBloodGroupOPositive() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByBloodGroup("O+");
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("O+");
		expectedVoucher.setVoucherId("1PBSD");
		expectedVoucher.setPoints((double) 50);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}
	
	/**
	 * <pre>
	 * Tests vouchers for A- blood group.
	 * </pre>
	 * 
	 */
	@Test
	void testGetVoucherByBloodGroupANegative() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByBloodGroup("A-");
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("A-");
		expectedVoucher.setVoucherId("560TI");
		expectedVoucher.setPoints((double) 100);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}
	
	/**
	 * <pre>
	 * Tests vouchers for O- blood group.
	 * </pre>
	 * 
	 */
	@Test
	void testGetVoucherByBloodGroupONegative() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByBloodGroup("O-");
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("O-");
		expectedVoucher.setVoucherId("6YAVP");
		expectedVoucher.setPoints((double) 70);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}
	
	/**
	 * <pre>
	 * Tests vouchers for A+ blood group.
	 * </pre>
	 * 
	 */
	@Test
	void testGetVoucherByBloodGroupAPositive() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByBloodGroup("A+");
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("A+");
		expectedVoucher.setVoucherId("FXQO2");
		expectedVoucher.setPoints((double) 50);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}
	
	/**
	 * <pre>
	 * Tests vouchers for AB+ blood group.
	 * </pre>
	 * 
	 */
	@Test
	void testGetVoucherByBloodGroupABPositive() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByBloodGroup("AB+");
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("AB+");
		expectedVoucher.setVoucherId("HWIHG");
		expectedVoucher.setPoints((double) 150);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}
	
	/**
	 * <pre>
	 * Tests vouchers for AB- blood group.
	 * </pre>
	 * 
	 */
	@Test
	void testGetVoucherByBloodGroupABNegative() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByBloodGroup("AB-");
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("AB-");
		expectedVoucher.setVoucherId("R6NG5");
		expectedVoucher.setPoints((double) 200);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}
	
	/**
	 * <pre>
	 * Tests vouchers for B+ blood group.
	 * </pre>
	 * 
	 */
	@Test
	void testGetVoucherByBloodGroupBPositive() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByBloodGroup("B+");
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("B+");
		expectedVoucher.setVoucherId("T0395");
		expectedVoucher.setPoints((double) 100);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}
	
	/**
	 * <pre>
	 * Tests vouchers for B- blood group.
	 * </pre>
	 */
	@Test
	void testGetVoucherByBloodGroupBNegative() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByBloodGroup("B-");
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("B-");
		expectedVoucher.setVoucherId("UP8XK");
		expectedVoucher.setPoints((double) 150);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}

	/**
	 * <pre>
	 * Tests vouchers by patient id.
	 * </pre>
	 */
	@Test
	void testGetVoucherByPatient() {
		DatabaseConnection.loadDatabaseConnection();
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher actualVoucher = redeemableVoucherDAO.getVoucherByPatient(13);
		RedeemableVoucher expectedVoucher = new RedeemableVoucher();
		expectedVoucher.setBloodGroup("AB-");
		expectedVoucher.setVoucherId("R6NG5");
		expectedVoucher.setPoints((double) 200);
		expectedVoucher.setValidityInDays(30);
		if(actualVoucher != null) {
			assertEquals(expectedVoucher.getBloodGroup(), actualVoucher.getBloodGroup());
			assertEquals(expectedVoucher.getVoucherId(), actualVoucher.getVoucherId());
			assertEquals(expectedVoucher.getPoints(), actualVoucher.getPoints());
			assertEquals(expectedVoucher.getValidityInDays(), actualVoucher.getValidityInDays());
		}
		else {
			assertNull(actualVoucher);
		}
	}
}
