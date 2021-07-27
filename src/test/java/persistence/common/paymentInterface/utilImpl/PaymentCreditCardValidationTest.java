package persistence.common.paymentInterface.utilImpl;
/**
 * Test cases for Payment Interface credit card module.
 * @author Saloni Raythatha
 *
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
public class PaymentCreditCardValidationTest {

    @Test
    public void testValidateCvv() {
        assertEquals("Cvv number should be 3 digit long", (new PaymentCreditCardValidation()).validateCvv(1L));
    }

    @Test
    public void testValidateExpiryDate() {
        assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!",
                (new PaymentCreditCardValidation()).validateExpiryDate("2020-03-01"));
        assertNull((new PaymentCreditCardValidation()).validateExpiryDate("9999-10"));
    }

    @Test
    public void testValidateExpiryDate2() {
        String date1 = "1999/08/21";
        String date2 = "11-10-2016";
        String date3 = "10-08/2015";
        PaymentCreditCardValidation payment = new PaymentCreditCardValidation();
        assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!", payment.validateExpiryDate(date1));
        assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!", payment.validateExpiryDate(date2));
        assertEquals("Invalid Date Format! Date should be formatted as yyyy-mm-dd!", payment.validateExpiryDate(date3));
    }


    @Test
    public void testValidateCreditCardNumber() {
        assertEquals("Card number should be 12 digit long",
                (new PaymentCreditCardValidation()).validateCreditCardNumber(1L));
    }
}

