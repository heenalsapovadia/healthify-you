package persistence.common.paymentInterface.utilImpl;

import persistence.common.paymentInterface.util.PaymentCreditCardValidationUtil;
import presentation.common.CommonErrors;
import presentation.common.ScreenFields;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * <pre>
 *
 * PaymentInterface Credit card validation implementation
 *
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class PaymentCreditCardValidation implements PaymentCreditCardValidationUtil {

    public String validateCreditCardNumber(Long creditCardNumber) {
        int length = (int) (Math.log10(creditCardNumber) + 1);
        String result1 = ScreenFields.ENTER_VALID_CREDITCARD_Number;
        String result2 = ScreenFields.MIN_CARD_NUMBER;
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
        String result1 = ScreenFields.ENTER_VALID_CVV;
        String result2 = ScreenFields.MIN_CVV_NUMBER;
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
        System.out.println(ScreenFields.CARD_NUMBER);
        cardNumber = sc.nextLong();
        if(validateCreditCardNumber(cardNumber) != null){
            do {
                System.out.println(ScreenFields.WRONG_CARD_NUMBER);
                cardNumber = sc.nextLong();
            } while (validateCreditCardNumber(cardNumber) != null);
        }

        System.out.println(ScreenFields.EXPIRY_DATE);
        expirtyDate= sc.next();
        if(validateExpiryDate(expirtyDate) != null){
            do {
                System.out.println(ScreenFields.WRONG_DATE);
                expirtyDate= sc.next();
            } while (validateExpiryDate(expirtyDate) != null);
        }
        System.out.println(ScreenFields.CVV_NUMBER);
        cvvNumber = sc.nextLong();
            if(validateCvv(cvvNumber) != null){
            do {
                System.out.println(ScreenFields.WRONG_CVV_NUMBER);
                cvvNumber = sc.nextLong();
            } while (validateCvv(cvvNumber) != null);
        }
        return Arrays.asList(cardNumber, expirtyDate, cvvNumber);
    }
}
