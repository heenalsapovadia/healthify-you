package presentation.common;
/**
 * <pre>
 *
 * this is presentation/output for payment interface
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
import persistence.common.paymentInterface.modelPaymentInterface.*;
import persistence.common.paymentInterface.util.PaymentInterfaceUtil;
import persistence.common.paymentInterface.utilImpl.PaymentCategoryWiseBilling;
import persistence.common.paymentInterface.utilImpl.PaymentCreditCardValidation;
import persistence.common.paymentInterface.utilImpl.PaymentInterfaceUtilImpl;
import persistence.patient.dao.RedeemableVoucherDAO;
import persistence.patient.daoImpl.RedeemableVoucherDAOImpl;
import persistence.patient.model.RedeemableVoucher;
import java.util.Scanner;

public class PaymentInterfaceOutput  {
    private static class PaymentInterfaceOutputHelper {
        private static final PaymentInterfaceOutput instance = new PaymentInterfaceOutput();
    }

    public static PaymentInterfaceOutput getInstance() {
        return PaymentInterfaceOutput.PaymentInterfaceOutputHelper.instance;
    }

    public int processPayment(PaymentBillingCategory billingCategory, double checkoutAmount, String voucherId) {
        PrintToConsole consoleObj = PrintToConsole.getInstance();
        consoleObj.printHeader(ScreenTitles.paymentInterface);
        return loadScreenOptions(consoleObj, billingCategory,checkoutAmount);
    }

    public int loadScreenOptions(PrintToConsole consoleObj,
                                  PaymentBillingCategory billingCategory,
                                  double checkoutAmount) {


        // Enter - credit details and it will go through validation..
        Scanner sc = new Scanner(System.in);
        Long cardNumber = null;
        String expirtyDate = null;
        Long cvvNumber = null;

        //PaymentCreditCardValidation - for validating credit card
        PaymentCreditCardValidation paymentCreditCardValidation = new PaymentCreditCardValidation();
        paymentCreditCardValidation.ValidateCC(cardNumber, expirtyDate, cvvNumber);
        PaymentCardDetails cardDetails = new PaymentCardDetails();
        cardDetails.setCardNumber(cardNumber);
        cardDetails.setCvvNumber(cvvNumber);
        cardDetails.setExpirtyDate(expirtyDate);

        // below is for redeem voucher and further process
        System.out.println(ScreenFields.CHECKOUT_AMOUNT + checkoutAmount);
        RedeemableVoucherDAO voucherDAO = new RedeemableVoucherDAOImpl();
        RedeemableVoucher voucher = voucherDAO.getVoucherByPatient();

        if (voucher != null) {
            System.out.println(ScreenFields.REDEEM_VOUCHER + voucher.getVoucherId());
            return launhScreenOptionsWithVoucher(consoleObj, sc, voucherDAO, billingCategory, checkoutAmount, voucher);
        } else {
            return launhScreenOptionsWithoutVoucher(consoleObj, sc, billingCategory, checkoutAmount);
        }
    }

    // below method to display the option when voucher is available in patients database
    private int launhScreenOptionsWithVoucher(PrintToConsole consoleObj,
                                             Scanner sc,
                                             RedeemableVoucherDAO voucherDAO,
                                             PaymentBillingCategory billingCategory,
                                             double checkoutAmount, RedeemableVoucher voucher) {

        System.out.println(ScreenFields.VOUCHER_ID_OPTION1);
        System.out.println(ScreenFields.VOUCHER_ID_OPTION2);

        PaymentInterfaceUtil paymentUtil = new PaymentInterfaceUtilImpl();
        PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();

        int sel = sc.nextInt();
        PaymentCategoryWiseBilling categoryEnumValues = new PaymentCategoryWiseBilling();

        if (sel == 1) {
            // Without voucher
            int billingId = paymentUtil.processPayment(billingCategory, checkoutAmount, "");
            System.out.println(categoryEnumValues.enumInIf(billingCategory));
            System.out.println("Billing id is: " + billingId);
            return billingId;
        } else if (sel == 2) {
            // With voucher
            System.out.println(ScreenFields.ENTER_VOUCHERID);
            String enteredVoucherId = sc.next();
            if (voucher.getVoucherId().equals(enteredVoucherId)) {

                // if voucher has less points than billing checkout amount then use voucher and remaining from credit card..
                if (voucher.getPoints() < checkoutAmount) {
                    double remainingAmount = checkoutAmount - voucher.getPoints();
                    int billingIdWithVoucher = paymentUtil.processPayment(billingCategory, voucher.getPoints(), voucher.getVoucherId());
                    int billingId2WithCreditCard = paymentUtil.processPayment(billingCategory, remainingAmount, "");
                    System.out.println(categoryEnumValues.enumInIf(billingCategory));
                    System.out.println(voucher.getPoints() + ScreenFields.VOUCHER_POINTS_USED_FOR_BILLING + billingIdWithVoucher);
                    System.out.println(ScreenFields.BALANCE_REMAINING_FROM_CHECKOUT + remainingAmount + ScreenFields.REMAINING_BALANCE_PAID_THROUGH_CREDITCARD + billingId2WithCreditCard);
                } else {
                    // if voucher has enough points for billing amount
                    double remainingAmount = voucher.getPoints() - checkoutAmount;
                    if (remainingAmount < 0) {
                        remainingAmount = 0;
                    }
                    int billingId = paymentUtil.processPayment(billingCategory, remainingAmount, voucher.getVoucherId());
                    System.out.println(categoryEnumValues.enumInIf(billingCategory));
                    System.out.println(ScreenFields.BILLING_ID_GENERATED + billingId);
                    return billingId;
                }
            } else {
                // if voucher is invalid
                System.out.println(ScreenFields.INVALID_VOUCHER);
            }
        } else {
            consoleObj.printError(CommonErrors.INVALID_SELECTION);
            sel = paymentInterfaceOutput.loadScreenOptions(consoleObj, billingCategory, checkoutAmount);
        }
        return sel;
    }

    // below method to display the option when voucher is not available in patients database
    private int launhScreenOptionsWithoutVoucher(PrintToConsole consoleObj,
                                                Scanner sc,
                                                PaymentBillingCategory billingCategory,
                                                double checkoutAmount) {
        System.out.println(ScreenFields.VOUCHER_ID_OPTION1);
        System.out.println(ScreenFields.VOUCHER_ID_OPTION2);
        PaymentInterfaceUtil paymentUtil = new PaymentInterfaceUtilImpl();
        PaymentInterfaceOutput paymentInterfaceOutput = new PaymentInterfaceOutput();

        int sel = sc.nextInt();
        PaymentCategoryWiseBilling categoryEnumValues = new PaymentCategoryWiseBilling();
        if (sel == 1) {
            // Without voucher
            int billingId = paymentUtil.processPayment(billingCategory,checkoutAmount, "");
            System.out.println(categoryEnumValues.enumInIf(billingCategory));
            System.out.println(ScreenFields.BILLING_ID + billingId);
            return billingId;
        }
        else {
            consoleObj.printError(ScreenFields.VOUCHER_NOT_AVAILABLE);
            sel = paymentInterfaceOutput.loadScreenOptions(consoleObj, billingCategory ,checkoutAmount);
        }
        return sel;
    }
}
