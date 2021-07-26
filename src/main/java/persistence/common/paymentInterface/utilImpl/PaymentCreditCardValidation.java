package persistence.common.paymentInterface.utilImpl;

import presentation.common.CommonErrors;
import presentation.common.ScreenFields;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class PaymentCreditCardValidation {

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
        String dateregex = "^[0-9]{4}-(1[0-2]|0[1-9])$";
        Pattern pattern = Pattern.compile(dateregex);
        Matcher matcher = pattern.matcher(Date);
        if (matcher.matches() == false || Date == null || Date == "") {
            return CommonErrors.INVALID_DATE_FORMAT;
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

    public final List<Object> ValidateCC(Long cardNumber, String expirtyDate, Long cvvNumber){

        Scanner sc = new Scanner(System.in);
        System.out.println(ScreenFields.cardNumber);
        cardNumber = sc.nextLong();
        if(validateCreditCardNumber(cardNumber) != null){
            do {
                System.out.println("Wrong credit card number. Please enter again");
                cardNumber = sc.nextLong();
            } while (validateCreditCardNumber(cardNumber) != null);
        }

        System.out.println(ScreenFields.expirydate);
        expirtyDate= sc.next();
        if(validateExpiryDate(expirtyDate) != null){
            do {
                System.out.println("Wrong date. Please enter again");
                expirtyDate= sc.next();
            } while (validateExpiryDate(expirtyDate) != null);
        }
        System.out.println(ScreenFields.cvvNumber);
        cvvNumber = sc.nextLong();
            if(validateCvv(cvvNumber) != null){
            do {
                System.out.println("Wrong Cvv. Please start with 9 and make sure its 3 digit long.");
                cvvNumber = sc.nextLong();
            } while (validateCvv(cvvNumber) != null);
        }
        return Arrays.asList(cardNumber, expirtyDate, cvvNumber);
    }


}
