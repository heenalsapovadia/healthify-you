package persistence.common.paymentInterface.util;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCardDetails;
import persistence.doctor.model.Prescription;
import persistence.patient.model.Patient;

public interface PaymentInterfaceUtil {

    void processPayment(Patient patient,
                        Prescription prescription,
                        PaymentBillingCategory billingCategory,
                        PaymentCardDetails paymentCardDetails,
                        int voucherAmount,
                        int checkoutAmount);
    }

