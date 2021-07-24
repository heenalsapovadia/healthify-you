package presentation.common;

import persistence.common.paymentInterface.modelPaymentInterface.*;
import persistence.common.paymentInterface.utilImpl.PaymentCategoryWiseBilling;
import persistence.common.paymentInterface.utilImpl.PaymentCreditCardValidation;
import persistence.common.paymentInterface.utilImpl.PaymentInterfaceUtilImpl;
import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.daoImpl.RedeemableVoucherDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.model.RedeemableVoucher;

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
        if (voucher != null) {
            System.out.println(ScreenFields.redeemVoucher + voucher.getVoucherId());
        }
        System.out.println(ScreenFields.voucherIdOption1);
        System.out.println(ScreenFields.voucherIdOption2);
        System.out.println(ScreenFields.paymentExit);

        int sel = sc.nextInt();
        PaymentCategoryWiseBilling categoryEnumValues = new PaymentCategoryWiseBilling();

        if(sel == 1) {
            // Without voucher
            PaymentInterface paymentInterface = new PaymentInterface();
            int billingId = paymentUtil.processPayment(billingCategory ,cardDetails,checkoutAmount, "");
            System.out.println(categoryEnumValues.enumInIf(billingCategory));
            System.out.println("Billing id is: " + billingId);
            return billingId;
        }

        else if(sel == 2) {
            // With voucher
            System.out.println(ScreenFields.enterVoucherId);
            String enteredVoucherId = sc.next();
            if (voucherDAO.getVoucherByPatient(Patient.getPatient().getPatientId()).getVoucherId().equals(enteredVoucherId)) {

                // if voucher has less points than billing checkout amount
                if (voucher.getPoints() < checkoutAmount) {
                    double remainingAmount = checkoutAmount - voucher.getPoints();
                    int billingIdWithVoucher = paymentUtil.processPayment(billingCategory, cardDetails, voucher.getPoints(), voucherId);
                    int billingId2WithCreditCard = paymentUtil.processPayment(billingCategory, cardDetails, remainingAmount, "");
                    // Print out message as needed
                    System.out.println(categoryEnumValues.enumInIf(billingCategory));
                    System.out.println( voucher.getPoints() + " -- Voucher points used for billing and billing id is: " + billingIdWithVoucher);
                    System.out.println( "Remaining balance of " + remainingAmount + " paid through credit card and billing id is: " + billingId2WithCreditCard);
                } else {
                    // if voucher has enough points for billing amount
                    double remainingAmount = voucher.getPoints() - checkoutAmount;
                    if (remainingAmount < 0) {
                        remainingAmount = 0;
                    }
                    int billingId = paymentUtil.processPayment(billingCategory, cardDetails, remainingAmount, voucherId);
                    System.out.println(categoryEnumValues.enumInIf(billingCategory));
                    System.out.println("Payment Successful and billing id is: " + billingId);
                    return billingId;
                }

            } else {
                // if voucher is invalid
                System.out.println("Invalid Voucher. Please pay through credit card.");
            }
        }
        else if(sel == 3) {
            System.out.println(ScreenFields.LOGOUT_MESSAGE);
            System.out.println(ScreenFields.APPLICATION_TERMINATION_MESSAGE);
            System.exit(0);
        }
        else {
            consoleObj.printError(CommonErrors.invalidSelection);
            sel = loadScreenOptions(consoleObj, billingCategory ,checkoutAmount, voucherId);
        }
        return sel;
    }
}
