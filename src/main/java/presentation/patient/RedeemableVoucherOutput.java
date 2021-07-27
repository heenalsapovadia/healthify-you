package presentation.patient;

import java.util.ArrayList;
import java.util.List;
import persistence.common.paymentInterface.util.PaymentInterfaceUtil;
import persistence.common.paymentInterface.utilImpl.PaymentInterfaceUtilImpl;
import persistence.patient.model.Patient;
import persistence.patient.model.RedeemableVoucher;
import persistence.patient.util.RedeemableVoucherUtil;
import persistence.patient.utilImpl.RedeemableVoucherUtilImpl;
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
	
	public void displayOutput() {
		PrintToConsole consoleObj = PrintToConsole.getInstance();
		RedeemableVoucher voucher = fetchAvailablePoints();
		consoleObj.printSingleNewLine();
		List<String> selectionOptions = getSelectionOptions();
		int sel;
		do {
			consoleObj.printHeader(ScreenTitles.REDEEMABLE_VOUCHERS);
			sel = consoleObj.printSelection(selectionOptions);
			switch(sel) {
				case 1: consoleObj.printSingleNewLine();
						loadVoucher(voucher);
						break;
				case 2: return;
				default: consoleObj.printError(CommonErrors.INVALID_SELECTION);
			}
		}
		while(sel != 2);
	}
	
	private RedeemableVoucher fetchAvailablePoints() {
		int patientId = Patient.instance().getPatientId();
		RedeemableVoucherUtil voucherUtil = new RedeemableVoucherUtilImpl();
		RedeemableVoucher voucher = voucherUtil.fetchVoucherByPatientId();
		if(voucher != null) {
			pointsAvailable = voucher.getPoints();
		}
		System.out.println(ScreenFields.POINTS_AVAILABLE+CommonConstants.COMMON_TEXT_SEPARATOR+pointsAvailable);
		PaymentInterfaceUtil paymentUtil = new PaymentInterfaceUtilImpl();
		pointsRedeemed = paymentUtil.getPointsRedeemed(patientId);
		if(pointsRedeemed > 0) { 
			System.out.println(ScreenFields.POINTS_REDEEMED+CommonConstants.SINGLE_SPACE+CommonConstants.COMMON_TEXT_SEPARATOR+pointsRedeemed);
		}
		else {
			System.out.println(ScreenFields.POINTS_REDEEMED+CommonConstants.SINGLE_SPACE+CommonConstants.COMMON_TEXT_SEPARATOR+CommonConstants.ZERO);
		}
		return voucher;
	}
	
	private List<String> getSelectionOptions() {
		List<String> selectionOptions = new ArrayList<>();
		selectionOptions.add(ScreenFields.VIEW_VOUCHER);
		selectionOptions.add(ScreenFields.EXIT);
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
