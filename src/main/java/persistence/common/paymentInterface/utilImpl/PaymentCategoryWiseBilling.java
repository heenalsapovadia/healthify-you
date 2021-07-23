package persistence.common.paymentInterface.utilImpl;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import persistence.patient.model.LabCheckBooking;
import presentation.patient.LabTestBookingOutput;

public class PaymentCategoryWiseBilling {

    public String enumInIf(PaymentBillingCategory category) {
        if(category == PaymentBillingCategory.L) {
            return "Billing successful for Lab Health Check Booking. ";
        }else if (category == PaymentBillingCategory.D) {
            return "Billing successful for  Doctor Appointment.";
        }else if (category == PaymentBillingCategory.M) {
            return "Billing successful for  Request Medication.";
        }else{
            return "Billing successful for Pharmacy.";
        }
    }
}
