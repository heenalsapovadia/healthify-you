package persistence.patient.util;

import persistence.patient.model.RedeemableVoucher;

/**
 * @author Gurleen Saluja
 *
 */
public interface RedeemableVoucherUtil {
	
	/**
	 * <pre>
	 * Fetches data from database and passes to 
	 * presentation layer.
	 * </pre>
	 * 
	 * @return voucher
	 */
	RedeemableVoucher fetchVoucherByPatientId();
}
