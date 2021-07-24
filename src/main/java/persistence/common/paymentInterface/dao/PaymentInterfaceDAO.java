package persistence.common.paymentInterface.dao;

import persistence.common.paymentInterface.modelPaymentInterface.PaymentInterface;
import persistence.patient.model.Patient;
import java.util.List;
import java.util.Map;

public interface PaymentInterfaceDAO {

    int insertPaymentInterfaceDetails(PaymentInterface paymentInterface);

    public List<PaymentInterface> getAllPaymentInterfaceDetails(Patient patient);

    /**
     * <pre>
     * Calculates and returns the points redeemed by a particular patient.
     * </pre>
     *
     * @param patientId
     * @return voucher points
     */
    public int getVoucherRedemptionPoints(int patientId);

    public int findMaxBillingId();
    
    public Map<Integer, PaymentInterface> getPaymentDetails(List<Integer> billingIdList);
}
