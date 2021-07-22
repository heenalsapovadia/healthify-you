/**
 * 
 */
package presentation.patient;

import java.util.ArrayList;
import java.util.List;
import persistence.common.paymentInterface.dao.PaymentInterfaceDAO;
import persistence.common.paymentInterface.daoImpl.PaymentInterfaceDAOImpl;
import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.daoImpl.RedeemableVoucherDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.model.RedeemableVoucher;
import presentation.common.CommonConstants;
import presentation.common.CommonErrors;
import presentation.common.PrintToConsole;
import presentation.common.ScreenFields;
import presentation.common.ScreenTitles;

/**
 * <pre>
 * Displays the Redeemable Vouchers Screen.
 * If a voucher is available displays the voucher code.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class RedeemableVoucherOutput {
	
	private double pointsAvailable;
	
	private double pointsRedeemed;
	
	public RedeemableVoucher displayOutput() {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		consoleObj.printHeader(ScreenTitles.REDEEMABLE_VOUCHERS);
		RedeemableVoucher voucher = fetchAvailablePoints();
		consoleObj.printSingleNewLine();
		List<String> selectionOptions = getSelectionOptions();
		int sel;
		do {
			sel = consoleObj.printSelection(selectionOptions);
			switch(sel) {
				case 1: consoleObj.printSingleNewLine();
						loadVoucher(voucher);
						return voucher;
				case 2:
					return null;
				default: consoleObj.printError(CommonErrors.invalidSelection);
				return null;
			}
		}
		while(sel != 2);
	}
	
	private RedeemableVoucher fetchAvailablePoints() {
		int patientId = Patient.getPatient().getPatientId();
		RedeemableVoucherDAO voucherDAO = new RedeemableVoucherDAOImpl();
		RedeemableVoucher voucher = voucherDAO.getVoucherByPatient(patientId);
		if(voucher != null) {
			pointsAvailable = voucher.getPoints();
		}
		System.out.println(ScreenFields.POINTS_AVAILABLE+CommonConstants.commonTextSeparator+pointsAvailable);
		PaymentInterfaceDAO paymentDAO = new PaymentInterfaceDAOImpl();
		pointsRedeemed = paymentDAO.getVoucherRedemptionPoints(patientId);
		if(pointsRedeemed > 0) { 
			System.out.println(ScreenFields.POINTS_REDEEMED+CommonConstants.singleSpace+CommonConstants.commonTextSeparator+pointsRedeemed);
		}
		else {
			System.out.println(ScreenFields.POINTS_REDEEMED+CommonConstants.singleSpace+CommonConstants.commonTextSeparator+CommonConstants.ZERO);
		}
		return voucher;
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.VIEW_VOUCHER);
		selectionOptions.add(ScreenFields.exit);
		return selectionOptions;
	}
	
	private void loadVoucher(RedeemableVoucher voucher) {
		if(voucher != null && pointsAvailable != pointsRedeemed) {
			System.out.println("Yayy! You have a voucher to be redeemed! The voucher code is "+voucher.getVoucherId());
		}
		else {
			System.out.println("Alas! Seems you do not have any voucher available!");
		}
	}
}
