package presentation.common;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCardDetails;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCreditCardValidation;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.common.paymentInterface.utilImpl.PaymentInterfaceUtilImpl;
import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.daoImpl.RedeemableVoucherDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.model.RedeemableVoucher;
import presentation.patient.RedeemableVoucherOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentInterfaceOutput {

    private PaymentInterfaceUtilImpl paymentUtil = new PaymentInterfaceUtilImpl();

    private static class PaymentInterfaceOutputHelper {
        private static final PaymentInterfaceOutput instance = new PaymentInterfaceOutput();
    }

    public static PaymentInterfaceOutput getInstance() {
        return PaymentInterfaceOutput.PaymentInterfaceOutputHelper.instance;
    }

    // modify argumnts to (BillingCategory, Amount)
    public int processPayment(PaymentBillingCategory billingCategory, double checkoutAmount, String voucherId) {
        PrintToConsole consoleObj = PrintToConsole.getInstance();
        consoleObj.printHeader(ScreenTitles.paymentInterface);
        return loadScreenOptions(consoleObj, billingCategory,checkoutAmount,voucherId);
    }

    private int loadScreenOptions(PrintToConsole consoleObj,
                                  PaymentBillingCategory billingCategory,
                                  double checkoutAmount, String voucherId) {
        List<String> selectionOptions = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String redeemVoucherAmount;

        // Credit card validation
        Long cardNumber = null;
        String expirtyDate = null;
        Long cvvNumber = null;

        PaymentCreditCardValidation paymentCreditCardValidation = new PaymentCreditCardValidation();
        paymentCreditCardValidation.ValidateCC(cardNumber, expirtyDate, cvvNumber);
        PaymentCardDetails cardDetails = new PaymentCardDetails();
        cardDetails.setCardNumber(cardNumber);
        cardDetails.setCvvNumber(cvvNumber);
        cardDetails.setExpirtyDate(expirtyDate);

        // below is for redeem voucher and further process
        System.out.println(ScreenFields.checkoutAmount + checkoutAmount);
        RedeemableVoucherDAO voucherDAO = new RedeemableVoucherDAOImpl();
        RedeemableVoucher voucher = voucherDAO.getVoucherByPatient(Patient.getPatient().getPatientId());
        if(voucher!=null) {
            System.out.println(ScreenFields.redeemVoucher + voucher.getVoucherId());
        }
        System.out.println(ScreenFields.voucherIdOption1);
        System.out.println(ScreenFields.voucherIdOption2);
        System.out.println(ScreenFields.paymentExit);

        int sel = sc.nextInt();

        if(sel == 1) {
            // Without voucher
            PaymentInterface paymentInterface = new PaymentInterface();
            int billingId = paymentUtil.processPayment(billingCategory ,cardDetails, checkoutAmount, "");
            System.out.println("Payment Successful and billing id is: " + billingId);
            return billingId;
        }

        else if(sel == 2) {
            // With voucher
            if(voucher == null) {
                System.out.println("No Reedemable voucher available. Please enter your selection again.");
                return 0;
            }
            System.out.println(ScreenFields.enterVoucherId);
            String enteredVoucherId = sc.next();
            if (voucherDAO.getVoucherByPatient(Patient.getPatient().getPatientId()).getVoucherId().equals(enteredVoucherId)) {
                double remainingAmount = voucher.getPoints() - checkoutAmount;
                if (remainingAmount < 0) {
                    remainingAmount = 0;
                }
                int billingId = paymentUtil.processPayment(billingCategory, cardDetails, remainingAmount, voucherId);
                System.out.println("Payment Successful and billing id is: " + billingId);
                return billingId;

            } else {
                System.out.println("Invalid Voucher. Please pay through Credit Card.");
                //int billingId = paymentUtil.processPayment(billingCategory,cardDetails ,checkoutAmount, voucherId);
                //System.out.println("Payment Successful and billing id is: " + billingId);
                //return billingId;
            }
        }
        else if(sel == 3) {
            System.out.println(ScreenFields.logoutMessage);
            System.out.println(ScreenFields.applicationTerminationMessage);
            System.exit(0);
        }
        else {
            consoleObj.printError(CommonErrors.invalidSelection);
            sel = loadScreenOptions(consoleObj, billingCategory ,checkoutAmount, voucherId);
        }
        return sel;
    }


}
