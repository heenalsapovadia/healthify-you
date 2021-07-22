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


    public String validateCreditCardNumber(Long creditCardNumber) {
        int length = (int) (Math.log10(creditCardNumber) + 1);
        String result1 = "Should start with 512. Please enter again.";
        String result2 = "Card number should be 12 digit long";
        if (length == 12) {
            String temp = creditCardNumber + "";
            if (temp.startsWith("512") && !temp.matches("^[ a-zA-Z]*$")) {
                return null;
            } else {
                return result1;
            }
        } else {
            return result2;
        }
    }

    public String validateExpiryDate(String Date) {
        String dateregex = "^(1[0-2]|0[1-9]|\\d)\\/(20\\d{2}|19\\d{2}|0(?!0)\\d|[1-9]\\d)$\n";
        Pattern pattern = Pattern.compile(dateregex);
        Matcher matcher = pattern.matcher(valueOf(Date));
        if (matcher.matches() == false || Date == null || Date == "") {
            return CommonErrors.invalidDateFormat;
        }
        return null;
    }

    public String validateCvv(Long cvvNumber) {
        int length = (int) (Math.log10(cvvNumber) + 1);
        String result1 = "Should start with 9. Please enter again.";
        String result2 = "Cvv number should be 3 digit long";
        if (length == 3) {
            String temp = cvvNumber + "";
            if (temp.startsWith("9") && !temp.matches("^[ a-zA-Z]*$")) {
                return null;
            } else {
                return result1;
            }
        } else {
            return result2;
        }
    }
}