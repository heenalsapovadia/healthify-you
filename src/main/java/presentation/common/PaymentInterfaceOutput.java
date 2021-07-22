package presentation.common;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCardDetails;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCreditCardValidation;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.common.paymentInterface.utilImpl.PaymentInterfaceUtilImpl;
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
        return loadScreenOptions(consoleObj, billingCategory, checkoutAmount, voucherId);
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
        System.out.println(ScreenFields.redeemVoucher);
        RedeemableVoucherOutput voucherOutput = new RedeemableVoucherOutput();
        RedeemableVoucher voucher = voucherOutput.displayOutput();
        System.out.println(ScreenFields.voucherIdOption1);
        System.out.println(ScreenFields.voucherIdOption2);
        System.out.println(ScreenFields.paymentExit);

        int sel = sc.nextInt();
        if(sel == 1) {
            // Without voucher
            PaymentInterface paymentInterface = new PaymentInterface();
            int billingId = paymentUtil.processPayment(billingCategory, checkoutAmount, "");
            System.out.println("Payment Successful and billing id is: " + billingId);
            return billingId;
        }
        else if(sel == 2) {
            // With voucher
            System.out.println(ScreenFields.enterVoucherId);
            String enteredVoucherId = sc.next();
            if (voucher.getVoucherId().equals(enteredVoucherId)) {
                double remainingAmount = checkoutAmount - voucher.getPoints();
                if (remainingAmount < 0 ) {
                    remainingAmount = 0;
                }
                int billingId = paymentUtil.processPayment(billingCategory, remainingAmount, voucherId);
                System.out.println("Payment Successful and billing id is: " + billingId);
                return billingId;

            } else {
                System.out.println("Invalid Voucher");
                int billingId = paymentUtil.processPayment(billingCategory, checkoutAmount, "");
                System.out.println("Payment Successful and billing id is: " + billingId);
                return billingId;
            }
        }
        else if(sel == 3) {
            System.out.println(ScreenFields.logoutMessage);
            System.out.println(ScreenFields.applicationTerminationMessage);
            System.exit(0);
        }
        else {
            consoleObj.printError(CommonErrors.invalidSelection);
            sel = loadScreenOptions(consoleObj, billingCategory, checkoutAmount, voucherId);
        }
        return sel;
    }


}
