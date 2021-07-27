package persistence.common.paymentInterface.util;

// author - saloni raythatha
// this is interface for payment credit card validation

import java.util.List;

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
