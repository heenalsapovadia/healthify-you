package persistence.common.paymentInterface.utilImpl;

import persistence.common.paymentInterface.daoImpl.PaymentInterfaceDAOImpl;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentCardDetails;
import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.doctor.model.Prescription;
import persistence.patient.model.Patient;
import presentation.common.CommonErrors;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class PaymentInterfaceUtilImpl {
    private PaymentInterfaceDAOImpl paymentPersistence = new PaymentInterfaceDAOImpl();

    public int processPayment(Patient patient,
                              PaymentBillingCategory billingCategory,
                              PaymentCardDetails paymentCardDetails,
                              String voucherID,
                              double remainingAmount) {
        PaymentInterface paymentDetails = new PaymentInterface();
        paymentDetails.setCurrentPaymentMode(persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface.payment_mode.C);
        paymentDetails.setStatusOfPayment(persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface.status.C);
        paymentDetails.setPatient_id(patient.getPatientId());
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