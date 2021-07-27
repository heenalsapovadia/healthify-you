package persistence.common.paymentInterface.util;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
/**
 * <pre>
 *
 * PaymentInterface Util - method to process all the payment through the interface
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public interface PaymentInterfaceUtil {

    // this method is the complete functionality for processing payment based on credit card, voucher and further generating billing
    int processPayment(PaymentBillingCategory billingCategory,
                        double checkoutAmount, String voucherId);
    
    /**
     * <pre>
     * Calculates points redeemed by patient till date
     * whenever a payment was made.
     * </pre>
     * 
     * @return points
     */
    int getPointsRedeemed(int patientId);
}