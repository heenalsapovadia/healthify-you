package persistence.patient.dao;

import persistence.patient.model.RedeemableVoucher;

/**
 * @author Gurleen Saluja
 *
 */
public interface RedeemableVoucherDAO {
	
	/**
	 * <pre>
	 * Fetches Redeemable Voucher details using blood group of a patient.
	 * </pre>
	 * 
	 * @param bloodGroup
	 * @return Voucher
	 */
	RedeemableVoucher getVoucherByBloodGroup(String bloodGroup);
	
	/**
	 * <pre>
	 * Fetches Redeemable Voucher Details using patient id of a patient.
	 * </pre>
	 * 
	 * @return
	 */
	RedeemableVoucher getVoucherByPatient();
}
