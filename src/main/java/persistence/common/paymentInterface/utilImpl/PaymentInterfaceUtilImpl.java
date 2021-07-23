package persistence.common.paymentInterface.utilImpl;

import persistence.common.paymentInterface.daoImpl.PaymentInterfaceDAOImpl;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCardDetails;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.patient.model.Patient;

import java.util.Date;


public class PaymentInterfaceUtilImpl {
    private PaymentInterfaceDAOImpl paymentPersistence = new PaymentInterfaceDAOImpl();

    public int processPayment(PaymentBillingCategory billingCategory,
                              PaymentCardDetails cardDetails,
                              double remainingAmount, String voucherID) {
        PaymentInterface paymentDetails = new PaymentInterface();
        paymentDetails.setCurrentPaymentMode(persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface.payment_mode.C);
        paymentDetails.setStatusOfPayment(persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface.status.C);
        paymentDetails.setPatient_id(Patient.getPatient().getPatientId());
        paymentDetails.setVoucher_id(voucherID);

        Date d1 = new Date();
        paymentDetails.setBilling_date(d1);

        paymentDetails.setBill_category(billingCategory);
        paymentDetails.setBill_amount(remainingAmount);

        paymentDetails.setCurrentPaymentMode(PaymentInterface.payment_mode.CC);

        double discount = 0;
        paymentDetails.setDiscount(discount);
        paymentDetails.setCreated_on(d1);

        paymentDetails.setStatusOfPayment(PaymentInterface.status.C);

        paymentDetails.setVoucher_redemption_date(d1);
        return paymentPersistence.insertPaymentInterfaceDetails(paymentDetails);
    }

}