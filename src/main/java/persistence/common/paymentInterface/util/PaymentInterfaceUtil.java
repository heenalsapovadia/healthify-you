package persistence.common.paymentInterface.util;

// author - saloni raythatha
// this is interface for payment interface - process payment

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;

public interface PaymentInterfaceUtil {

    // this method is the complete functionality for processing payment based on credit card, voucher and further generating billing
    int processPayment(PaymentBillingCategory billingCategory,
                        double checkoutAmount, String voucherId);

}