package persistence.common.paymentInterface.util;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCardDetails;
import persistence.doctor.model.Prescription;
import persistence.patient.model.Patient;

public interface PaymentInterfaceUtil {

    void processPayment(PaymentBillingCategory billingCategory,
                        double checkoutAmount, String voucherId);


    public String validateCreditCardNumber(Long creditCardNumber);

    public String validateExpiryDate(String Date);

    public String validateCvv(Long cvvNumber);

}