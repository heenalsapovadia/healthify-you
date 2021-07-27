package persistence.common.paymentInterface.util;

import java.util.List;
/**
 * <pre>
 *
 * PaymentInterface Credit Card Interface
 * This validates all the credit card inputs
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public interface PaymentCreditCardValidationUtil {

    //validating credit card number
     String validateCreditCardNumber(Long creditCardNumber);

     // validating expiry for credit card
     String validateExpiryDate(String Date);

     // validating cvv number
     String validateCvv(Long cvvNumber);

     // Validating credit card functionality
     List<Object> ValidateCC(Long cardNumber, String expirtyDate, Long cvvNumber);
}
