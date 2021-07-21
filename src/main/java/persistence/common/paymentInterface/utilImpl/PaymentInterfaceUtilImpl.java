package persistence.common.paymentInterface.utilImpl;

import persistence.admin.model.PaymentMode;
import persistence.common.paymentInterface.daoImpl.PaymentInterfaceDAOImpl;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCardDetails;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.doctor.model.Prescription;
import persistence.patient.model.Patient;

import java.util.Date;

public class PaymentInterfaceUtilImpl {
    private PaymentInterfaceDAOImpl paymentPersistence = new PaymentInterfaceDAOImpl();
    public void processPayment(Patient patient,
                               Prescription prescription,
                               PaymentBillingCategory billingCategory,
                               PaymentCardDetails paymentCardDetails,
                               String voucherAmount,
                               int checkoutAmount){
        PaymentInterface paymentDetails = new PaymentInterface();
        paymentDetails.setCurrentPaymentMode(persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface.payment_mode.C);
        paymentDetails.setStatusOfPayment(persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface.status.C);
        paymentDetails.setPatient_id(patient.getPatientId());
        paymentDetails.setPrescription_id(prescription.getPrescription_id());
        paymentDetails.setVoucher_id(voucherAmount);

        Date d1 = new Date();
        paymentDetails.setBilling_date(d1);

        paymentDetails.setBill_category(billingCategory);
        paymentDetails.setBill_amount(checkoutAmount);

        String modeOfPayment = null;
        paymentDetails.setCurrentPaymentMode(PaymentInterface.payment_mode.valueOf(modeOfPayment));

        double discount = 0;
        paymentDetails.setDiscount(discount);
        paymentDetails.setCreated_on(d1);
        
        String statusOfPayment = null;
        paymentDetails.setStatusOfPayment(PaymentInterface.status.valueOf(statusOfPayment));

        paymentDetails.setVoucher_redemption_date(d1);
        paymentPersistence.insertPaymentInterfaceDetails(paymentDetails);

    }
}
