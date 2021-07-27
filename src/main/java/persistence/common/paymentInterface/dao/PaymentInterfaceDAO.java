package persistence.common.paymentInterface.dao;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.patient.model.Patient;
import java.util.List;
import java.util.Map;
/**
 * <pre>
 *
 * PaymentInterface Database DAO - performs database functionality from payment_billing, voucher table
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public interface PaymentInterfaceDAO {

    int insertPaymentInterfaceDetails(PaymentInterface paymentInterface);

    List<PaymentInterface> getAllPaymentInterfaceDetails(Patient patient);

    /**
     * <pre>
     * Calculates and returns the points redeemed by a particular patient.
     * </pre>
     *
     * @param patientId
     * @return voucher points
     */
    int getVoucherRedemptionPoints(int patientId);

    int findMaxBillingId();
    
    /**
     * <pre>
     * Generates map of Payment corresponding to billing id.
     * </pre>
     * 
     * @param billingIdList
     * @return map
     */
     Map<Integer, PaymentInterface> getPaymentDetails(List<Integer> billingIdList);
}
