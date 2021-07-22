package presentation.common;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCardDetails;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCreditCardValidation;
import persistence.common.paymentInterface.utilImpl.PaymentInterfaceUtilImpl;
import persistence.patient.model.Patient;
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
    public int processPayment(Patient patient,
                              PaymentBillingCategory billingCategory,
                              double checkoutAmount) {
        PrintToConsole consoleObj = PrintToConsole.getInstance();
        consoleObj.printHeader(ScreenTitles.paymentInterface);
        return loadScreenOptions(consoleObj, patient, billingCategory, checkoutAmount);
    }

    private int loadScreenOptions(PrintToConsole consoleObj,
                                  Patient patient,
                                  PaymentBillingCategory billingCategory,
                                  double checkoutAmount) {
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
        String voucherId = "XYZWS";
        System.out.println(ScreenFields.voucherId);
        System.out.println(ScreenFields.paymentExit);
        int sel = sc.nextInt();
        if(sel == 1) {
            int billingId = paymentUtil.processPayment(patient, billingCategory, cardDetails, voucherId , checkoutAmount);
            System.out.println("Payment Successful with Billing ID - " + billingId);
        }
        else if(sel == 2) {
            System.out.println(ScreenFields.logoutMessage);
            System.out.println(ScreenFields.applicationTerminationMessage);
            System.exit(0);
        }
        else {
            consoleObj.printError(CommonErrors.invalidSelection);
            sel = loadScreenOptions(consoleObj, patient, billingCategory, checkoutAmount);
        }
        System.out.println("\n");
        return sel;
    }


}
