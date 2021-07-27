package persistence.common.paymentInterface.modelPaymentInterface;
/**
 * <pre>
 *
 * Test Method for model class Payment Billing Category
 * </pre>
 *
 * @author Saloni Raythatha
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PaymentBillingCategoryTest {
    @Test
    public void testValueOf() {
        PaymentBillingCategory.valueOf("Name");
    }

    @Test
    public void testValueOf1() {
        PaymentBillingCategory.valueOf("Name");
    }

    @Test
    public void testValueOf2() {
        PaymentBillingCategory.valueOf("foo");
    }

    @Test
    public void testValueOf3() {
        assertEquals(PaymentBillingCategory.D, PaymentBillingCategory.valueOf("D"));
    }

    @Test
    public void testValueOf4() {
        assertEquals(PaymentBillingCategory.L, PaymentBillingCategory.valueOf("L"));
    }

    @Test
    public void testValueOf5() {
        assertEquals(PaymentBillingCategory.M, PaymentBillingCategory.valueOf("M"));
    }

    @Test
    public void testValues() {
        PaymentBillingCategory[] actualValuesResult = PaymentBillingCategory.values();
        assertEquals(4, actualValuesResult.length);
        assertEquals(PaymentBillingCategory.D, actualValuesResult[0]);
        assertEquals(PaymentBillingCategory.L, actualValuesResult[1]);
        assertEquals(PaymentBillingCategory.M, actualValuesResult[2]);
        assertEquals(PaymentBillingCategory.P, actualValuesResult[3]);
    }

    @Test
    public void testValues2() {
        PaymentBillingCategory[] actualValuesResult = PaymentBillingCategory.values();
        assertEquals(4, actualValuesResult.length);
        assertEquals(PaymentBillingCategory.D, actualValuesResult[0]);
        assertEquals(PaymentBillingCategory.L, actualValuesResult[1]);
        assertEquals(PaymentBillingCategory.M, actualValuesResult[2]);
        assertEquals(PaymentBillingCategory.P, actualValuesResult[3]);
    }

    @Test
    public void testValues3() {
        PaymentBillingCategory[] actualValuesResult = PaymentBillingCategory.values();
        assertEquals(4, actualValuesResult.length);
        assertEquals(PaymentBillingCategory.D, actualValuesResult[0]);
        assertEquals(PaymentBillingCategory.L, actualValuesResult[1]);
        assertEquals(PaymentBillingCategory.M, actualValuesResult[2]);
        assertEquals(PaymentBillingCategory.P, actualValuesResult[3]);
    }
}

