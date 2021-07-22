package persistence.common.paymentInterface.modelPaymentInterface;

import persistence.common.paymentInterface.utilImpl.PaymentInterfaceUtilImpl;
import presentation.common.ScreenFields;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PaymentCreditCardValidation {

    private PaymentInterfaceUtilImpl paymentUtil = new PaymentInterfaceUtilImpl();
    public final List<Object> ValidateCC(Long cardNumber, String expirtyDate, Long cvvNumber){

        Scanner sc = new Scanner(System.in);
        System.out.println(ScreenFields.cardNumber);
        cardNumber = sc.nextLong();
        if(paymentUtil.validateCreditCardNumber(cardNumber) != null){
            do {
                System.out.println("Wrong credit card number. Please enter again");
                cardNumber = sc.nextLong();
            } while (paymentUtil.validateCreditCardNumber(cardNumber) != null);
        }
        System.out.println(ScreenFields.expirydate);
        expirtyDate= sc.next();
        if(paymentUtil.validateExpiryDate(expirtyDate) != null){
            do {
                System.out.println("Wrong date. Please enter again");
                expirtyDate= sc.next();
            } while (paymentUtil.validateCreditCardNumber(cardNumber) != null);
        }
        System.out.println(ScreenFields.cvvNumber);
        cvvNumber = sc.nextLong();
        if(paymentUtil.validateCvv(cvvNumber) != null){
            do {
                System.out.println("Wrong Cvv. Please start with 9 and make sure its 3 digit long.");
                cvvNumber = sc.nextLong();
            } while (paymentUtil.validateCreditCardNumber(cardNumber) != null);
        }
        return Arrays.asList(cardNumber, expirtyDate, cvvNumber);
    }
}
