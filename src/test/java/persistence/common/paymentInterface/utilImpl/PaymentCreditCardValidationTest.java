package persistence.common.paymentInterface.utilImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class PaymentCreditCardValidationTest {
    @Test
    public void testValidateCreditCardNumber() {
        assertEquals("Card number should be 12 digit long",
                (new PaymentCreditCardValidation()).validateCreditCardNumber(1L));
    }

    @Test
    public void testValidateExpiryDate() {
        assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!",
                (new PaymentCreditCardValidation()).validateExpiryDate("2020-03-01"));
        assertNull((new PaymentCreditCardValidation()).validateExpiryDate("9999-10"));
    }

    @Test
    public void testValidateCvv() {
        assertEquals("Cvv number should be 3 digit long", (new PaymentCreditCardValidation()).validateCvv(922l));
    }
}

