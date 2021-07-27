/**
 * 
 */
package persistence.patient.utilImpl;

import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.daoImpl.RedeemableVoucherDAOImpl;
import persistence.patient.model.RedeemableVoucher;
import persistence.patient.util.RedeemableVoucherUtil;

/**
 * @author Gurleen Saluja
 *
 */
public class RedeemableVoucherUtilImpl implements RedeemableVoucherUtil {

	@Override
	public RedeemableVoucher fetchVoucherByPatientId() {
		RedeemableVoucherDAO redeemableVoucherDAO = new RedeemableVoucherDAOImpl();
		return redeemableVoucherDAO.getVoucherByPatient();
	}
}
