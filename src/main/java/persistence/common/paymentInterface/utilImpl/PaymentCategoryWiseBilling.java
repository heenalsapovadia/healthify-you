package persistence.common.paymentInterface.utilImpl;

// author - saloni raythatha
// this is class for representing enum values for category wise billing labtest, doctor appointment, request medication

import persistence.common.paymentInterface.modelPaymentInterface.PaymentBillingCategory;
import presentation.common.ScreenFields;

public class PaymentCategoryWiseBilling {

    public String enumInIf(PaymentBillingCategory category) {
        if(category == PaymentBillingCategory.L) {
            return ScreenFields.BILL_SUCCESSFUL_FOR_LABTEST;
        }else if (category == PaymentBillingCategory.D) {
            return ScreenFields.BILL_SUCCESSFUL_FOR_DOCTOR_APPOINTMENT;
        }else if (category == PaymentBillingCategory.M) {
            return ScreenFields.BILL_SUCCESSFUL_FOR_REQUEST_MEDICATION;
        }else{
            return ScreenFields.BILL_SUCCESSFUL_FOR_PHARMARCY;
        }
    }
}
