package persistence.patient.utilImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import persistence.patient.model.Patient;
import persistence.patient.model.RedeemableVoucher;
import persistence.patient.util.RedeemableVoucherUtil;
import presentation.startup.DatabaseConnection;

/**
 * <pre>
 * Tests for Redeemable Voucher util.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
class RedeemableVoucherUtilImplTest {

	@Test
	void testFetchVoucherByPatientId() {
		DatabaseConnection.loadDatabaseConnection();
		Patient.setPatient("gs@gmail.com");
		RedeemableVoucherUtil voucherUtil = new RedeemableVoucherUtilImpl();
		RedeemableVoucher voucher = voucherUtil.fetchVoucherByPatientId();
		assertEquals("R6NG5", voucher.getVoucherId());
		assertEquals("AB-", voucher.getBloodGroup());
		assertEquals(200.0, voucher.getPoints());
		assertEquals(30, voucher.getValidityInDays());
	}
}
